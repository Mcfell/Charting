package com.chart.action;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.chart.bean.User;
import com.chart.myUtil.DataUtil;
import com.chart.service.UserService;
/**
 * 登陆action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {
    @Resource
    private UserService userService ;
    //接受session的map
    private Map<String,Object> sessionMap ;
    //到达登陆页面
    public String toLoginPage(){
        return "loginPage" ;
    }
    //进行登陆处理
    public String doLogin(){
        return "success";
    }
    //校验登陆信息
    public void validateDoLogin(){
        User user = userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
        if(user == null){
            addActionError("email/password错误");
        }
        else{
            sessionMap.put("user", user);
        }
    }
    //注入session的map
    public void setSession(Map<String, Object> arg0) {
        this.sessionMap = arg0 ;
    }
}
