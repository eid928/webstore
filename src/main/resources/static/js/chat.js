const url = '/websocket';
var stompClient;
var fromWho;

function connect() {
	
	console.log('connectin to chat...')
	var socket = new SockJS(url);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('connect to: ' + frame);
		fromWho = frame.headers["user-name"];
		getMessageHistory();
		stompClient.subscribe('/topic/' + fromWho, function (response) {
			let data = JSON.parse(response.body);
			console.log(response);
			console.log(data);
			document.getElementById("messageContent").innerHTML += 
				'<div class="messageReceive">' +
					'<h6>' + data.fromUser.username + ': </h6>' +
					'<h6 style="margin: 0px;">' + data.content + '</h6>' +
            	'</div>';
		})
	});
}

function disconnect() {

    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function getMessageHistory() {
	
	uri = 'http://localhost:8080/chatmessages?fromUsername=' + fromWho +
											'&toUsername=' + toWho;


	$.ajax({
		type: "GET", 
		url: uri, 
		dataType: "json",
		success: function(result) {
			console.log(result);
			document.getElementById('messageContent').innerHTML = '';
			for(var i = 0; i < result.length; i ++) {
				if(result[i].fromUser.username === fromWho) {
					document.getElementById('messageContent').innerHTML +=
							'<div class="messageSend">' +
								'<h6>' + result[i].fromUser.username + ': </h6>' +
								'<h6 style="margin: 0px;">' + result[i].content + '</h6>' +
							'</div>';
				} else {
					document.getElementById("messageContent").innerHTML += 
							'<div class="messageReceive">' +
								'<h6>' + result[i].fromUser.username + ': </h6>' +
								'<h6 style="margin: 0px;">' + result[i].content + '</h6>' +
							'</div>';
				}
			}
			messageContent.scrollTop = messageContent.scrollHeight;
		}, 
		error: function() {
			alert("fail");
		}
	});
}

function send(message, toWho) {

	if (message === '') {
		return;
	}

	var rowData = {
		'fromUser': {
			'username': fromWho
		}, 
		'toUser': {
			'username': toWho
		},
		'content': message
	};
	var data = JSON.stringify(rowData);
	stompClient.send("/app/chat/" + toWho, {}, data);
	document.getElementById('messageContent').innerHTML +=
		'<div class="messageSend">' +
            '<h6>' + fromWho + ': </h6>' +
            '<h6 style="margin: 0px;">' + message + '</h6>' +
		'</div>';
	messageContent.scrollTop = messageContent.scrollHeight;
}