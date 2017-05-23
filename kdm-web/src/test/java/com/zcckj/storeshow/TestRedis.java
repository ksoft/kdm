package com.zcckj.storeshow;

import com.ksoft.kdm.KdmWebApplication;
import com.ksoft.kdm.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangjianbo
 * @date 2017/5/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@SpringBootTest(classes = KdmWebApplication.class)
@EnableCaching
public class TestRedis {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private RedisService redisService;

	@Test
	public void test() throws Exception {
		// 保存字符串
        redisTemplate.opsForValue().set("aaa", "111");
		System.out.println(redisTemplate.opsForValue().get("aaa"));
		Assert.assertEquals("111", redisTemplate.opsForValue().get("aaa"));
    }

    @Test
	public void tt() throws Exception{
		redisService.set("ttt","3333");
		System.out.println(redisService.get("ttt"));
	}
}