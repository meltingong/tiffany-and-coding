package com.itwill.shop.chat.sql;

public class ChatMsgSQL {
	
	public static final String CHATMSG_INSERT = "insert into chatmsg(msgNo,msgcontent,msgsendtime,msgread,userid,room_no)\r\n"
			+ "values(chatMsg_msgNo_SEQ.nextval,?,sysdate,?,?,room_room_no_SEQ.currval)";
	public static final String CHATMSG_DELETE = "delete from chatMsg where msgNo=?";
	public static final String CHATMSG_UPDATE_DELETE_MSG = "update chatMsg set msgcontent=? where msgNo=?";
	public static final String CHATMSG_READ_UPDATE = "update chatMsg set msgread=? where roomNo=? and userId=?";
	public static final String COUNT_ALL_NOT_READ_MSG = "select count(*) from chatMsg where msgread=0";
	public static final String SELECT_ALL_NOT_READ_MSG = "select * from chatMsg where userId=?";
	public static final String COUNT_NOT_READ_MSG = "select count(*) from chatMsg where room_no=? and msgread=0 and userid=?";
	public static final String SELECT_NOT_READ_MSG = "select * from chatMsg where room_no=? and msgread=0 and userid=?";
	//public static final String SELECT_BY_MSG_NO = "";
	public static final String SELECT_CHAT_BY_ROOM_NO= "select * from chatMsg where room_no=?";
	
}
