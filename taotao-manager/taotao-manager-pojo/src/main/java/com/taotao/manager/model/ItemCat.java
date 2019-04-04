/******************************************************************
** 类    名：ItemCat
** 描    述：商品类目
** 创 建 者：bianj
** 创建时间：2019-03-30 19:25:38
******************************************************************/

package com.taotao.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品类目(TB_ITEM_CAT)
 * 
 * @author bianj
 * @version 1.0.0 2019-03-30
 */
@Entity
@Table(name = "TB_ITEM_CAT")
public class ItemCat implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 406803419169094880L;
    
    /** 类目ID */
    @Column(name = "ID")
    private Long id;
    
    /** 父类目ID=0时，代表的是一级的类目 */
    @Column(name = "PARENT_ID")
    private Long parentId;
    
    /** 类目名称 */
    @Column(name = "NAME")
    private String name;
    
    /** 状态。可选值:1(正常),2(删除) */
    @Column(name = "STATUS")
    private Integer status;
    
    /** 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数 */
    @Column(name = "SORT_ORDER")
    private Integer sortOrder;
    
    /** 该类目是否为父类目，1为true，0为false */
    @Column(name = "IS_PARENT")
    private Boolean isParent;
    
    /** 创建时间 */
    @Column(name = "CREATED")
    private Date created;
    
    /** 创建时间 */
    @Column(name = "UPDATED")
    private Date updated;
    
    /**
     * 获取类目ID
     * 
     * @return 类目ID
     */
    public Long getId() {
        return this.id;
    }
     
    /**
     * 设置类目ID
     * 
     * @param id
     *          类目ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * 获取父类目ID=0时，代表的是一级的类目
     * 
     * @return 父类目ID=0时
     */
    public Long getParentId() {
        return this.parentId;
    }
     
    /**
     * 设置父类目ID=0时，代表的是一级的类目
     * 
     * @param parentId
     *          父类目ID=0时，代表的是一级的类目
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    /**
     * 获取类目名称
     * 
     * @return 类目名称
     */
    public String getName() {
        return this.name;
    }
     
    /**
     * 设置类目名称
     * 
     * @param name
     *          类目名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取状态。可选值:1(正常),2(删除)
     * 
     * @return 状态。可选值:1(正常)
     */
    public Integer getStatus() {
        return this.status;
    }
     
    /**
     * 设置状态。可选值:1(正常),2(删除)
     * 
     * @param status
     *          状态。可选值:1(正常),2(删除)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * 获取排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     * 
     * @return 排列序号
     */
    public Integer getSortOrder() {
        return this.sortOrder;
    }
     
    /**
     * 设置排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     * 
     * @param sortOrder
     *          排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    /**
     * 获取该类目是否为父类目，1为true，0为false
     * 
     * @return 该类目是否为父类目
     */
    public Boolean getIsParent() {
        return this.isParent;
    }
     
    /**
     * 设置该类目是否为父类目，1为true，0为false
     * 
     * @param isParent
     *          该类目是否为父类目，1为true，0为false
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getCreated() {
        return this.created;
    }
     
    /**
     * 设置创建时间
     * 
     * @param created
     *          创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    
    /**
     * 获取创建时间
     * 
     * @return 创建时间
     */
    public Date getUpdated() {
        return this.updated;
    }
     
    /**
     * 设置创建时间
     * 
     * @param updated
     *          创建时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     *  获得类目名称(easyui)
     * @return
     */
    public String getText() {
        return this.name;
    }

    /**
     *  判断是否是子节点(easyui)
     * @return
     */
    public String getState() {
        return isParent ? "closed" : "open";
    }
}