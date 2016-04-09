package com.chart.action;

import javax.annotation.Resource;

import org.junit.Test;

import com.chart.Mapper.UserMapper;
import com.chart.bean.User;
import com.chart.myUtil.MemcachedUtils;
import com.chart.service.UserService;
import com.opensymphony.xwork2.Action;

public class testAction {
	private UserMapper userMapper;
	private UserService userService;
	private User user;
	private String testkey;
	
	@Test
	public String  test()
	{
		String key="hello";
		System.out.println("this is a test action!");
		/*user=new User();
		user.setNickname("tes22t");
		user.setPassword("tsett");
		user.setEmail("23123");
		user.setPhone("23123123");
		userMapper.insertUser(user);*/
		System.out.println(key);
		MemcachedUtils.add("key", key+" test");
		testkey = (String)MemcachedUtils.get("key");
		System.out.println(key);
		return Action.SUCCESS;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		userService = userService;
	}
	
	public String getTestkey() {
		return testkey;
	}
	public void setTestkey(String testkey) {
		this.testkey = testkey;
	}
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
