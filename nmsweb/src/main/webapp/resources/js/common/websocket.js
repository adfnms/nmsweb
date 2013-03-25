/** Websocket connect
 * @param wsURL
 * @param receive
 * @returns {___socket0}
 */
function connect(wsURL,receive) {
	var socket;
	var host = wsURL;

	try {
		socket = new WebSocket(host);

		socket.onopen = function() {};

		socket.onmessage = function(msg) {
			if (typeof receive == "function") {
				receive(msg.data);
			}
		};

		socket.onclose = function() {};

	} catch (exception) {
		alert(exception.toString());
	}
	
	return socket;

}// End connect

/** Websocket send data
 * @param data
 */
function wsSendData(data) {

	if (data == "") {return;}
	
	try {
		socket.send(data);

	} catch (exception) {
		alert("[WS] Message Send Fail");
	}
}

/** Websocket close
 */
function wsClose() {
	
	try {
		socket.close();

	} catch (exception) {
		alert("[WS] Close Fail");
	}
}