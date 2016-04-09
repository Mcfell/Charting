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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@WebServlet(name="serverSocket",value="/serverSocket")
@ServerEndpoint(value ="/websocket")
public class ServerSocket {
	  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
	  //采用tomcat7.0自带的websocket注解方式实现
	  @OnMessage  
	    public void onMessage(String message, Session session)   
	        throws IOException, InterruptedException {  
	          
	        // Print the client message for testing purposes  
	        System.out.println("Received: " + message);  
	          
	        // Send the first message to the client  
	        session.getBasicRemote().sendText("This is the first server message");  
	        Set<Session> allss= session.getOpenSessions();
	        Iterator<Session> it = allss.iterator();
	        while(it.hasNext()){
	        	session.getBasicRemote().  
                sendText("This is an intermediate server message. Session: "   
                    + it.next().getId()); 
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
	        session.getBasicRemote().sendText("This is the last server message");  
	    }  
	      
	    @OnOpen  
	    public void onOpen () {  
	        System.out.println("Client connected");  
	    }  
	  
	    @OnClose  
	    public void onClose () {  
	        System.out.println("Connection closed");  
	    }  
}