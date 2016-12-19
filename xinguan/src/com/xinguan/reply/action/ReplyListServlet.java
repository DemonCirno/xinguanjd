package com.xinguan.reply.action;

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
import com.xinguan.pojo.Reply;
import com.xinguan.question.dao.IQuesDao;
import com.xinguan.question.dao.QuestionFactory;
import com.xinguan.reply.dao.IReplyDao;
import com.xinguan.reply.dao.ReplyFactory;
import com.xinguan.utils.Page;
import com.xinguan.utils.PageUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

@WebServlet("/ReplyListServlet")
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String quesID = request.getParameter("quesID");
		System.out.println("quesID:"+quesID);
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(quesID != null && !"".equals(quesID)){
			int id = 0;
			id = Integer.parseInt(quesID);
			IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
			Question ques = quesdao.findQuesByID(id);
			map.put("ques", ques);
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
			//获取当前回复页
			int currentPage = 0;
			String currentPagestr = request.getParameter("currentPage");
			System.out.println("当前回复页:"+currentPagestr);
			
			if(currentPagestr == null || "".equals(currentPagestr)){
				currentPage = 1;
			}else{
				currentPage = Integer.parseInt(currentPagestr);
			}
			IReplyDao replydao = ReplyFactory.createReplyImplInstance();
			
			Page page = PageUtil.createPage(5, replydao.countReply(id), currentPage);
			List<Reply> replylist = replydao.findReplyByPageID(id, page);
			
			map.put("replylist", replylist);
			JSONObject json = JSONObject.fromObject(map,jsonConfig);
			System.out.println(json.toString());
			response.getWriter().print(json.toString());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
