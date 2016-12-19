package com.xinguan.question.action;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinguan.pojo.Question;
import com.xinguan.question.dao.IQuesDao;
import com.xinguan.question.dao.QuestionFactory;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


@WebServlet("/NewMemQuestionServlet")
public class NewMemQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,Object> info = new HashMap<String,Object>();	
		String quesID = request.getParameter("quesID");
		int id = 0;
		if(quesID != null){
			id = Integer.parseInt(quesID);
		}
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		List<Question> list = quesdao.findQuesListByMemberID(id);
		info.put("list", list);
		/*
		 * 由于日期问题
		 */
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor(){
			
			String path = "yyyy-MM-dd";
			public Object processArrayValue(Object value, JsonConfig arg1) {
				// TODO Auto-generated method stub
				return process(value);
			}

			@Override
			public Object processObjectValue(String arg0, Object value, JsonConfig arg2) {
				// TODO Auto-generated method stub
				return process(value);
			}
			private Object process(Object value){
				if (value instanceof Date){
					 SimpleDateFormat sdf = new SimpleDateFormat(path,
		                        Locale.UK);
					 return sdf.format((Date) value);
				}
				return value == "" ? "" : value.toString();
			}
		});
		/**
		 *
		 */
		JSONObject json = JSONObject.fromObject(info,jsonConfig);
		System.out.println(json.toString());
		response.getWriter().print(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
