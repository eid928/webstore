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

function send(message, toWho) {

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
}