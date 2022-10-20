package com.demo;

import com.demo.util.JedisUtil;
import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        // get Jedis object
        Jedis jedis = JedisUtil.getJedis();
        String s = jedis.set("username", "admin");
        System.out.println(s);
        // close connection
        jedis.close();
    }
}
