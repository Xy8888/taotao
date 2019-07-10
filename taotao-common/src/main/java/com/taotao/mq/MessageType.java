package com.taotao.mq;

import java.io.Serializable;

public class MessageType implements Serializable{

    // 消息类型, 0:删除  1: 增加  2: 修改
    private int type;

    //消息内容
    private String message;

    public MessageType() {

    }

    public MessageType(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
