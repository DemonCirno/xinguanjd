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
 * ×¢²á
 * @author cirno
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("stuName");
		System.out.println(name);
		String stuid = request.getParameter("stuID");
		System.out.println(stuid);
		String password = request.getParameter("password");
		System.out.println(password);
		String phone = request.getParameter("stuPhone");
		System.out.println(phone);
		String token = request.getParameter("token");
		System.out.println(token);
		
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		if(token != null && !"".equals(token)){
			
			if(token.equals(request.getSession().getAttribute("flag"))){
				System.out.println(request.getSession().getAttribute("flag"));
				Member member = new Member();
				member.setMemberName(name);
				int id = 0;
				if(stuid !=null && !"".equals(stuid)){
					id = Integer.parseInt(stuid);
				}
				
				member.setMemberStuID(id);
				member.setPassword(password);
				member.setMemberPhone(phone);
				memberdao.register(member);
				
				map.put("regStatus", 1);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json.toString());
				System.out.println(json.toString());
			}else{
				//ÑéÖ¤Âë´íÎó
				map.put("regStatus", 0);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json.toString());
				System.out.println(json.toString());
			}
			
		}
		
	}
}