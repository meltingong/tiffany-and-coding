<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	String sUserId=(String)session.getAttribute("sUserId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel=stylesheet href="css/chattingicon.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="quickmenu">
 <%if(sUserId != null){ %>
 <a href="chat-room.jsp"><img width ="50px" height="50px" src="image/chatting.png" border="0"><br>실시간 채팅</a>
<%}else{%>
	<a href="user_login_form.jsp"><img width ="50px" height="50px" src="image/chatting.png" border="0"><br>실시간 채팅</a>
<%} %>

</div>
</body>
<script>
$(document).ready(function(){
	  var currentPosition = parseInt($(".quickmenu").css("top"));
	  $(window).scroll(function() {
	    var position = $(window).scrollTop(); 
	    $(".quickmenu").stop().animate({"top":position+currentPosition+"px"},1000);
	  });
	});
	
</script>
</html>