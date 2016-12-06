package com.xinguan.member.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@WebServlet("/ForgetPassServlet")
public class ForgetPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = request.getParameter("token");
		System.out.println(token);
		Map<String,Integer> map_info = new HashMap<String,Integer>();
			
			if(token !=null && !"".equals(token)){
				System.out.println(request.getSession().getAttribute("flag"));
				
				if(token.equals(request.getSession().getAttribute("flag"))){
					//map.put("success", "验证码正确");
					map_info.put("forgetStatus", 1);
					System.out.println(map_info.toString());
					JSONObject json = JSONObject.fromObject(map_info);
					response.getWriter().print(json.toString());
				}else{
					//map.put("error", "验证码错误");
					map_info.put("forgetStatus", 0);
					System.out.println(map_info.toString());
					JSONObject json = JSONObject.fromObject(map_info);
					response.getWriter().print(json.toString());
				}
			}
		}
}
