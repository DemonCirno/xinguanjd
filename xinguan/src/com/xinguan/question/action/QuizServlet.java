package com.xinguan.question.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.xinguan.pojo.Member;
import com.xinguan.pojo.Question;
import com.xinguan.question.dao.IQuesDao;
import com.xinguan.question.dao.QuestionFactory;

import net.sf.json.JSONObject;

@WebServlet("/QuizServlet")

public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String,Object> info = new HashMap<String,Object>();
		Map<String,String> map = new HashMap<String,String>();
		
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		
		Member member = (Member) request.getAttribute("member");
		if(member != null){
			
			String realpath = null;
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List<FileItem> fileitems = upload.parseRequest(request);
				for	(FileItem fileItem : fileitems) {
					
					if(fileItem.isFormField()){
						String name = fileItem.getFieldName();
						System.out.println(name);
						String value = fileItem.getString();
						System.out.println(value);
						map.put(name, value);
					}
					//文件
					else{
						String savapath = "D:/apache-tomcat-8.0.38/webapps/xinguan/images";
						
						System.out.println("文件保存路径："+savapath);
						System.out.println("文件类型："+fileItem.getContentType());
						System.out.println("文件名"+fileItem.getName());
						
						String name = fileItem.getName();
						String filetype = name.substring(name.lastIndexOf(".")+1);
						
						
						UUID uuid = UUID.randomUUID();
						
						realpath = savapath + File.separator + uuid.toString() + "." + filetype;
						System.out.println("文件绝对地址:"+realpath);
						
						fileItem.write(new File(realpath));
						
						System.out.println(new Date());
						System.out.println("上传成功");
						fileItem.delete();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Question ques = new Question();
			ques.setMemberID(member.getMemberID());
			ques.setMemberName(member.getMemberName());
			ques.setQuesTitle(map.get("title"));
			ques.setQuesContent(map.get("content"));
			ques.setQuesImagePath(realpath);
			ques.setQuesPublishTime(new Date());
			boolean flag = quesdao.quiz(ques);
			if(flag){
				//成功
				info.put("quizStatus", 1);
				JSONObject json = JSONObject.fromObject(info);
				response.getWriter().print(json.toString());
			}else{
				//失败
				info.put("quizStatus", 0);
				JSONObject json = JSONObject.fromObject(info);
				response.getWriter().print(json.toString());
			}
		}else{
			info.put("quizStatus", 2);
			JSONObject json = JSONObject.fromObject(info);
			response.getWriter().print(json.toString());
		}
	}
}
