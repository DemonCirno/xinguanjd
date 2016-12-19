package com.xinguan.member.action;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xinguan.utils.JdbcUtils;

import net.sf.json.JSONObject;

@WebServlet("/fileupload")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties config = new Properties();
		
		Map<String,String> info = new HashMap<String,String>();
		
		//服务器端保存文件的绝对路径
		String realpath = "";
					
		// 首先检测是否是文件上传，主要依据enctype的值来判定
		if(!ServletFileUpload.isMultipartContent(request)){
			info.put("error", "表单 enctype属性必须为multipart/form-data");
			JSONObject json = JSONObject.fromObject(info);
			response.getWriter().print(json.toString());
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
        factory.setSizeThreshold(1024*1024);
        
        System.err.println(System.getProperty("java.io.tmpdir"));
        
        // 设置临时文件夹的目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if(!fileItem.isFormField()){
					
					config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("images.properties"));
					
					//服务器端存储图片的绝对路径
					String savapath = config.getProperty("savapath");
					//获取图片URL
					String image_url = config.getProperty("image_url");
					//获取文件名称
					String name = fileItem.getName(); 
					//截取文件后缀名
					String filetype = name.substring(name.lastIndexOf(".")+1);
					
					if(filetype == null || "".equals(filetype)){
//						System.out.println("上传文件不能为空!");
						info.put("error", "上传文件不能为空");
						JSONObject json = JSONObject.fromObject(info);
						response.getWriter().print(json.toString());
					}
					else{
						System.out.println(filetype);
						UUID uuid = UUID.randomUUID();
						String image_name = uuid.toString() + "." + filetype;
						realpath = savapath +"\\"+ image_name;
						System.out.println("文件名称:"+image_name);
						fileItem.write(new File(realpath));
						System.out.println(new Date());
						System.out.println("上传成功");
						info.put("image_path", image_url+image_name);
						JSONObject json = JSONObject.fromObject(info);
						response.getWriter().print(json.toString());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
