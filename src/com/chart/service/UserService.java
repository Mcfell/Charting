package com.chart.service;

import java.util.List;

import com.chart.Mapper.UserMapper;
import com.chart.bean.User;

public interface UserService{
	public User validateLoginInfo(String email, Object md5);
	public boolean isRegisted(String value);
	User selectUser(int id);
	void insertUser(User User);
	void updateUser(User User);
	void deleteUser(int id);
	List selectAll();
}
