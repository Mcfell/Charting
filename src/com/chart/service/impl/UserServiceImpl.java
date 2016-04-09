package com.chart.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.chart.Mapper.UserMapper;
import com.chart.bean.User;
import com.chart.service.UserService;

public class UserServiceImpl implements UserService {

	public UserMapper userMapper;
	@Override
	public User validateLoginInfo(String email, Object md5) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRegisted(String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User selectUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectUser(id);
	}

	@Override
	public void insertUser(User User) {
		// TODO Auto-generated method stub
		userMapper.insertUser(User);
	}

	@Override
	public void updateUser(User User) {
		// TODO Auto-generated method stub
		userMapper.updateUser(User);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(id);
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	

	
}
