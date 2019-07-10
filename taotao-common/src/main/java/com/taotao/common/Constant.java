package com.taotao.common;

/**
 * 系统常量
 */
public class Constant {

    /**
     * rediskey
     */
    public static class RedisKey {
        public static final String INDEX_BIG_CONTENT = "INDEX_BANNER_CATEGORY_ID_";
    }

    /**
     * 消息类型
     */
    public static class MsgType {
        public static final int ITEM_DELETE = 0;
        public static final int ITEM_ADD = 1;
        public static final int ITEM_UPDATE = 2;
    }
}
