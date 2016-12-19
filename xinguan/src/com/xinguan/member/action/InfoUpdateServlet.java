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
/**
 * 功能：修改姓名
 * 
 * @author cirno
 *
 */
@WebServlet("/InfoUpdateServlet")
public class InfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("member");
		
		if(member != null){
			//接受传入数据
			String nickname = request.getParameter("nickname");
			//打印数据到控制台
				System.out.println(nickname);
				
			String phonenum = request.getParameter("phonenum");
				System.out.println(phonenum);
				
			Member member_update = new Member();
			
			member_update.setMemberName(nickname);
			member_update.setMemberPhone(phonenum);
			
			IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
			boolean update = memberdao.updateMemberInfo(member.getMemberID(), member_update);
			
			Map<String,Integer> info = new HashMap<String,Integer>();
			
			if(update){
				info.put("updateStatus", 1);
				JSONObject json = JSONObject.fromObject(info);
				response.getWriter().print(json.toString());
				System.out.println(json.toString());
			}else{
				info.put("updateStatus", 0);
				JSONObject json = JSONObject.fromObject(info);
				response.getWriter().print(json.toString());
				System.out.println(json.toString());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
