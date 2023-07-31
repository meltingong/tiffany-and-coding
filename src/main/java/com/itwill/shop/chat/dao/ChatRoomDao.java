package com.itwill.shop.chat.dao;

import java.util.List;

import com.itwill.shop.chat.dto.ChatRoom;

public interface ChatRoomDao {
	
	// 채팅방 생성
	int insertChatRoom(ChatRoom chatRoom);
	
	// 채팅방 삭제
	int deleteChatRoom(String roomName);
	
	// 채팅방 목록 보기
	List<ChatRoom> chatRoomList(String userId);
	
	// 채팅방 목록 선택 기능
	ChatRoom selectByRoomName(String roomName);
	
	// from_id, to_id 로 채팅방 찾기
	ChatRoom selectById(String fromId, String toId);
	
	// 채팅 생성 중복 체크
	boolean selectExist(String fromId, String toId);
	
	// 1개의 채팅방 안 읽은 메시지 수
	//int countNotReadinRoom(int roomNo, String userId);
	
	
}
