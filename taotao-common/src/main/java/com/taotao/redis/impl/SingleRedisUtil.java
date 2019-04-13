package com.taotao.redis.impl;

import com.taotao.redis.RedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class SingleRedisUtil implements RedisUtil{
    private JedisPool jedisPool;

    /* (non-Javadoc)
     * @see com.taotao.util.redis.RedisUtil#add(java.lang.String, java.lang.String)
     */
    @Override
    public void add(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    /* (non-Javadoc)
     * @see com.taotao.util.redis.RedisUtil#add(java.lang.String, java.lang.String, java.lang.Integer)
     */
    @Override
    public void add(String key, String value, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);

        //设置过期时间
        jedis.expire(key, seconds);

        //关闭jedis
        jedis.close();
    }

    /* (non-Javadoc)
     * @see com.taotao.util.redis.RedisUtil#del(java.lang.String)
     */
    @Override
    public void del(String key) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(key);
        jedis.close();
    }

    /* (non-Javadoc)
     * @see com.taotao.util.redis.RedisUtil#get(java.lang.String)
     */
    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    /* (non-Javadoc)
     * @see com.taotao.util.redis.RedisUtil#expire(java.lang.String, java.lang.Integer)
     */
    @Override
    public void expire(String key, Integer seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
        jedis.close();
    }

    @Override
    public boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean exists = jedis.exists(key);
        jedis.close();
        return exists;
    }

    /**
     * @param jedisPool the jedisPool to set
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
