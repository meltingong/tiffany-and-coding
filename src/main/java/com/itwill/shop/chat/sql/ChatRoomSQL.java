package com.itwill.shop.chat.sql;

public class ChatRoomSQL {
	
	public static final String CHATROOM_INSERT = "insert into room(room_no,room_name,from_id,to_id)\r\n"
			+ "values(room_room_no_SEQ.nextval,?,?,?)";
	public static final String CHATROOM_DELETE = "delete from room where room_name =?";
	public static final String CHATROOM_LIST = "select r.room_no, r.room_name,c.msgcontent from room r join chatMsg c on c.room_no = r.room_no where c.userid=?";
	public static final String SELECT_BY_ROOM_NAME = "select * from room where room_name=?";
	public static final String SELECT_BY_ID = "select * from room where room.from_id=? and room.to_id=?";
	public static final String SELECT_EXIST= "select count(*) cnt from room where room.from_id=? and room.to_id=?";
	//public static final String COUNT_NOT_READ_IN_ROOM = "";
	
	
	
	
}
