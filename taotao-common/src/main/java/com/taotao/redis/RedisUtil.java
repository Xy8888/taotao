package com.taotao.redis;

public interface RedisUtil {

    /**
     * 增加数据，永不过时
     */
    void add(String key, String value);

    /***
     * 添加数据
     * 该方法有有效期限制
     * @param key
     * @param value
     * @param seconds
     */
    void add(String key,String value,Integer seconds);

    /***
     * 删除操作
     * @param key
     */
    void del(String key);

    /***
     * 根据key查询数据
     * @param key
     * @return
     */
    String get(String key);

    /***
     * 过期
     * @param key
     * @param seconds
     */
    void expire(String key,Integer seconds);

    /****
     * 判断是否存在对应的key的值
     * @param key
     * @return
     */
    boolean exists(String key);
}
