package com.itwill.shop.chat.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
	private int room_no;
	private String room_name;
	private String from_id;
	private String to_id;
	private List<ChatMsg> chatMsgList;
	
	public ChatRoom() {
		chatMsgList = new ArrayList<ChatMsg>();
	}
	
	
	public ChatRoom(int room_no, String room_name) {
		this.room_no = room_no;
		this.room_name = room_name;
		this.chatMsgList = new ArrayList<>();
	}


	public ChatRoom(int room_no, String room_name ,String from_id, String to_id) {
		super();
		this.room_no = room_no;
		this.room_name = room_name;
		this.from_id = from_id;
		this.to_id = to_id;
		this.chatMsgList = new ArrayList<>();
	}

	public String getRoomName() {
		return room_name;
	}

	public void setRoomName(String room_name) {
		this.room_name = room_name;
	}

	public List<ChatMsg> getChatMsgList() {
		return chatMsgList;
	}

	public void setChatMsgList(List<ChatMsg> chatMsgList) {
		this.chatMsgList = chatMsgList;
	}
	
	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	@Override
	public String toString() {
		return "ChatRoom [room_no=" + room_no + ", room_name=" + room_name + ", from_id=" + from_id + ", to_id=" + to_id
				+ ", chatMsgList=" + chatMsgList + "]";
	}

}
