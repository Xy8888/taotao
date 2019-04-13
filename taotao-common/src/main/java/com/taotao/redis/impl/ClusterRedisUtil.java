package com.taotao.redis.impl;

import com.taotao.redis.RedisUtil;
import redis.clients.jedis.JedisCluster;

public class ClusterRedisUtil implements RedisUtil {

    private JedisCluster jedisCluster;

    /*
     * (non-Javadoc)
     *
     * @see com.taotao.util.redis.RedisUtil#add(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void add(String key, String value) {
        jedisCluster.set(key, value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.taotao.util.redis.RedisUtil#add(java.lang.String,
     * java.lang.String, java.lang.Integer)
     */
    @Override
    public void add(String key, String value, Integer seconds) {
        jedisCluster.set(key, value);

        // 设置过期时间
        jedisCluster.expire(key, seconds);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.taotao.util.redis.RedisUtil#del(java.lang.String)
     */
    @Override
    public void del(String key) {
        // TODO Auto-generated method stub
        jedisCluster.del(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.taotao.util.redis.RedisUtil#get(java.lang.String)
     */
    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.taotao.util.redis.RedisUtil#expire(java.lang.String,
     * java.lang.Integer)
     */
    @Override
    public void expire(String key, Integer seconds) {
        jedisCluster.expire(key, seconds);
    }

    @Override
    public boolean exists(String key) {
        Boolean exists = jedisCluster.exists(key);
        return exists;
    }

    /**
     * @param jedisCluster
     *            the jedisCluster to set
     */
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

}
