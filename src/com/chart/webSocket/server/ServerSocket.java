package com.chart.webSocket.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import com.chart.bean.BatSession;

import net.sf.json.JSONObject;



@WebServlet(name="serverSocket",value="/serverSocket")
@ServerEndpoint(value ="/websocket")
public class ServerSocket {
	  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
	  private static final String GUEST_PREFIX = "bat";
	  private static final AtomicInteger connectionIds = new AtomicInteger(0);
	  private static final Map<String,BatSession[]> battle= new ConcurrentHashMap<String,BatSession[]>(100);
	  private static final Set<BatSession> connections = new CopyOnWriteArraySet<BatSession>();
	  private BatSession batsession;
	  //将对战双方加入战斗队列中
	  private Boolean pickOne(){
		Iterator<BatSession> it = connections.iterator();
		BatSession tmps;
		while(it.hasNext()){
			tmps=it.next();
			if(!tmps.getIsBat()&&!tmps.getSession().getId().equals(batsession.getSession().getId())){
				BatSession[] tmp = new BatSession[2];
				tmp[0]=batsession;
				tmp[1]=it.next();
				String num = GUEST_PREFIX+connectionIds;
				batsession.setBatNum(num);
				tmps.setBatNum(num);
				batsession.setIsBat(true);
				tmps.setIsBat(true);
				battle.put(GUEST_PREFIX+connectionIds, tmp);
				connectionIds.incrementAndGet();
				return true;
			}
		}
		return false;
	  }
	  //采用tomcat7.0自带的websocket注解方式实现
	  @OnMessage  
	    public void onMessage(String message, Session session)   
	        throws IOException, InterruptedException {  
	         /*JSONObject msg = JSONObject.fromObject(message);
	         switch(Integer.parseInt((String) msg.get("type"))){
	         case 0: 
	        	 pickOne();break;
	         default:break;
	         }*/
		    if(pickOne()){
		    	// Print the client message for testing purposes  
		         System.out.println("batStart: " + batsession.getBatNum());  
		         System.out.println("你的对手是："+battle.get(batsession.getBatNum())[0].getSession().getId());
		        // Send the first message to the client  
		        session.getBasicRemote().sendText("batStart: " + batsession.getBatNum()); 
		    }else{
		    	System.out.println("请等待对手加入 ");  
		    	session.getBasicRemote().sendText("请等待对手加入 ");
		    }
	         
	        
	        // Send 3 messages to the client every 5 seconds  
	        /*int sentMessages = 0;  
	        while(sentMessages < 1000000){  
	            Thread.sleep(100);  
	            session.getBasicRemote().  
	                sendText("This is an intermediate server message. Count: "   
	                    + sentMessages);  
	            sentMessages++;  
	        }  */
	          
	        // Send a final message to the client  
	        //session.getBasicRemote().sendText("This is the last server message");  
	    }  
	      
	    @OnOpen  
	    public void onOpen(Session session) {  
	    	batsession = new BatSession();
	    	batsession.setSession(session);
	    	batsession.setIsBat(false);
	    	connections.add(batsession);
	        System.out.println("Client:: "+session.getId()+" comming");  
	    }  
	  
	    @OnClose  
	    public void onClose (Session session) { 
	    	connections.remove(batsession);
	    	battle.remove(this.batsession.getBatNum());
	        System.out.println("Client:: "+session.getId()+" leave");    
	    }  
}