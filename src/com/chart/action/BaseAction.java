package com.chart.action;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable{
/**
 * Field-driven方式：直接在Action类中定义各种Java基本类型(包装类型)、String、Date类型的属性，
 * 					这些属性名和页面表单输入域的名字一一对应。
 * Model-driven方式：通过领域模型JavaBean来进行数据传递。在Action类中定义一个领域模型JavaBean的对象，
 * 					JavaBean所封装的属性与页面表单输入域的名字一一对应。把页面表单的数据直接封装到这个JavaBean中。
 */
	public T model;
	public BaseAction() {
		try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class) type.getActualTypeArguments()[0];
            model = (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return null;
	}
   

}
