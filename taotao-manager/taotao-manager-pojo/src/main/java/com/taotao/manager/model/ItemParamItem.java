/******************************************************************
** 类    名：ItemParamItem
** 描    述：商品规格和商品的关系表
** 创 建 者：bianj
** 创建时间：2019-03-30 19:25:38
******************************************************************/

package com.taotao.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品规格和商品的关系表(TB_ITEM_PARAM_ITEM)
 * 
 * @author bianj
 * @version 1.0.0 2019-03-30
 */
@Entity
@Table(name = "TB_ITEM_PARAM_ITEM")
public class ItemParamItem implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 3494473167264202249L;
    
    /**  */
    @Column(name = "ID")
    private Long id;
    
    /** 商品ID */
    @Column(name = "ITEM_ID")
    private Long itemId;
    
    /** 参数数据，格式为json格式 */
    @Column(name = "PARAM_DATA")
    private String paramData;
    
    /**  */
    @Column(name = "CREATED")
    private Date created;
    
    /**  */
    @Column(name = "UPDATED")
    private Date updated;
    
    /**
     * 获取
     * 
     * @return 
     */
    public Long getId() {
        return this.id;
    }
     
    /**
     * 设置
     * 
     * @param id
     *          
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * 获取商品ID
     * 
     * @return 商品ID
     */
    public Long getItemId() {
        return this.itemId;
    }
     
    /**
     * 设置商品ID
     * 
     * @param itemId
     *          商品ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    
    /**
     * 获取参数数据，格式为json格式
     * 
     * @return 参数数据
     */
    public String getParamData() {
        return this.paramData;
    }
     
    /**
     * 设置参数数据，格式为json格式
     * 
     * @param paramData
     *          参数数据，格式为json格式
     */
    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public Date getCreated() {
        return this.created;
    }
     
    /**
     * 设置
     * 
     * @param created
     *          
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public Date getUpdated() {
        return this.updated;
    }
     
    /**
     * 设置
     * 
     * @param updated
     *          
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}