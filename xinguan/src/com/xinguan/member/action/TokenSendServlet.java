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

@WebServlet("/TokenSendServlet")
public class TokenSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String phone = request.getParameter("stuPhone");
		System.out.println(phone);
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map_info = new HashMap<String,Object>();
		if(phone != null && !"".equals(phone)){
		Member member = memberdao.findMemberByPhone(phone);
		
		int tokennum = 0;
		
		if(member != null){
			//�ֻ�����ע��
			map_info.put("phoneExsit", 0);
			JSONObject json = JSONObject.fromObject(map_info);
			response.getWriter().print(json.toString());
		}else{
			
			map_info.put("phoneExsit", 1);
			JSONObject json = JSONObject.fromObject(map_info);
			response.getWriter().print(json.toString());
			tokennum = (int) ((Math.random()*9+1)*100000);
			
			map.put("name", "�û�");
			map.put("token",tokennum+"");
			JSONObject json1 = JSONObject.fromObject(map);
			
			//���÷��Ͷ��ŵķ���
			SendMessageUtil.sendMessage(phone, json1);
			
			//�����ɵ����������session���У��ȴ��ȶ�
			request.getSession().setAttribute("flag",tokennum+"");
			System.out.println("���ͳɹ�");
		}
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
