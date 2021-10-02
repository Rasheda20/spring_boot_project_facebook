const url='http://localhost/8080';
let stompClient;
let selectedUser;

function connectToChat(Uname){
	
	
	console.log("connection to chat....");
	let socket = new SockJS(url +'/user/chat');
	stompClient=Stomp.over(socket);
	stompClient.connect({}, function(frame){
	console.log("connection to "+frame);
	stompClient.subscribe("/user/topic/messages"+ Uname, function(response){
		let data = JSON.parse(response.body);
		console.log(data);
		
	});	
		
	} );
}



function sendMsg(from, text){
	stompClient.send("/user/app/chat" + selectedUser, {}, JSON.stringify({
		fromLogin:from,
		message:text
	}))
	
}