package com.yc.test;

import java.util.Date;

import org.json.JSONObject;

import com.chart.bean.Boal;
import com.chart.bean.C2BMessage;
import com.chart.myUtil.JsonUtil;

public class dd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String message = new String("{\"t\":1,\"m\":{\"to\":88,\"all\":{\"x\":6,\"y\":281,\"vx\":2.225597484987002,\"vy\":15.90317593812953}},\"time\":\"\"}");
		  System.out.println(message);
		  Boal b = new Boal(11.0,22.0,33,44);
		  
		  C2BMessage m = new C2BMessage(1,"123",b,"",11111);
		  String _message = JsonUtil.toJson(m);
		  System.out.println(_message);
		  String j = JsonUtil.fromJson(message, C2BMessage.class);
		  System.out.println(j);
		  
	     //  JSONObject msg = new JSONObject(message);
	}

}
