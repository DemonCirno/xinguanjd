package com.xinguan.reply.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinguan.reply.dao.IReplyDao;
import com.xinguan.reply.dao.ReplyFactory;

import net.sf.json.JSONObject;
/**
 * 实现给回答点赞的功能
 * 每次访问页面时给count
 * +1s
 * 蛤蛤
 * @author cirno
 *
 */
@WebServlet("/ReplyCountServlet")
public class ReplyCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		Map<String,Integer> map = new HashMap<String,Integer>();
		String replyID = request.getParameter("replyID");
		if(replyID !=null && !"".equals(replyID)){
			int id = 0;
			id = Integer.parseInt(replyID);
			Boolean flag = replydao.updateCount(id);
			if(flag){
				//点赞成功
				map.put("countStatus", 1);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json.toString());
			}else{
				//点赞失败
				map.put("countStatus", 0);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json.toString());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
