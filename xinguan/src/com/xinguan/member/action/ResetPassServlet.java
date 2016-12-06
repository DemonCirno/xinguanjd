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

@WebServlet("/ResetPassServlet")
public class ResetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
		String password_new = request.getParameter("password_new");
		System.out.println(password_new);
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> info = new HashMap<String,Object>();
		Member member = (Member)request.getSession().getAttribute("member");
		boolean flag = false;
		if(member !=null ){
			if(password_new != null && !"".equals(password_new)){
				System.out.println(member.getMemberID());
				flag = memberdao.resetPassword(member.getMemberID(), password_new);	
				if(flag){
					//重置成功
					info.put("resetStatus", 1);
					JSONObject json = JSONObject.fromObject(info);
					response.getWriter().print(json.toString());
				}else{
					//重置失败
					info.put("resetStatus", 2);
					JSONObject json = JSONObject.fromObject(info);
					response.getWriter().print(json.toString());
				}
			}
		}
	}
}