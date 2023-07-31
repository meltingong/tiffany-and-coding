package com.itwill.shop.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itwill.shop.chat.dto.ChatMsg;
import com.itwill.shop.chat.sql.ChatMsgSQL;
import com.itwill.shop.common.DataSourceFactory;

public class ChatMsgDaoImpl implements ChatMsgDao{
	
	private DataSource dataSource;
	
	public ChatMsgDaoImpl() throws Exception {
		dataSource=DataSourceFactory.getDataSource();
	}
	
	// 채팅방 1개 전체 대화보기
	@Override
	public List<ChatMsg> selectChatByRoomNo(int roomNo) {
		List<ChatMsg> chattingList = new ArrayList<ChatMsg>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.SELECT_CHAT_BY_ROOM_NO);
			pstmt.setInt(1, roomNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				chattingList.add(new ChatMsg(
								rs.getInt("msgNo"),
								rs.getString("msgContent"),
								rs.getString("msgSendTime"),
								rs.getInt("msgRead"),
								rs.getInt("room_no"),
								rs.getString("userId")
								));
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return chattingList;
	}

	@Override
	public List<ChatMsg> selectNotReadMsg(int roomNo, String userId) {
		List<ChatMsg> selectNotReadMsgByOneRoomList = new ArrayList<ChatMsg>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.SELECT_NOT_READ_MSG);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2,userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				selectNotReadMsgByOneRoomList.add(new ChatMsg(
								rs.getInt("msgNo"),
								rs.getString("msgContent"),
								rs.getString("msgSendTime"),
								rs.getInt("msgRead"),
								rs.getInt("room_no"),
								rs.getString("userId")
								));
			}
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return selectNotReadMsgByOneRoomList;
	}

	@Override
	public int countNotReadMsg(int roomNo, String userId) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.COUNT_NOT_READ_MSG);
			pstmt.setInt(1, roomNo);
			pstmt.setString(2,userId);
			rowCount = pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public List<ChatMsg> selectAllNotReadMsg(String userId) {
		List<ChatMsg> notReadMsgList = new ArrayList<ChatMsg>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.SELECT_ALL_NOT_READ_MSG);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notReadMsgList.add(new ChatMsg(
						rs.getInt("msgNo"),
						rs.getString("msgContent"),
						rs.getString("msgSendTime"),
						rs.getInt("msgRead"),
						rs.getInt("room_no"),
						rs.getString("userId")
						));
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return notReadMsgList;
	}

	@Override
	public int countAllNotReadMsg(String userId) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.COUNT_ALL_NOT_READ_MSG);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rowCount++;
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public int updateReadMsg(int roomNo, String userId) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.CHATMSG_READ_UPDATE);
			pstmt.setInt(1,1);
			pstmt.setInt(2, roomNo);
			pstmt.setString(3, userId);
			rowCount = pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public int deleteChatMsg(int msgNo) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.CHATMSG_DELETE);
			pstmt.setInt(1, msgNo);
			rowCount = pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
	//메시지 삭제 시 기존 메시지 대체
	@Override
	public int updateDeletedMsg(int msgNo) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ChatMsgSQL.CHATMSG_UPDATE_DELETE_MSG);
			pstmt.setString(1, "삭제된 메시지 입니다.");
			pstmt.setInt(2, msgNo);
			rowCount = pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public int insertChatMsg(ChatMsg chatMsg) {
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
				con = dataSource.getConnection();
				pstmt = con.prepareStatement(ChatMsgSQL.CHATMSG_INSERT);
				pstmt.setString(1, chatMsg.getMsgContent());
				pstmt.setInt(1, chatMsg.getMsgRead());
				pstmt.setString(1, chatMsg.getUserId());
				rowCount = pstmt.executeUpdate();
				pstmt.close();
				con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	
}
