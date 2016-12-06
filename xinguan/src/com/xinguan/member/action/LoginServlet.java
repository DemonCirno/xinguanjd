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
 * ֻ�ܴ���type="text"�ı�
 * @author cirno
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String stuId = request.getParameter("stuID");
		System.out.println(stuId);
		String password = request.getParameter("password");
		System.out.println(password);
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Map<String,Object> map_error = new HashMap<String,Object>();
		Map<String,Object> flag = new HashMap<String,Object>();
		
		if(stuId !=null && !"".equals(stuId)){
			int id = Integer.parseInt(stuId);
			Member member = memberdao.findMemberByID(id);
			//�û�������
			if(member == null){
				//map_error.put("error", "���û�δע��");
				map_error.put("loginStatus", 0);
				JSONObject json = JSONObject.fromObject(map_error);
				response.getWriter().print(json.toString());
			//�û�����
			}else{
				//���벻Ϊ��
				if(password != null && !"".equals(password)){
					if(password.equals(member.getPassword())){
						request.getSession().setAttribute("member", member);
						//��¼�ɹ�
						flag.put("loginStatus", 2);
						JSONObject json = JSONObject.fromObject(flag);
						response.getWriter().print(json.toString());
					}
					else{
						//map_error.put("error", "�������");
						map_error.put("loginStatus",1);
						JSONObject json = JSONObject.fromObject(map_error);
						response.getWriter().print(json.toString());
					}
				}
			}
		}
	}
}
