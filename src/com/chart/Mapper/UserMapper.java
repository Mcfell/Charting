package com.chart.Mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.chart.bean.User;

public interface UserMapper extends SqlMapper {

    public User selectUser(int id);  
   
    public void insertUser(User User);  
   
    public void updateUser(User User);  
   
    public void deleteUser(int id);  
   
    public List selectAll();  
}
