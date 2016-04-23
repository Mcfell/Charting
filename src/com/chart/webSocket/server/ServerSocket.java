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
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.chart.bean.BatSession;
import com.chart.bean.Boal;
import com.chart.bean.C2BMessage;
import com.chart.myUtil.JsonUtil;




@WebServlet(name="serverSocket",value="/serverSocket")
@ServerEndpoint(value ="/websocket")
public class ServerSocket {
	  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");	// 日期格式化
	  private static final String BAT_PREFIX = "bat";
	  private static final int PLAYER_NUM = 2; 
	  private static final int MAX_PLAYER_NUM = 100;
	  private static final String MESSAGE_TYPE = "t";
	  private static final AtomicInteger connectionIds = new AtomicInteger(0);
	  private static final Map<String,BatSession> connections= new ConcurrentHashMap<String,BatSession>(MAX_PLAYER_NUM);
	  private static final Map<String,String[]> battle= new ConcurrentHashMap<String,String[]>(100);
	  private BatSession batsession;
	  private C2BMessage _message;
	 // private BatSession elemyBatsession;
	  
	  //采用tomcat7.0自带的websocket注解方式实现
	  @Test
	  @OnMessage  
	    public void onMessage(String message, Session session)   
	        throws IOException, InterruptedException, JSONException {
		 //message = new String("{\"t\":1,\"m\":{\"to\":88,\"all\":{\"x\":6,\"y\":281,\"vx\":2.225597484987002,\"vy\":15.90317593812953},\"chart\":null},\"date\":null}");
		   System.out.println(message);
		   _message = JsonUtil.fromJson(message, C2BMessage.class);
	       JSONObject msg = new JSONObject(message);
	        // switch(msg.getInt(MESSAGE_TYPE)){	//消息类型
	        switch(_message.getT()){ 
	        case 100: //选择对手
	        	 if(batsession.getBatNum()==null){
	     		    if(pickOne()){
	     		    	// Print the client message for testing purposes 
	     		        System.out.println("batStart: " + batsession.getBatNum());  
	     		        System.out.println(battle.get(batsession.getBatNum())[0]+" VS "+battle.get(batsession.getBatNum())[1]);
	     		        // Send the first message to the client
	     		        //SendMsgToOpponents(message);
	     		        //BordCast(GetGroupMembers());
	     		       _message = JsonUtil.fromJson(message, C2BMessage.class);
	     		       Boal a = new Boal(200,200,0,10);
	     		       _message.setA(a);
	     		       SendMsgToOpponents(JsonUtil.toJson(_message)); //初始化小球
	     		       a.setVy(-10);
	     		       SendMsgToSelf(JsonUtil.toJson(_message));//初始化小球
	     		  }else{
	     		    	System.out.println("请等待对手加入"); 
	     		    	SendMsgToSelf("请等待对手加入");
	     		   }
	     		  }else{
	     			 if(IsBattleAlive())
		        		  SendMsg(msg);
					  else
						  _message.setC(batsession.getBatNum()+" has closed");
						  SendMsgToSelf(JsonUtil.toJson(_message));
	     		  }
	        	  break;
	         case 101: //发送消息
	        	  if(IsBattleAlive())
	        		  SendMsg(msg);
				  else{
					  _message.setC(batsession.getBatNum()+" has closed");
					  SendMsgToSelf(JsonUtil.toJson(_message));
				  }
	        	  break;
	         default:break;
	         }
		  
	    }  
	    
		@OnOpen  
	    public void onOpen(Session session) {  
	    	batsession = new BatSession();
	    	batsession.setSession(session);
	    	batsession.setIsBat(false);
	    	connections.put(session.getId(),batsession);
	        System.out.println("Client:: "+session.getId()+" comming");  
	    }  
	  
	    @OnClose  
	    public void onClose (Session session) { 
	    	if(IsBattleAlive()){//error
	    		try {
					SendMsgToOpponents("battle closed:Because of "+session.getId()+" leave");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		battle.remove(this.batsession.getBatNum());
	    	}
	    	connections.remove(session.getId());
	        System.out.println("Client:: "+session.getId()+" leave");    
	    }  
	    
	    public boolean IsBattleAlive(){
	    	if(this.batsession.getBatNum()!=null) 
	    		return battle.get(this.batsession.getBatNum()) != null;
	    	else 
	    		return false;
	    }
	    //将对战双方加入战斗队列中
	    private Boolean pickOne(){
			if(connections.size()<PLAYER_NUM) return false;
			Iterator<Entry<String, BatSession>> it = connections.entrySet().iterator();
			BatSession tmps;	//对手
			BatSession[] tmp = new BatSession[PLAYER_NUM];//battle集合
			String[] bat = new String[PLAYER_NUM];//player集合
			int i = 0;			
			while(it.hasNext()){
				tmps=it.next().getValue();
				if(!tmps.getIsBat()&&!tmps.getSession().getId().equals(batsession.getSession().getId())){
					String num = BAT_PREFIX+connectionIds;
					tmps.setBatNum(num);
					tmps.setIsBat(true);
					bat[i]=tmps.getSession().getId();
					tmp[i++]=tmps;
					if(i==PLAYER_NUM-1){
						batsession.setBatNum(num);
						batsession.setIsBat(true);
						bat[i]=batsession.getSession().getId();
						tmp[i] = batsession;
						battle.put(BAT_PREFIX+connectionIds, bat);
						connectionIds.incrementAndGet();
						return true;
					}
					
				}
			}
			return false;
		  }
		  
		  private void SendMsg(JSONObject msg) throws IOException, JSONException {
					
					if(msg.getString("to")!=null)
						SendMsgToOpponents("{\"t\":101,\"m\":\""+msg.getString("to")+"\",\"mt\":11}");
					if(msg.getString("a")!=null)
						BordCast("{\"t\":101,\"m\":"+msg.getString("a")+",\"mt\":12}");
					if(msg.getString("chart")!=null)
						SendMsgToOpponents("{\"t\":101,\"m\":\""+msg.getString("c")+"\",\"mt\":13}");
			}
		   /*
		    * 向对局的所有玩家发送数据
		    */
	    public void BordCast(String msg) throws IOException{
	    	String[] batNum = battle.get(batsession.getBatNum());
	    	for(int i = 0;i<batNum.length;i++){
	    		connections.get(batNum[i]).getSession().getBasicRemote().sendText(msg);
	    	}
	    }
	    
	    public void SendMsgToOpponents(String msg) throws IOException{
	    	String[] batNum = battle.get(batsession.getBatNum());
	    	for(int i = 0;i<batNum.length;i++){
	    		if(connections.get(batNum[i])!=null &&							//仍在战斗的对手
	    				!batNum[i].equals(batsession.getSession().getId()))
	    			connections.get(batNum[i]).getSession().getBasicRemote().sendText(msg);
	    	}
	    }
	    private void SendMsgToSelf(String msg) throws IOException{
	    	batsession.getSession().getBasicRemote().sendText(msg);
	    }
	    
	    public String GetGroupMembers(){
	    	String[] batNum = battle.get(batsession.getBatNum());
	    	StringBuffer s = new StringBuffer();
	    	for(int i = 0;i<batNum.length;i++){
	    			s.append(batNum[i]);
	    			if(i<batNum.length-1)
	    				s.append(" VS　");
	    	}
	    	return s.toString();
	    }
}