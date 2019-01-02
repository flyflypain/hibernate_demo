var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#notice").html("");
}

function connect() {
	var fromUser = $("#from").val();
	var websocket = new WebSocket("ws://localhost:8080/demo/chat/"+fromUser);
	websocket.onopen = function () {
        console.log("connected to chat server");
    }
}


//断开连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//发送聊天记录
function sendContent() {
    stompClient.send("/app/v6/chat", {}, JSON.stringify({'content': $("#content").val()}));
    
}

//显示聊天记录
function showContent(body) {
    $("#record").append("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleTimeString()+"</td></tr>");
}

//显示实时在线用户
function showOnlieUser(body) {
    $("#online").html("<tr><td>" + body.content + "</td> <td>"+new Date(body.time).toLocaleTimeString()+"</td></tr>");
}


$(function () {
    
	connect();//自动上线
	
	$("form").on('submit', function (e) {
        e.preventDefault();
    });
     
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() {
    	sendContent(); 
    });
});

