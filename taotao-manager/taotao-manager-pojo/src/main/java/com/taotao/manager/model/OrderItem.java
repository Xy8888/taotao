/******************************************************************
** 类    名：OrderItem
** 描    述：
** 创 建 者：bianj
** 创建时间：2019-03-30 19:25:38
******************************************************************/

package com.taotao.manager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (TB_ORDER_ITEM)
 * 
 * @author bianj
 * @version 1.0.0 2019-03-30
 */
@Entity
@Table(name = "TB_ORDER_ITEM")
public class OrderItem implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 328894469404745533L;
    
    /**  */
    @Column(name = "ID")
    private String id;
    
    /** 商品id */
    @Column(name = "ITEM_ID")
    private String itemId;
    
    /** 订单id */
    @Column(name = "ORDER_ID")
    private String orderId;
    
    /** 商品购买数量 */
    @Column(name = "NUM")
    private Integer num;
    
    /** 商品标题 */
    @Column(name = "TITLE")
    private String title;
    
    /** 商品单价 */
    @Column(name = "PRICE")
    private Long price;
    
    /** 商品总金额 */
    @Column(name = "TOTAL_FEE")
    private Long totalFee;
    
    /** 商品图片地址 */
    @Column(name = "PIC_PATH")
    private String picPath;
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getId() {
        return this.id;
    }
     
    /**
     * 设置
     * 
     * @param id
     *          
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 获取商品id
     * 
     * @return 商品id
     */
    public String getItemId() {
        return this.itemId;
    }
     
    /**
     * 设置商品id
     * 
     * @param itemId
     *          商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    /**
     * 获取订单id
     * 
     * @return 订单id
     */
    public String getOrderId() {
        return this.orderId;
    }
     
    /**
     * 设置订单id
     * 
     * @param orderId
     *          订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    /**
     * 获取商品购买数量
     * 
     * @return 商品购买数量
     */
    public Integer getNum() {
        return this.num;
    }
     
    /**
     * 设置商品购买数量
     * 
     * @param num
     *          商品购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }
    
    /**
     * 获取商品标题
     * 
     * @return 商品标题
     */
    public String getTitle() {
        return this.title;
    }
     
    /**
     * 设置商品标题
     * 
     * @param title
     *          商品标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * 获取商品单价
     * 
     * @return 商品单价
     */
    public Long getPrice() {
        return this.price;
    }
     
    /**
     * 设置商品单价
     * 
     * @param price
     *          商品单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }
    
    /**
     * 获取商品总金额
     * 
     * @return 商品总金额
     */
    public Long getTotalFee() {
        return this.totalFee;
    }
     
    /**
     * 设置商品总金额
     * 
     * @param totalFee
     *          商品总金额
     */
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
    
    /**
     * 获取商品图片地址
     * 
     * @return 商品图片地址
     */
    public String getPicPath() {
        return this.picPath;
    }
     
    /**
     * 设置商品图片地址
     * 
     * @param picPath
     *          商品图片地址
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}