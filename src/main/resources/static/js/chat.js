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
		'message': message, 
		'fromLogin': fromWho
	};
	var data = JSON.stringify(rowData);
	stompClient.send("/app/chat/" + fromWho + "/" + toWho, {}, data);
}