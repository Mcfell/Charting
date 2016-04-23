<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="scripts/swfobject.js"></script>
	<script type="text/javascript" src="scripts/jquery.js"></script>
	<script type="text/javascript" src="scripts/web_socket.js"></script>
	<script type="text/javascript" src="scripts/jquery.WebSocket.js"></script>
	<%
		String message_to = request.getParameter( "message_to" );
	    String message_me = request.getParameter( "message_me" );
	    request.setAttribute( "message_to" , message_to );
	    request.setAttribute( "message_me" , message_me );
	%>
<script>
	$(function ()
	{
		window.onbeforeunload = onbeforeunload_handler; 
		window.onunload = onunload_handler; 
		function onbeforeunload_handler(){ 
			//ws.close();
			return warning; 
		}
		function onunload_handler()
		{
			//alert(1);
			ws = null;
		}
	});
	var message_to = "${message_to}";
	var message_me = "${message_me}";
	//var ws = new WebSocket("ws://192.168.202.56:8080/websocket_msg/serverSocket?message_to="+message_to+"&message_me="+message_me);
	var url = "Charting/websocket?message_to="+message_to+"&message_me="+message_me;
	var ws = new $.websocket({
		protocol : "Charting/websocket",
		domain : "localhost",
		port : "8080",
		onOpen:function(event){  
            showMessage("已成功登录");  
        },  
        onError:function(event){
             alert("error:"+ event)
        },  
        onMessage:function(result){  
            receiveMessage(result);
        },
        onClose:function(event){
            ws = null;
        }
	});
	
	function send(){
        if(!ws){
            alert("已经断开聊天室");
            return;
        }
         var msg=$.trim($("#msg").val());
         if(msg==""){return;}
         ws.send(msg);
         $("#messageInput").val("").focus();;
    }
    
    function receiveMessage(result){
        showMessage(result);
    }
 
    function showMessage(msg){
		document.getElementById("chatlog").textContent += msg + "\n";
    }
</script>
  </head>
  
  <body>
    <body>
		<textarea id="chatlog" readonly style="width:500px;height:500px;"></textarea><br/>
		<input id="msg" type="text" />
		<button type="submit" id="sendButton" onClick="send()">Send!</button>
		<button type="submit" id="sendButton" onClick="closeConnect()">End</button>
	</body>
  </body>
</html>
