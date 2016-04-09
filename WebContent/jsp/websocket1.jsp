<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
<script type="text/javascript">
var ws = null;
function startWebSocket() {
	if ('WebSocket' in window)
		ws = new WebSocket("ws://localhost:8080/Charting/websocket");
	else if ('MozWebSocket' in window)
		ws = new MozWebSocket("ws://localhost:8080/Charting/websocket");
	else
		alert("not support");
	
	
	ws.onmessage = function(evt) {
		document.getElementById("data").innerHTML += evt.data+"\n";
	};
	
	ws.onclose = function(evt) {
		alert("close");
	};
	
	ws.onopen = function(evt) {
		alert("open");
	};
}

function sendMsg() {
	ws.send(document.getElementById('writeMsg').value);
}
</script>
</head>
<body onload="startWebSocket();">
<input type="text" id="writeMsg"></input>
<input type="button" value="send" onclick="sendMsg()"></input>
<textarea id="data"></textarea>
</body>
</html>