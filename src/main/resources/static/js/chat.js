const url = '/gs-guide-websocket';
var stompClient;

function connect() {
	
	console.log('connectin to chat...')
	var socket = new SockJS(url);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('connect to: ' + frame);
		stompClient.subscribe('/topic/greetings', function (response) {
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

function send() {

	var rowData = {
		'message': 'hello', 
		'fromLogin': 'eid928'
	};
	var data = JSON.stringify(rowData);
	stompClient.send("/app/hello", {}, data);
}