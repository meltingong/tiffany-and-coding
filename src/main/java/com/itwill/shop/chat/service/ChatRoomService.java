package com.itwill.shop.chat.service;

import java.util.List;

import com.itwill.shop.chat.dao.ChatRoomDaoImpl;
import com.itwill.shop.chat.dto.ChatRoom;

public class ChatRoomService {
	
	private ChatRoomDaoImpl chatRoomDaoImpl;
	
	
	public ChatRoomService() throws Exception {
		chatRoomDaoImpl = new ChatRoomDaoImpl();
	}
	
	// 채팅방 생성
	public int insertChatRoom(ChatRoom chatRoom) {
		if(chatRoomDaoImpl.selectExist(chatRoom.getFrom_id(), chatRoom.getTo_id())) {
			return -1;
		}else {
			return chatRoomDaoImpl.insertChatRoom(chatRoom);
		}
	}
	
	// 채팅방 삭제
	public int deleteChatRoom(String roomName) {
		return chatRoomDaoImpl.deleteChatRoom(roomName);
	}
	
	// 채팅방 목록 보기
	public List<ChatRoom> chatRoomList(String userId){
		return chatRoomDaoImpl.chatRoomList(userId);
	}
	
	
	
}
