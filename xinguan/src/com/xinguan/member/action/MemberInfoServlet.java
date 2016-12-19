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


@WebServlet("/MemberInfoServlet")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("member");
		
		Map<String,Member> info = new HashMap<String,Member>();
		
		if(member != null){
			
			IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
			
			System.out.println(member.getMemberID());
			
			Member mem = memberdao.findMemberByMemID(member.getMemberID());

//			Member mem = memberdao.findMemberByMemID(1);
			info.put("member", mem);
			
			JSONObject json = JSONObject.fromObject(info);
			response.getWriter().print(json.toString());
			System.out.println(json.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
