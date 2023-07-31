package com.itwill.shop.chat.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessageServlet extends HttpServlet{
	   private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String message = request.getParameter("message");
	        // 채팅 메시지를 처리하고 저장하는 로직 작성 (예를 들면, DB에 저장)
	        // ...
	        
	        // HTTP 응답 코드 200 (OK) 전송
	        response.setStatus(HttpServletResponse.SC_OK);
	    }
}

