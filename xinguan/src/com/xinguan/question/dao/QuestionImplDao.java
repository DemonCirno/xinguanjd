package com.xinguan.question.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.xinguan.pojo.Question;
import com.xinguan.utils.JdbcUtils;
import com.xinguan.utils.Page;

public class QuestionImplDao implements IQuesDao {

	@Override
	public boolean quiz(Question ques) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql_insert = "insert into tb_question (quesTitle,quesContent,quesImagePath,quesTime,memberID,memberName) values(?,?,?,?,?,?)";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql_insert);
			
			pst.setString(1,ques.getQuesTitle());
			pst.setString(2, ques.getQuesContent());
			pst.setString(3, ques.getQuesImagePath());
			pst.setTimestamp(4, new Timestamp(ques.getQuesPublishTime().getTime()));
			pst.setInt(5, ques.getMemberID());
			pst.setString(6, ques.getMemberName());
			
			int count = pst.executeUpdate();
			if(count>0){
				flag = true;
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return flag;
	}

	@Override
	public int findAllQuesCount() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		String sql_selectAll = "select count(*) from tb_question";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql_selectAll);
			rs = (ResultSet) pst.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return count;
	}

	@Override
	public List<Question> findQuesByPage(Page page) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Question> list = new ArrayList<Question>();
		String sql_limit = "select * from tb_question order by quesTime desc limit ?,?";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql_limit);
			pst.setInt(1, page.getBeginIndex());
			pst.setInt(2, page.getEveryPage());
			rs = (ResultSet) pst.executeQuery();
			while(rs.next()){
				Question ques = new Question();
				
				ques.setQuesID(rs.getInt(1));
				ques.setQuesTitle(rs.getString(2));
				ques.setQuesContent(rs.getString(3));
				ques.setQuesImagePath(rs.getString(4));
				ques.setQuesPublishTime(rs.getDate(5));
				ques.setMemberID(rs.getInt(6));
				ques.setMemberName(rs.getString(7));
				list.add(ques);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return list;
	}

	@Override
	public List<Question> findQuesNew() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Question> list = new ArrayList<Question>();
		String sql_limit = "select * from tb_question order by quesTime desc limit 0,8";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql_limit);
			
			rs = (ResultSet) pst.executeQuery();
			while(rs.next()){
				Question ques = new Question();
				
				ques.setQuesID(rs.getInt(1));
				ques.setQuesTitle(rs.getString(2));
				ques.setQuesContent(rs.getString(3));
				ques.setQuesImagePath(rs.getString(4));
				ques.setQuesPublishTime(rs.getDate(5));
				ques.setMemberID(rs.getInt(6));
				ques.setMemberName(rs.getString(7));
				list.add(ques);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return list;
	}

	@Override
	public Question findQuesByID(int quesID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Question ques = null;
		String find_ques = "select * from tb_question where quesID=?";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(find_ques);
			pst.setInt(1, quesID);
			rs = (ResultSet) pst.executeQuery();
			if(rs.next()){
				ques = new Question();
				ques.setQuesID(rs.getInt(1));
				ques.setQuesTitle(rs.getString(2));
				ques.setQuesContent(rs.getString(3));
				ques.setQuesImagePath(rs.getString(4));
				ques.setQuesPublishTime(rs.getDate(5));
				ques.setMemberID(rs.getInt(6));
				ques.setMemberName(rs.getString(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ques;
	}

	@Override
	public List<Question> findQuesListByMemberID(int memberID) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 
		Question ques = null;
		String find_ques_list = "select * from tb_question where memberID =? limit 0,5";
		List<Question> list = new ArrayList<Question>();
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(find_ques_list);
			pst.setInt(1, memberID);
			rs =(ResultSet) pst.executeQuery();
			while(rs.next()){
				ques = new Question();
				ques.setQuesID(rs.getInt(1));
				ques.setQuesTitle(rs.getString(2));
				ques.setQuesContent(rs.getString(3));
				ques.setQuesImagePath(rs.getString(4));
				ques.setQuesPublishTime(rs.getDate(5));
				ques.setMemberID(rs.getInt(6));
				ques.setMemberName(rs.getString(7));
				list.add(ques);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Question> findQuesListByMemberID_Page(int memberID, Page page) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Question> list = new ArrayList<Question>();
		String sql_limit = "select * from tb_question where memberID = ? order by quesTime desc limit ?,?";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(sql_limit);
			pst.setInt(1, memberID);
			pst.setInt(2, page.getBeginIndex());
			pst.setInt(3, page.getEveryPage());
			rs = (ResultSet) pst.executeQuery();
			while(rs.next()){
				Question ques = new Question();
				
				ques.setQuesID(rs.getInt(1));
				ques.setQuesTitle(rs.getString(2));
				ques.setQuesContent(rs.getString(3));
				ques.setQuesImagePath(rs.getString(4));
				ques.setQuesPublishTime(rs.getDate(5));
				ques.setMemberID(rs.getInt(6));
				ques.setMemberName(rs.getString(7));
				list.add(ques);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return list;
	}

	@Override
	public boolean deleteQuesByID(int quesID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		String delete_sql = "delete from tb_question where quesID=?";
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(delete_sql);
			pst.setInt(1, quesID);
			int count = pst.executeUpdate();
			if(count>0){
				System.out.println("删除成功");
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
