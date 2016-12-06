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
import com.xinguan.utils.SendMessageUtil;

import net.sf.json.JSONObject;


@WebServlet("/ForgetPassSendServlet")
public class ForgetPassSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IMemberDao memberdao =MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> map_info = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		String phone = request.getParameter("phonenum");
		System.out.println(phone);
		
		if(phone !=null && !"".equals(phone)){
		//手机号是否注册
			Member member = memberdao.findMemberByPhone(phone);
					
			//手机号没有注册
				if(member == null){
					//map.put("error", "该手机号未注册");
					map_info.put("forgetSendStatus", 0);
					JSONObject json = JSONObject.fromObject(map_info);
					System.out.println(map_info.toString());
					response.getWriter().print(json.toString());
				}
			//手机号已经注册
				else{
					int flag = (int) ((Math.random()*9+1)*100000);
					map.put("name", "用户");
					map.put("token",flag+"");
					JSONObject json = JSONObject.fromObject(map);
					SendMessageUtil.sendMessage(phone, json);
					//储存验证码为flag
					request.getSession().setAttribute("flag",flag+"");
					request.getSession().setAttribute("member", member);
					//发送验证码成功
					map_info.put("forgetSendStatus", 1);
					System.out.println(map_info.toString());
					JSONObject message = JSONObject.fromObject(map_info);
					response.getWriter().print(message.toString());
				}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
