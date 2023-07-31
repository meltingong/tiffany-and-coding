package com.itwill.shop.chat.dto;

public class ChatMsg {
	private int msgNo;
	private String msgContent;
	private String msgSendTime;
	private int msgRead;
	
	/*FK*/
	private int room_no;
	private String userId;
	
	
	public ChatMsg() {
		
	}
	
	public ChatMsg(int msgNo, String msgContent, String msgSendTime, int msgRead, int room_no, String userId) {
		this.msgNo = msgNo;
		this.msgContent = msgContent;
		this.msgSendTime = msgSendTime;
		this.msgRead = msgRead;
		this.room_no = room_no;
		this.userId = userId;
	}

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgSendTime() {
		return msgSendTime;
	}

	public void setMsgSendTime(String msgSendTime) {
		this.msgSendTime = msgSendTime;
	}

	public int getMsgRead() {
		return msgRead;
	}

	public void setMsgRead(int msgRead) {
		this.msgRead = msgRead;
	}

	public int getRoom_no() {
		return room_no;
	}

	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ChatMsg [msgNo=" + msgNo + ", msgContent=" + msgContent + ", msgSendTime=" + msgSendTime + ", msgRead="
				+ msgRead + ", room_no=" + room_no + ", userId=" + userId + "]";
	}


}
