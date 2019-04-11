package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.manager.mapper.ContentCategoryMapper;
import com.taotao.manager.model.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
@Service(interfaceClass = ContentCategoryService.class)
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    /**
     * 根据父级id查找
     * @param parentId
     * @return
     */
    @Override
    public List<ContentCategory> getListByParentId(Long parentId) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        return contentCategoryMapper.select(contentCategory);
    }

    @Override
    public ContentCategory add(ContentCategory contentCategory) {
        // 补全数据
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(contentCategory.getCreated());
        contentCategory.setIsParent(false);

        int acount = contentCategoryMapper.insertSelective(contentCategory);
        // 如果是在叶子节点添加的，需要改成非叶子节点状态
        ContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        if (parentContentCategory != null && !parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKeySelective(parentContentCategory);
        }
        return contentCategory;
    }

    @Override
    public int updateContentCategory(ContentCategory contentCategory) {
        contentCategory.setUpdated(new Date());
        return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public int deleteContentCategory(ContentCategory contentCategory) {
        // 查找到当前内容所有子类,递归查找
        List allIds = new ArrayList<Long>();
        allDeleteNode(allIds, contentCategory.getId());
        allIds.add(contentCategory.getId());
        // 根据所有id查询
        int dcount = super.deleteByIds(allIds);
        // 判断父级是否还有子节点,如果没有则需修改isParent为false
        if (contentCategory.getParentId() > 0) {
            // 查询该类的父类下是否还有其他子类
            ContentCategory searchCategory = new ContentCategory();
            searchCategory.setParentId(contentCategory.getParentId());
            int count = contentCategoryMapper.selectCount(searchCategory);
            if (count <= 0) {
                ContentCategory parentCategory = new ContentCategory();
                parentCategory.setId(contentCategory.getParentId());
                parentCategory.setIsParent(false);
                contentCategoryMapper.updateByPrimaryKeySelective(parentCategory);
            }
        }
        return dcount;
    }

    /**
     *  递归添加所有需要删除的子节点
     * @param allIds
     * @param id
     */
    private void allDeleteNode(List<Long> allIds, Long id) {
        List<ContentCategory> list = getListByParentId(id);
        if (list != null && list.size() > 0) {
            for (ContentCategory contentCategory : list) {
                allIds.add(contentCategory.getId());
                allDeleteNode(allIds, contentCategory.getId());
            }
        }
    }
}
