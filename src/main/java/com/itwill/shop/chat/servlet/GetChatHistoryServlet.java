package com.itwill.shop.chat.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itwill.shop.chat.dto.ChatMsg;
import com.itwill.shop.chat.service.ChatMsgService;

@SuppressWarnings("serial")
@WebServlet("/GetChatHistoryServlet")
public class GetChatHistoryServlet extends HttpServlet{
	
	private ChatMsgService chatMsgService;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int room_no = Integer.parseInt(request.getParameter("room_no"));
	    // ChatMsgService를 호출하여 채팅방 번호에 해당하는 채팅 메시지를 가져옵니다.
	    List<ChatMsg> chatMessages = chatMsgService.selectChatByRoomNo(room_no);
	    
	    // 채팅 메시지 목록을 JSON으로 변환합니다.
	    Gson gson = new Gson();
	    String json = gson.toJson(chatMessages);
	    
	    // JSON 형식의 응답을 설정하고 전송합니다.
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	  }
}