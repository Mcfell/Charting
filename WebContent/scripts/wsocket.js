var screen =null;
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
var ws = new $.websocket({
	protocol : "Charting/websocket",
	domain : "localhost",
	port : "8080",
	onOpen:function(event){  
       // showMessage("已成功登录");  
		
    },  
    onError:function(event){
         alert("error:"+ event)
    },  
    onMessage:function(result){  
       // receiveMessage(result);
    	console.log("receive:"+result)
		var data = JSON.parse(result)
		if(data.t==100){
			screen = new Screen("gameSpace",{ballsnum:1, spring:0.5, bounce:-0.9, gravity:0.01,diameter:10},data.a);
			screen.initialize();
		}else if(data.t==101){
			document.getElementById("data").textContent = result+"\n";
			switch(data.mt){
				case 11:getFlag("elemy").style.left = data.m+"px";break;
				default:break;
			}
			//getFlag("elemy").style.left = evt.data+"px";
		}
    },
    onClose:function(event){
        ws = null;
    }
});

/*function startWebSocket() {
	if ('WebSocket' in window)
		ws = new WebSocket("ws://localhost:8080/Charting/websocket");
	else if ('MozWebSocket' in window)
		ws = new MozWebSocket("ws://localhost:8080/Charting/websocket");
	else
		alert("not support");
	
	ws.onmessage = function(evt) {
		console.log("receive:"+evt.data)
		var data = JSON.parse(evt.data)
		if(data.t==100){
			screen = new Screen("gameSpace",{ballsnum:1, spring:0.5, bounce:-0.9, gravity:0.01,diameter:10},data.a);
			screen.initialize();
		}else if(data.t==101){
			document.getElementById("data").textContent = evt.data+"\n";
			getFlag("elemy").style.left = evt.data+"px";
		}
		
	};
	
	ws.onclose = function(evt) {
		//alert("close");
	};
	
	ws.onopen = function(evt) {
		
	};
}
startWebSocket()
*/

function sendMsg(msg) {
	ws.send(msg);
}
function startGame(){
	ws.send("{'t':100}");
}
function endGame(){
	ws.close();
}
