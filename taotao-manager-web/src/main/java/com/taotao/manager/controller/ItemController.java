package com.taotao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.model.Item;
import com.taotao.manager.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    //@Autowired
    @Reference(interfaceClass = ItemService.class)
    private ItemService itemService;

    @PostMapping(value = "/item")
    @ResponseBody
    public String addItem(Item item, String desc) throws Exception {
        int acount = itemService.add(item, desc);
        return "success";
    }

    @GetMapping(value = "/item")
    @ResponseBody
    public Map<String, Object> list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "rows", required = false, defaultValue = "10") int size) {
        PageInfo<Item> pageInfo = itemService.getPageList(page, size);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", pageInfo.getTotal());
        dataMap.put("rows", pageInfo.getList());
        return dataMap;
    }

    @PostMapping(value = "/item/update")
    @ResponseBody
    public String update(Item item, String desc, HttpSession session) throws Exception {
        Item oldItem = itemService.getOneById(item.getId());
        int ucount = itemService.updateItem(item, desc);
        // 修改成功后删除之前的图片(若有修改图片)
        if (ucount > 0 && StringUtils.isNotBlank(oldItem.getImage()) && !oldItem.getImage().equals(item.getImage())) {
            String image = oldItem.getImage();
            image = image.replaceAll("http://admin.taotao.com", "");
            String[] fileNames = image.split(",");
            String realPath = session.getServletContext().getRealPath("/");
            for (String fileName : fileNames) {
                if (new File(realPath + fileName).exists()) {
                    new File(realPath + fileName).delete();
                }
            }
        }
        if (ucount <= 0) {
            return "error";
        }
        return "success";
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping(value = "item/delete")
    public String delete(@RequestParam(value = "")List<Object> ids) throws Exception {
        int dcount = itemService.deleteByIds(ids);
        return "success";
    }
}
