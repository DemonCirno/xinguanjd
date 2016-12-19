package com.xinguan.reply.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinguan.pojo.Member;
import com.xinguan.pojo.Reply;
import com.xinguan.reply.dao.IReplyDao;
import com.xinguan.reply.dao.ReplyFactory;

import net.sf.json.JSONObject;
/**
 * 发布回复
 * @author cirno
 *
 */
@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member) request.getSession().getAttribute("member");
		Map<String,Object> info = new HashMap<String,Object>();
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		//首先登陆
		if(member != null){
			
			String quesID = request.getParameter("quesID");
			System.out.println(quesID);
			String replycontent = request.getParameter("content");
			System.out.println(replycontent);
			int id = 0;
			id = Integer.parseInt(quesID);
			
			Reply reply = new Reply();
			reply.setMemberID(member.getMemberID());
			reply.setQuesID(id);
			reply.setReplyContent(replycontent);
			reply.setReplyTime(new java.sql.Date(new java.util.Date().getTime()));
			
			replydao.reply(reply);
			
			//回复成功
			info.put("replyStatus", 1);
			JSONObject json = JSONObject.fromObject(info);
			System.out.println(json.toString());
			response.getWriter().print(json.toString());
		}else{
			//用户未登录
			info.put("replyStatus", 0);
			JSONObject json = JSONObject.fromObject(info);
			System.out.println(json.toString());
			response.getWriter().print(json.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
