package com.xinguan.member.action;

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
import com.xinguan.pojo.Member;

import net.sf.json.JSONObject;

@WebServlet("/memberExistServlet")
public class memberExistServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		String stuID = request.getParameter("stuID");
		System.out.println(stuID);
		int id = 0;
		if(stuID != null && !"".equals(stuID)){
			id = Integer.parseInt(stuID);
		}
		Member member = memberdao.findMemberByID(id);
		//成员不存在
		if(member == null){
			map.put("isExsit", 0);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json.toString());
		}else{
			map.put("isExsit", 1);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
