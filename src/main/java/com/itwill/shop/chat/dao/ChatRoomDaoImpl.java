package com.itwill.shop.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itwill.shop.chat.dto.ChatRoom;
import com.itwill.shop.chat.sql.ChatRoomSQL;
import com.itwill.shop.common.DataSourceFactory;

public class ChatRoomDaoImpl implements ChatRoomDao{
	
	private DataSource dataSource;
	
	public ChatRoomDaoImpl() throws Exception {
		dataSource=DataSourceFactory.getDataSource();
	}
	
	// 채팅방 생성
	@Override
	public int insertChatRoom(ChatRoom chatRoom) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(ChatRoomSQL.CHATROOM_INSERT);
				pstmt.setString(1,chatRoom.getRoomName());
				pstmt.setString(2, chatRoom.getFrom_id());
				pstmt.setString(3, chatRoom.getTo_id());
				rowCount = pstmt.executeUpdate();
				pstmt.close();
				con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	// 채팅방 삭제
	@Override
	public int deleteChatRoom(String room_name) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(ChatRoomSQL.CHATROOM_DELETE);
				pstmt.setString(1,room_name);
				rowCount = pstmt.executeUpdate();
				pstmt.close();
				con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	// 채팅방 목록 보기
	@Override
	public List<ChatRoom> chatRoomList(String userId) {
		List<ChatRoom> roomList = new ArrayList<ChatRoom>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatRoomSQL.CHATROOM_LIST);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				roomList.add(new ChatRoom(
						rs.getInt("room_no"),
						rs.getString("room_name")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	// 채팅방 목록 선택 기능
	@Override
	public ChatRoom selectByRoomName(String room_name) {
		
		return null;
	}
	
	// from_id, to_id 로 채팅방 찾기
	@Override
	public ChatRoom selectById(String fromId, String toId) {
		ChatRoom findRoom = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatRoomSQL.SELECT_BY_ID);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				findRoom = new ChatRoom(
							rs.getInt("room_no"),
							rs.getString("room_name"),
							rs.getString("from_id"),
							rs.getString("to_id")
						);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return findRoom;
	}
	
	// 채팅 생성 중복 체크
	@Override
	public boolean selectExist(String fromId, String toId) {
		int rowCount = 0;
		boolean exist = false; 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatRoomSQL.SELECT_EXIST);
			pstmt.setString(1, fromId);
			pstmt.setString(2, toId);
			rs = pstmt.executeQuery();
			rs.next();
			rowCount = rs.getInt("cnt");
			
			if(rowCount == 0) {
				exist = false;
				// 존재하면 true 존재하지 않으면 false
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return exist;
	}
	
}
