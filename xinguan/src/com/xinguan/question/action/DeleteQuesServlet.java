package com.xinguan.question.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinguan.member.dao.IMemberDao;
import com.xinguan.member.dao.MemberDaoFactory;
import com.xinguan.question.dao.IQuesDao;
import com.xinguan.question.dao.QuestionFactory;

import net.sf.json.JSONObject;


@WebServlet("/DeleteQuesServlet")
public class DeleteQuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<String,Integer>();
		String quesID = request.getParameter("quesID");
			System.out.println(quesID);
		int id = 0;
		if(quesID !=null && !"".equals(quesID)){
			id = Integer.parseInt(quesID);
		}
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		boolean flag = quesdao.deleteQuesByID(id);
		if(flag){
			map.put("deleteStatus", 1);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json.toString());
			System.out.println(json.toString());
		}else{
			map.put("deleteStatus", 0);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json.toString());
			System.out.println(json.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
