package com.yc.memcachedtest;
import com.chart.myUtil.MemcachedUtils;
import com.danga.MemCached.MemCachedClient;


import com.danga.MemCached.SockIOPool;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;


/*

 * spymemcached也自带了几个例子，大家可以看看

 * http://code.google.com/p/spymemcached/wiki/Examples

 */


class User implements Serializable{ //必须将对象序列化才能保存

	public String userName;

	public String password;

}


public class MemcachedTest{

	@Test  
    public void testMemcached(){  
        MemcachedUtils.add("hello", "world", new Date(60));  
        String hello = (String) MemcachedUtils.get("hello");  
        Assert.assertEquals("world", hello);  
        long starttime = System.currentTimeMillis();
        for(int i = 0; i < 10000000; ++i) {  
            User userBean = new User();
            userBean.password="1111";
            userBean.userName="test";
            MemcachedUtils.add("user" + i, userBean, new Date(60));  
            Object obj = MemcachedUtils.get("user" + i);  
            Assert.assertEquals(userBean, obj);  
        }  
        long endtime = System.currentTimeMillis();
        System.out.println(endtime-starttime);
    } 
}