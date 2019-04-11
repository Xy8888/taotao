/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.0.0
 */

package com.taotao.manager.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表(TB_USER)
 * 
 * @author bianj
 * @version 1.0.0 2019-04-11
 */
@Entity
@Table(name = "TB_USER")
public class User implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 4345289333259382540L;

    /**  */
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 19)
    private Long id;

    /** 用户名 */
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    /** 密码，加密存储 */
    @Column(name = "PASSWORD", nullable = false, length = 32)
    private String password;

    /** 注册手机号 */
    @Column(name = "PHONE", unique = true, nullable = true, length = 20)
    private String phone;

    /** 注册邮箱 */
    @Column(name = "EMAIL", unique = true, nullable = true, length = 50)
    private String email;

    /**  */
    @Column(name = "CREATED", nullable = false)
    private Date created;

    /**  */
    @Column(name = "UPDATED", nullable = false)
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
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     * 
     * @return 用户名
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 设置用户名
     * 
     * @param username
     *          用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码，加密存储
     * 
     * @return 密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码，加密存储
     * 
     * @param password
     *          密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取注册手机号
     * 
     * @return 注册手机号
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置注册手机号
     * 
     * @param phone
     *          注册手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取注册邮箱
     * 
     * @return 注册邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置注册邮箱
     * 
     * @param email
     *          注册邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}