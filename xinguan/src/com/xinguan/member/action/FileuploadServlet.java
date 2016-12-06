package com.xinguan.member.action;


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

@WebServlet("/fileupload")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> map_error = new HashMap<String,String>();
		// 首先检测是否是文件上传，主要依据enctype的值来判定
		String realpath = "D:/apache-tomcat-8.0.38/webapps/xinguan/images/moren.ico";
		
		if(!ServletFileUpload.isMultipartContent(request)){
			map_error.put("error", "表单 enctype属性必须为multipart/form-data");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if(!fileItem.isFormField()){
					
//					String savapath = request.getSession().getServletContext().getRealPath("images");
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
		
	}
}
