<%@page import="com.itwill.board.Board"%>
<%@page import="com.itwill.board.BoardService"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@page import="com.itwill.shop.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf" %>  
<%
	if(request.getMethod().equalsIgnoreCase("get")){
		response.sendRedirect("board_list.jsp");
		return;
	}

	String boardpass = request.getParameter("boardpass");
	String boardno = request.getParameter("boardno");
	String pageno = request.getParameter("pageno");
	
	UserService userService = new UserService();
	User user = userService.findUser(sUserId);
	
	// 해당 게시판 찾기
	BoardService boardService = new BoardService();
	Board board = boardService.findBoard(Integer.parseInt(boardno));
	
	String isSecret = board.getSecret();
	if(isSecret == null){
		isSecret = " ";
	}
	
	/*
	회원의 비밀번호와 입력받은 비밀번호 일치 시 해당 글 보여주기,
	일치하지 않을 시 board_list.jsp로 이동
	*/
	
	String passStr = user.getPassword();
	
	if(passStr.equals(boardpass)){
		response.sendRedirect("board_view.jsp?boardno="+boardno+"&pageno="+pageno);
	}else{
		response.sendRedirect("board_list.jsp");
	}
	
	
%>















