package com.xinguan.member.dao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.xinguan.pojo.Member;
import com.xinguan.utils.JdbcUtils;

public class MemberDaoImpl implements IMemberDao {
	
	
	@Override
	public void register(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String insert_sql = "insert into tb_members (memberName,memberStuID,memberPhone,password) values (?,?,?,?)";
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(insert_sql);
			pst.setString(1,member.getMemberName());
			pst.setInt(2, member.getMemberStuID());
			pst.setString(3, member.getMemberPhone());
			pst.setString(4, member.getPassword());
			int count = pst.executeUpdate();
			if(count>0){
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
	}

	@Override
	public Member findMemberByID(int stuId) {
		Member member = null;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String select_sql = "select * from tb_members where memberStuID = ?";
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(select_sql);
			pst.setInt(1, stuId);
			rs = (ResultSet) pst.executeQuery();
			if(rs.next()){
				member = new Member();
				member.setMemberID(rs.getInt(1));
				member.setMemberName(rs.getString(2));
				member.setMemberNickname(rs.getString(3));
				member.setMemberSex(rs.getBoolean(4));
				member.setMemberStuID(rs.getInt(5));
				member.setMemberPhone(rs.getString(6));
				member.setPassword(rs.getString(7));
				member.setImagePath(rs.getString(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn,pst, rs);
		}
		
		return member;
	}

	@Override
	public Member findMemberByPhone(String phonenum) {
		// TODO Auto-generated method stub
		Member member = null;
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String select_sql = "select * from tb_members where memberPhone = ?";
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(select_sql);
			pst.setString(1, phonenum);
			rs = (ResultSet) pst.executeQuery();
			if(rs.next()){
				member = new Member();
				member.setMemberID(rs.getInt(1));
				member.setMemberName(rs.getString(2));
				member.setMemberNickname(rs.getString(3));
				member.setMemberSex(rs.getBoolean(4));
				member.setMemberStuID(rs.getInt(5));
				member.setMemberPhone(rs.getString(6));
				member.setPassword(rs.getString(7));
				member.setImagePath(rs.getString(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn,pst, rs);
		}
		
		return member;
	}

	@Override
	public boolean resetPassword(int memberID, String password) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String reset_sql = "update tb_members set password = ? where memberID = ?";
		boolean flag = false;
		try {
			conn =JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(reset_sql);
			pst.setString(1, password);
			pst.setInt(2, memberID);
			
			int count = pst.executeUpdate();
			if(count>0){
				flag = true;
				System.out.println("更新成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtils.release(conn, pst, rs);
		}
		return flag;
	}

	@Override
	public void updateMemberInfo(int memberID, Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String update_sql ="update tb_members "
				+ "set memberNickname=?,memberSex=?,imagePath=? "
				+ "where memberID=?";
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(update_sql);
			pst.setString(1, member.getMemberNickname());
			pst.setBoolean(2, member.isMemberSex());
			pst.setString(3, member.getImagePath());
			pst.setInt(4, memberID);
			
			int count = pst.executeUpdate();
			if(count>0){
				System.out.println("更新信息成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
	}
}
