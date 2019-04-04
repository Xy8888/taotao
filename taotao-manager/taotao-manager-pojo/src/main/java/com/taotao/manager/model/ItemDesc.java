/******************************************************************
** 类    名：ItemDesc
** 描    述：商品描述表
** 创 建 者：bianj
** 创建时间：2019-03-30 19:25:38
******************************************************************/

package com.taotao.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品描述表(TB_ITEM_DESC)
 * 
 * @author bianj
 * @version 1.0.0 2019-03-30
 */
@Entity
@Table(name = "TB_ITEM_DESC")
public class ItemDesc implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1551257661298567157L;
    
    /** 商品ID */
    @Column(name = "ITEM_ID")
    private Long itemId;
    
    /** 商品描述 */
    @Column(name = "ITEM_DESC")
    private String itemDesc;
    
    /** 创建时间 */
    @Column(name = "CREATED")
    private Date created;
    
    /** 更新时间 */
    @Column(name = "UPDATED")
    private Date updated;
    
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
     * 获取商品描述
     * 
     * @return 商品描述
     */
    public String getItemDesc() {
        return this.itemDesc;
    }
     
    /**
     * 设置商品描述
     * 
     * @param itemDesc
     *          商品描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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
     * 获取更新时间
     * 
     * @return 更新时间
     */
    public Date getUpdated() {
        return this.updated;
    }
     
    /**
     * 设置更新时间
     * 
     * @param updated
     *          更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}