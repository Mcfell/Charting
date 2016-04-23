<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Index</title>
<base href="<%=basePath%>">	
<script type="text/javascript" src="scripts/swfobject.js"></script>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/web_socket.js"></script>
<script type="text/javascript" src="scripts/jquery.WebSocket.js"></script>
<script type="text/javascript" src="scripts/wsocket.js"></script>
</head>
<body>
<button type="button" value="start game" onclick="startGame()">start game</button>
<button type="button" value="start game" onclick="endGame()">stop Game</button>
<%@include file="game.html" %>
<input type="text" id="writeMsg"></input>
<textarea id="data" readonly style="width:300px;height:500px;"></textarea>
</body>
</html>