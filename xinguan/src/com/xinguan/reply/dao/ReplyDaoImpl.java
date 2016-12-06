package com.xinguan.reply.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.xinguan.pojo.Reply;
import com.xinguan.utils.JdbcUtils;
import com.xinguan.utils.Page;

public class ReplyDaoImpl implements IReplyDao {

	@Override
	public void reply(Reply reply) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String reply_insert="insert into tb_reply (replyContent,replyTime,count,memberID,quesID) values (?,?,?,?,?)";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(reply_insert);
			pst.setString(1, reply.getReplyContent());
			pst.setDate(2, reply.getReplyTime());
			pst.setInt(3, reply.getCount());
			pst.setInt(4, reply.getMemberID());
			pst.setInt(5, reply.getQuesID());
			int count = pst.executeUpdate();
			if(count>0)
				System.out.println("回复插入成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
	}

	@Override
	public int countReply(int quesID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		String count_reply = "select count(*) from tb_reply where quesID=?";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(count_reply);
			pst.setInt(1, quesID);
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
	public List<Reply> findReplyByPageID(int quesID, Page page) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String find_page_id = "select * from tb_reply where quesID=? order by replyTime desc limit ?,?";
		List<Reply> reply_list = new LinkedList<>();
		
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(find_page_id);
			pst.setInt(1, quesID);
			pst.setInt(2, page.getBeginIndex());
			pst.setInt(3, page.getEveryPage());
			rs = (ResultSet) pst.executeQuery();
			
			while(rs.next()){
				Reply reply = new Reply();
				reply.setReplyID(rs.getInt(1));
				reply.setReplyContent(rs.getString(2));
				reply.setReplyTime(rs.getDate(3));
				reply.setCount(rs.getInt(4));
				reply.setMemberID(rs.getInt(5));
				reply.setQuesID(rs.getInt(6));
				reply_list.add(reply);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtils.release(conn, pst, rs);
		}
		return reply_list;
	}

	@Override
	public Boolean updateCount(int replyID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Boolean flag = false;
		String update_count = "update tb_reply set count = count+1 where replyId =?;";
		try {
			conn = JdbcUtils.getConnection();
			pst = (PreparedStatement) conn.prepareStatement(update_count);
			pst.setInt(1, replyID);
			int count = pst.executeUpdate();
			if(count>0){
				flag = true;
				System.out.println("第"+replyID+"条回复点赞成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}


}
