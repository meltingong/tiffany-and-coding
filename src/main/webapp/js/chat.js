var num = null;
var loginName = null;
//var loginId = getLoginId();
var yourId = null;
var mImage = null;
var socket = null;

var checkSeller = null;
var yourImg = null;
var yourFreshness = null;
var product = null;
var p_img = null;

var c_room_no = null;


var last_seen_time = null;

var c_app_lat = null;
var c_app_lng = null;

var promiseData = null;

var jsonData = {
	code: null,
	url: null,
	msg: null,
	your_id: null, // 상대 아이디 
	data: null //chat_contents 

};

// 채팅방 클릭
$(document).ready(function () {
  // 채팅방 클릭 이벤트 처리
  $(document).on("click", "#chatRoomList li", function () {
    const chatRoomNo = $(this).find("input[name='chatRoomNo']").val();
   // const chatRoomName = $(this).find("input[name='chatRoomName']").val();
    //const otherName = $(this).find("input[name='to_id']").val();
    
    loadChatHistory(chatRoomNo);
  });
});

function loadChatHistory(chatRoomNo) {
  $.ajax({
    type: "GET",
    url: "GetChatHistoryServlet", // 서버 측 서블릿 또는 컨트롤러의 URL로 변경해야 합니다.
    data: { room_no: chatRoomNo 
    		//room_name : chatRoomName,
    		//to_id: otherName
    		},
    dataType: "json",
    success: function (response) {
      // 서버 응답을 처리하고 HTML에서 채팅 내역을 업데이트합니다.
      updateChatHistory(response);
       // 채팅방 이름과 채팅 상대를 업데이트합니다.
            //$("#updateRoomName").text(chatRoomName);
           // $("#updateToId").text(otherName);
    },
   error: function (xhr, status, error) {
      // 에러 메시지를 출력합니다.
      console.error("AJAX 요청이 실패했습니다. 상태 코드: " + xhr.status + ", 에러: " + error);
      alert("채팅 내역을 불러오는데 실패했습니다.");
    }
  });
}

// HTML에서 채팅 내역을 업데이트하는 함수
function updateChatHistory(chatMessages) {
  // 이전의 채팅 메시지를 chat-history div에서 제거합니다.
  $("#chat-history").empty();

  // 채팅 메시지를 순회하며 chat-history div에 추가합니다.
  for (let i = 0; i < chatMessages.length; i++) {
    const chatMsg = chatMessages[i];
    // 각 메시지를 chat-history div에 추가합니다.
    // 필요한 형식에 맞게 메시지를 포매팅합니다.
    $("#chat-history").append(
      '<div class="message my-message">' + chatMsg.msgContent + '</div>'
    );
  }
}


//메세지 전송 

function message_send_function() {
	$('#chat_content_msg').focus();

	$("#chat_content_msg").keydown(function(e) {
		
		if (e.keyCode == 13) {
			console.log('Enter');
			e.preventDefault();
			e.stopPropagation();
			if ($('#chat_content_msg').val() == "") {
				alert('내용을 입력하세요');
				$('#chat_content_msg').focus();
				return false;
			}
			console.log("send 버튼 클릭");
			timestamp = new Date().getTime();



			//제이슨데이터 만들기 
			// 임시 데이터 test

			jsonData.mId = loginId;


			/*****상대방 아이디 / 채팅방 데이터 받아와야 함  */
			jsonData.your_id = yourId;
			jsonData.msg = "메세지 전송(socket.send)";
			jsonData.code = "1";
			jsonData.data = [{
				c_content_no: "",
				c_content: $('#chat_content_msg').val(),
				send_time: "",
				c_read: "0",
				user_id: loginId,
				c_room_no: c_room_no
			}]

			console.log("json데이터만들기 끝")

			message_sendDB(jsonData);
			console.log("DB 전송")
			return false;

		}
	});

	$('#btnChatSend').click(function(e) {
		e.preventDefault();
		e.stopPropagation();
		if ($('#chat_content_msg').val() == "") {
			alert('내용을 입력하세요');
			$('#chat_content_msg').focus();
			return false;
		}
		console.log("send 버튼 클릭");
		timestamp = new Date().getTime();



		//제이슨데이터 만들기 
		// 임시 데이터 test

		jsonData.mId = loginId;


		/*****상대방 아이디 / 채팅방 데이터 받아와야 함  */
		jsonData.your_id = yourId;
		jsonData.msg = "메세지 전송(socket.send)";
		jsonData.code = "1";
		jsonData.data = [{
			c_content_no: "",
			c_content: $('#chat_content_msg').val(),
			send_time: "",
			c_read: "0",
			user_id: loginId,
			c_room_no: c_room_no
		}]

		console.log("json데이터만들기 끝")


		message_sendDB(jsonData);
		console.log("DB 전송")
		return false;


	});
	return false;
}

function message_sendDB(jsonData) {
	$.ajax({
		url: 'chat_message_rest',
		data: JSON.stringify(jsonData.data[0]), //전송 데이터

		type: "POST", //전송 타입
		async: true, //비동기 여부
		//timeout: 5000, //타임 아웃 설정

		contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
		dataType: "JSON", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			

		// [응답 확인 부분 - json 데이터를 받습니다] -보낸 클라이언트가 자기한테 받는 거 (?)
		success: function(response) {
			console.log("성공");
			console.log(" 내가 보낸 것 [requestPostBodyJson] : [response] : " + JSON.stringify(response));
			console.log("");
			jsonData.data[0].send_time = response.send_time;
			jsonData.data[0].c_content_no = response.c_content_no
			console.log(JSON.stringify(jsonData));

			socket.send(JSON.stringify(jsonData));
			console.log("socket 전송");
			//console.log("성공적인 socket 전송 여부: "+sendResult);
			$('#chat_content_msg').val("");
		},
		error: function(xhr) {
			console.log("error");
		}

	});

}



function connectWS() {
	console.log("connectWS 실행 : " + loginId)
	var url = "ws://localhost:80/brown_carrot_market/replyEcho?" + loginId + "&" + c_room_no;
	var ws = new WebSocket(url);
	socket = ws;

	ws.onopen = function() {
		console.log(loginId + '서버 연결 성공');
		jsonData.mId = loginId;


		/*****상대방 아이디 / 채팅방 데이터 받아와야 함  */
		jsonData.your_id = yourId;
		jsonData.msg = "채팅방 입장(socket.send)";
		jsonData.code = "2";
		jsonData.data = [{
			c_content_no: "",
			c_content: "",
			send_time: "",
			c_read: "0",
			user_id: loginId,
			c_room_no: c_room_no
		}]
		socket.send(JSON.stringify(jsonData));
		console.log()
		getChatNum(loginId);

	};

	ws.onerror = function(evt) {
		console.log('에러');
	}

	ws.onmessage = function(result) {
		result.stopPropagation();
		//console.log(result.data);
		//var onMsg=JSON.parse(result.data);
		console.log('메세지 얻기');
		//console.log(onMsg.data[0]);
		var onmsg = JSON.parse(result.data);
		console.log(onmsg.code);


		//메세지 전송한 경우
		if (onmsg.code == "1") {
			if (onmsg.user_id != loginId && onmsg.toastId == "youExist") {
				//상대가 메세지 보낸 경우
				console.log("상대가 보낸 경우" + onmsg.user_id)
				$('#chat_history').append(message_other(onmsg));
			} else if (onmsg.user_id != loginId) {
				toastr.options.positionClass = "toast-top-right";
				toastr['warning'](onmsg.user_id + " : " + onmsg.c_content);

				/******************채팅수증가******* */
				getChatNum(loginId);
			} else if (onmsg.user_id == loginId) {
				console.log("내가 보낸 경우" + onmsg.user_id)
				//내가 보낸 경우
				$('#chat_history').append(message_you(onmsg));
			}
			/*****************메시지 보내는 순간 리스트 새로고침***********************/

			reloadChatList();
			/****************************************************************************/

			/*************메시지 보내는 순간 메시지 포커스************/
			if ($('#chat-history').get(0).scrollHeight > 698) {
				// 세로 스크롤바가 있을 경우 처리
				$('#chat-history').css("display", "flex");
				console.log("스크롤바 있음");
			} else {
				$('#chat-history').css("display", "block");
				console.log("스크롤바 없음");

			}







		} //입장한 경우
		else if (onmsg.code == "2") {
			console.log(">>>>>>>>입장한 경우");
			var chat_detail = {
				"c_room_no": c_room_no,
				"loginId": loginId
			}

			$.ajax({


				url: "chat_detail_rest",
				method: "POST",
				data: JSON.stringify(chat_detail),
				async: true,
				contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
				dataType: "JSON", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)    			



				success: function(jsonResult) {
					var chatContentArray = jsonResult.data;
					yourId = jsonResult.yourId;
					yourImg = jsonResult.yourImg;
					console.log("채팅방의 상대방 ID:" + yourId);
					//$('#chat_history').html("");
					//$('#chatHead').html("");
					//$('#chatHead').append(chat_head(yourId,yourImg));



					for (const item of chatContentArray) {
						var chat_read = "";
						if (item.c_read == 0) {
							chat_read = "";
						} else if (item.c_read == 1) {
							chat_read = `<i class="fa fa-check"></i>`;
						}
						console.log(item.c_content_no);
						$(`#${item.c_content_no}`).html(chat_read);


					};
				}
			});
			return false;


		} else if (onmsg.code == "3") {
			console.log("약속 잡기");
			if (onmsg.user_id == 'admin') {
				$('#chat_history').append(message_admin(onmsg));
			} else {
				$('#chat_history').append(message_admin_promise(onmsg));
			}
			/*****************메시지 보내는 순간 리스트 새로고침***********************/

			reloadChatList();
			/****************************************************************************/


		}
	}



	ws.onclose = function(evt) {
		console.log('소켓 닫기');

	}
}



/*****************삭제....*************** 

$(document).on('click', '#deleteRoom', function(e) {
	console.log(c_room_no);

	var chat_room = {
		"c_room_no": c_room_no,
		"loginId": loginId
	}
	$.ajax({


		url: "chat_delete_rest",
		method: "POST",
		data: JSON.stringify(chat_room),
		async: true,
		contentType: "application/json; charset=utf-8", //헤더의 Content-Type을 설정
		dataType: "JSON", //응답받을 데이터 타입 (XML,JSON,TEXT,HTML,JSONP)  




		success: function(jsonResult) {
			var chatList = jsonResult.data;
			//	var chatContentArray=jsonResult.data;
			//	yourId=jsonResult.yourId;
			//	yourImg = jsonResult.yourImg;
			//	c_room_no=jsonResult.c_room_no;
			//	console.log("채팅방의 상대방 ID:"+yourId);
			//	console.log(chatContentArray[0]);
			//	//$('#content').html('채팅 불러오기 성공');
			//	$('#chat_history').html("");
			//	$('#chatHead').html("");
			//loginId=$('#loginId').val();
			console.log("불러오기");
			console.log(chatList);
			$('#chatRoomList').html("");
			$('#chat_history').html("");
			$('#chatHead').html("");
			// 채팅방 확장 
			$(function() {
				$("#chat").css({
					"margin-left": "280px",
					"border-left": "1px solid #eaeaea"


				});

			});
			$('#chatHead').append(chatRoomHeadGongji());
			$('#chat_history').append(chatRoomGongji());

			for (const item of chatList) {

				$('#chatRoomList').append(chatRoomListNew(item));
				$('#plist').show();

			}

		}

	});

});.*/
/************************************ 채팅방 닫기 *****************************
$(document).on('click', '#outRoom', function(e) {
	socket.close();
	$('#chat_history').html("");
	$('#chatHead').html("");
	// 채팅방 확장 
	$(function() {
		$("#chat").css({
			"margin-left": "280px",
			"border-left": "1px solid #eaeaea"


		});

	});
	$('#chatHead').append(chatRoomHeadGongji());
	$('#chat_history').append(chatRoomGongji());
	$('#plist').show();

	connectServer(loginId);


});
*/
/********************************************************************** 
function chatRoomHeadGongji() {
	return `					<div class="row">
								<div class="col-lg-12">
									<a href="javascript:void(0);" data-toggle="modal"
										data-target="#view_info"> <img
										src="img/user_profile/carrot3.png"
										alt="avatar" style="float:left;">
									</a>
									<div class="chat-about">
	
										<h6 class="m-b-0"><b>대장 토끼</b></h6>
										
										<small>자주 묻는 질문</small>
									</div>
								</div>

							</div>`
}
function chatRoomGongji() {
	return `<li class="clearfix">
									<div class="message-data"><img
											src="img/chat-img/logo_carrot.png"
											alt>
										<span class="message-data-adminGongji">흙토끼</span>
									</div>
									<div class="message my-message">
									<h6><b>기본매너</b></h6>
									<p>기본적으로 지켜야하는 매너에는 무엇이 있을까요?</p>
									<p>· 서로 존중해요. 우리 존댓말로 대화해요.<br>
									   · 모두의 시간은 소중합니다. 시간 약속을 꼭 지켜주세요.<br>
									   · 절대로 중간에 연락 끊기는 일이 없도록 해요.<br>
									   · 따듯한 감사 인사로 마무리를 지어요.<br>
									   · 늦은 시간 채팅은 부담스러울 수 있어요.<br>
									</p>
</div>
								</li>
								
								<li class="clearfix">
									<div class="message-data text-right"><img
											src="img/chat-img/logo_carrot.png"
											alt>
										<span class="message-data-adminGongji">금토끼</span>
									</div>
									<div class="message other-message float-right">
									<h6><b>이런 행동은 할 수 없어요.</b></h6>
									<p><br>
									   · 판매 금지 물품 거래.<br>
									   · 중고거래 사기 등 이웃에게 손해를 입히는 행위.<br>
									   · 허위 정보게시 등 이웃을 속이거나 기만하는 행위.<br>
									   · 불쾌감, 성적 수치심 등을 주는 행위.<br>
									   · 이웃을 배제하거나 소외시키는 행위.<br>

									</p>
</div>
								</li>`
}*/