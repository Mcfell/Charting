package com.chart.action;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.chart.Mapper.UserMapper;
import com.chart.bean.User;
import com.chart.myUtil.DataUtil;
import com.chart.myUtil.ValidateUtil;
import com.chart.service.UserService;
/**
 * 注册action
 */
public class RegAction extends BaseAction<User> {
   
	private User model = new User();
    private String confirmPassword ; //确认密码字段
    //注入userService
    public UserMapper userMapper;
    
    
	public UserMapper getUserMapper() {
		return userMapper;
	}
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
	public User getModel() {
        return model;
    }
    //到达注册页面
    @SkipValidation
    public String toRegPage(){
        return "regPage" ;
    }
    //进行用户注册
    public String doUserReg(){
        //密码加密
    	System.out.println("register:"+model.getNickname());
    	userMapper.insertUser(model);
        System.out.println("register:"+model.getNickname());
        return SUCCESS ;
    }
    //校验
    public void validate() {
        //1.非空
        if(!ValidateUtil.isValid(model.getEmail())){
            addFieldError("email", "email是必填项!");
        }
        if(!ValidateUtil.isValid(model.getPassword())){
            addFieldError("password", "password是必填项!");
        }
        if(!ValidateUtil.isValid(model.getNickname())){
            addFieldError("nickname", "nickName是必填项!");
        }
        if(hasErrors()){
            return ;
        }
        /*//2.密码一致性
        if(!model.getPassword().equals(confirmPassword)){
            addFieldError("password", "密码不一致!");
            return  ;
        }*/
       /* //3.email占用
       if(userService.isRegisted(model.getEmail())){
            addFieldError("email", "email已占用!");
        }
     //3.email占用
       if(userService.isRegisted(model.getPhone())){
            addFieldError("phone", "phone已占用!");
        }*/
    }
    @Test
	public void dd(){
		  String specialStr = "<div id=\"testDiv\">test1;test2</div>"; 
	      String str1 = HtmlUtils.htmlEscape(specialStr);// ①转换为HTML转义字符表示 
	      System.out.println(str1); 
	     
	      String str2 = HtmlUtils.htmlEscapeDecimal(specialStr);// ②转换为数据转义表示 
	      System.out.println(str2); 
	     
	      String str3 = HtmlUtils.htmlEscapeHex(specialStr); //③转换为十六进制数据转义表示 
	      System.out.println(str3); 
	     
	     // ④下面对转义后字符串进行反向操作 
	      System.out.println(HtmlUtils.htmlUnescape(str1)); 
	      System.out.println(HtmlUtils.htmlUnescape(str2)); 
	      System.out.println(HtmlUtils.htmlUnescape(str3)); 
	      
	      String sql="'1' or '1'='1";  
	      //  System.out.println("防SQL注入:"+StringEscapeUtils.escapeSql(sql)); //防SQL注入  
	          
	        System.out.println("转义HTML,注意汉字:"+StringEscapeUtils.escapeHtml3("<font>chen磊  xing</font>"));    //转义HTML,注意汉字  
	        System.out.println("反转义HTML:"+StringEscapeUtils.unescapeHtml3("<font>chen磊  xing</font>"));  //反转义HTML  
	          
	        System.out.println("转成Unicode编码："+StringEscapeUtils.escapeJava("陈磊兴"));     //转义成Unicode编码  
	          
	        System.out.println("转义XML："+StringEscapeUtils.escapeXml("<name>陈磊兴</name>"));   //转义xml  
	        System.out.println("反转义XML："+StringEscapeUtils.unescapeXml("<name>陈磊兴</name>"));    //转义xml  
	}
}