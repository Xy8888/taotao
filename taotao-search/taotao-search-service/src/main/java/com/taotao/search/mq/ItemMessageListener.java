package com.taotao.search.mq;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.Constant;
import com.taotao.mq.MessageType;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

public class ItemMessageListener implements MessageListener{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemService itemService;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                // 分析信息
                MessageType messageType = objectMapper.readValue(text, MessageType.class);
                // 增加或修改时增加索引
                switch (messageType.getType()) {
                    case Constant.MsgType.ITEM_ADD :
                    case Constant.MsgType.ITEM_UPDATE:
                        itemService.importItem2Index(Long.parseLong(messageType.getMessage()));
                        break;
                    case Constant.MsgType.ITEM_DELETE:
                        itemService.deleteItemIndex(objectMapper.readValue(messageType.getMessage(), List.class));
                        break;
                    default:
                        break;
                }
                /*if (messageType.getType() == Constant.MsgType.ITEM_ADD || messageType.getType() == Constant.MsgType.ITEM_UPDATE) {
                    itemService.importItem2Index(Long.parseLong(messageType.getMessage()));
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
