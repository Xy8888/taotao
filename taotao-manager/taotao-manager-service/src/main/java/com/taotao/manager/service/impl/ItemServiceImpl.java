package com.taotao.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.Constant;
import com.taotao.manager.mapper.ItemDescMapper;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.model.Item;
import com.taotao.manager.model.ItemDesc;
import com.taotao.manager.service.ItemService;
import com.taotao.mq.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;
//import org.springframework.stereotype.Service;

@Component
@Service(interfaceClass = ItemService.class)
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination destination;

    /**
     * 增加商品的同时增加索引
     * @param item
     * @param desc
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public int add(Item item, String desc) throws JsonProcessingException {
        // 补全数据
        long id = System.currentTimeMillis() + (long) (Math.random() * 100000);
        item.setId(id);
        item.setStatus(1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        int acount = itemMapper.insertSelective(item);

        // 增加详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        acount = itemDescMapper.insertSelective(itemDesc);
        // 发送消息到mq
        if (acount > 0) {
            String msg = objectMapper.writeValueAsString(new MessageType(Constant.MsgType.ITEM_ADD, String.valueOf(id)));
            sendItemMsg(msg);
        }
        return acount;
    }

    /**
     * 根据id删除商品的同时删除索引库索引
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public int deleteByIds(List<Object> ids) throws Exception {
        int dcount = super.deleteByIds(ids);
        if (dcount > 0) {
            String msg = objectMapper.writeValueAsString(new MessageType(Constant.MsgType.ITEM_DELETE, objectMapper.writeValueAsString(ids)));
            sendItemMsg(msg);
        }
        return dcount;
    }

    /**
     * 修改的同时删除索引库索引
     * @param item
     * @param desc
     * @return
     * @throws Exception
     */
    @Override
    public int updateItem(Item item, String desc) throws Exception {
        Date now = new Date();
        Long id = item.getId();
        item.setUpdated(now);
        int ucount = itemMapper.updateByPrimaryKeySelective(item);
        // 修改详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setUpdated(now);
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        if (ucount > 0) {
            String msg = objectMapper.writeValueAsString(new MessageType(Constant.MsgType.ITEM_UPDATE, String.valueOf(id)));
            sendItemMsg(msg);
        }
        return ucount;
    }

    /**
     * 发送消息到mq
     * @param msg
     */
    private void sendItemMsg(String msg) {
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
