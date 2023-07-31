
<%@page import="com.itwill.board.Board"%>
<%@page import="com.itwill.board.BoardService"%>
<%@page import="com.itwill.shop.user.User"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//세션의 로그인 정보 
String sUserId = (String)session.getAttribute("sUserId");
User sUser = (User)session.getAttribute("sUser");

//로그인 전이면 로그인 창으로 
if(sUserId == null){
	out.println("<script>");
	out.println("alert('로그인 후 글 보기가 가능합니다.');");
	out.println("location.href='user_login_form.jsp';");
	out.println("</script>");
}


int boardno = Integer.parseInt(request.getParameter("boardno"));
int pageno = Integer.parseInt(request.getParameter("pageno"));

// 게시판 쓴 회원정보 (아이디, 비밀번호)
BoardService boardService = new BoardService();
Board board = boardService.findBoard(boardno);
String boardUserId = board.getUserId();

UserService userService = new UserService();
User boardUser = userService.findUser(boardUserId);

String passStr = boardUser.getPassword();

%>	
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<title>Tiffany&Co</title>

<style type="text/css">
#wrap {
	width: 100%;
	text-align: center;
	margin: 0 auto 0 auto;
}

#chk {
	text-align: center;
}

#cancelBtn {
	visibility: visible;
}

#useBtn {
	visibility: visible;
}
</style>

<script type="text/javascript">
/*
 입력한 비밀번호와 게시판을 쓴 회원의 비밀번호 일치시 해당 글 보여주기,
 불일치 시 비밀번호가 일치하지 않습니다 메세지
 */

 
	function boardPassCheck() {
		let boardPass = document.getElementById('boardpass').value;
		if (boardPass == <%=passStr%>) {
			location.href = 'board_view.jsp?boardno=<%=boardno%>&pageno=<%=pageno%>';
		} else {
			alert('비밀번호가 일치하지 않습니다.');
		}

	}
</script>

</head>
<body>
	<div id="wrap">
		<br> <b><font size="3" color="gray">비밀번호 확인</font></b>
		<p></p>
		<hr size="0.5" style="margin: 0 0 0 0"  >
		<div id="chk" style="margin-top: 30px">
		<p></p>
		<p><font size="1">회원가입시 설정한 비밀번호를 입력해주세요.</font></p>
			<form name='checkForm' id="checkForm" style="display: inline;">
				<input type="hidden" name="board" id="board"  value="<%=boardno%>"> 
				<input type="hidden" name="page" id="page"  value="<%=pageno%>"> 
				<!-- 비밀번호 입력 창 -->
				<input type="text" name="boardpass" id="boardpass" width="30px" > 
			<p></p>
			<p></p>
			<p></p>
				<input id="useBtn" type="button" value="사용"  style="font-size: 10pt" onclick="boardPassCheck();">
			</form>	
				&nbsp;&nbsp;&nbsp;
				<input id="cancelBtn" type="button" value="취소"  style="font-size: 10pt" onclick="history.back();"> 
		</div>
	</div>
</body>
</html>