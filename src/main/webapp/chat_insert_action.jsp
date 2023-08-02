<%@page import="com.itwill.board.Board"%>
<%@page import="com.itwill.shop.chat.service.ChatRoomService"%>
<%@page import="com.itwill.shop.chat.dto.ChatRoom"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="login_check.jspf" %>
<%

	ChatRoom chatRoom = new ChatRoom();
	ChatRoomService chatRoomService = new ChatRoomService();
	Board board = new Board();
	chatRoom.setRoomName(board.getUserId());
	chatRoom.setFrom_id(sUserId);

%>