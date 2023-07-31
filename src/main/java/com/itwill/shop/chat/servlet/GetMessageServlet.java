package com.itwill.shop.chat.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class GetMessageServlet extends HttpServlet{
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // 채팅 메시지를 가져오는 로직 작성 (예를 들면, DB에서 가져오기)
	        // 임의의 메시지 목록 생성 (실제로는 DB 등에 저장된 메시지를 가져와야 합니다)
	        List<String> messages = new ArrayList<>();
	        messages.add("User1: Hello!");
	        messages.add("User2: Hi there!");
	        messages.add("User1: How are you?");
	        messages.add("User2: I'm doing well, thanks!");

	        // JSON 형태로 메시지 전송
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{ \"messages\": " + new Gson().toJson(messages) + " }");
	    }
}
