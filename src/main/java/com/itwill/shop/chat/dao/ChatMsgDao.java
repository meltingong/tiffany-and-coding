package com.itwill.shop.chat.dao;

import java.util.List;

import com.itwill.shop.chat.dto.ChatMsg;

public interface ChatMsgDao {
	
	// 채팅방 1개 전체 대화보기
	List<ChatMsg> selectChatByRoomNo(int roomNo);
	
	// 채팅방 메시지 1개 보기
	//ChatMsg selectByMsgNo(int msgNo);
	
	// 채팅방 1개의 읽지 않은 메세지 보기
	List<ChatMsg> selectNotReadMsg(int roomNo, String userId);
	
	// 채팅방 1개의 읽지 않은 메시지 수
	int countNotReadMsg(int roomNo, String userId);
	
	// 읽지 않은 메세지 전체 보기
	List<ChatMsg> selectAllNotReadMsg(String userId);
	
	// 읽지 않은 메세지 총 개수
	int countAllNotReadMsg(String userId);
	
	// 메세지 읽음으로 변경
	int updateReadMsg(int roomNo, String userId);
	
	// 채팅 메세지 1개 삭제
	int deleteChatMsg(int msgNo);
	
	// 채팅 메세지 삭제 시 메세지 대체
	int updateDeletedMsg(int msgNo);
	
	//채팅 생성
	int insertChatMsg(ChatMsg chatMsg);
	
	
	
}
