package com.itwill.shop.chat.service;

import java.util.List;

import com.itwill.shop.chat.dao.ChatMsgDaoImpl;
import com.itwill.shop.chat.dto.ChatMsg;
import com.itwill.shop.user.UserDao;

public class ChatMsgService {
	private ChatMsgDaoImpl chatMsgDaoImpl;
	private UserDao userDao; 
	
	public ChatMsgService() throws Exception {
		chatMsgDaoImpl = new ChatMsgDaoImpl();
		userDao = new UserDao();
	}
	
	// 메시지 생성
	public int insertChatMsg(ChatMsg chatMsg) {
		return chatMsgDaoImpl.insertChatMsg(chatMsg);
	}
	
	// 메시지 삭제 후 기존 메시지 대체
	public int updateDeletedMsg(int msgNo) {
		return chatMsgDaoImpl.updateDeletedMsg(msgNo);
	}
	
	// 메시지 삭제 (안쓸듯)
	public int deleteChatMsg(int msgNo) {
		return chatMsgDaoImpl.deleteChatMsg(msgNo);
	}
	
	// 읽은 메시지 업데이트(readcount)
	public int updateReadMsg(int roomNo, String userId) {
		return chatMsgDaoImpl.updateReadMsg(roomNo, userId);
	}
	
	// 읽지 않은 메시지 총 카운트
	public int countAllNotReadMsg(String userId) {
		return chatMsgDaoImpl.countAllNotReadMsg(userId);
	}
	
	// 읽지 않은 메시지 전체 보기
	public List<ChatMsg> selectAllNotReadMsg(String userId){
		return chatMsgDaoImpl.selectAllNotReadMsg(userId);
	}
	
	// 채팅방 1개의 읽지 않은 메시지 카운트
	public int countNotReadMsg(int roomNo, String userId) {
		return chatMsgDaoImpl.countNotReadMsg(roomNo, userId);
	}
	
	// 채팅방 1개의 읽지 않은 메시지 보기
	public List<ChatMsg> selectNotReadMsg(int roomNo, String userId){
		return chatMsgDaoImpl.selectNotReadMsg(roomNo, userId);
	}
	
	// 채팅방 1개 전체 대화보기
	public List<ChatMsg> selectChatByRoomNo(int roomNo){
		return chatMsgDaoImpl.selectChatByRoomNo(roomNo);
	}
	
	
}
