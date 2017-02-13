package com.xinguan.member.action;


import java.io.File;
import java.io.IOException;
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
import net.sf.json.JSONObject;

@WebServlet("/fileupload")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map<String,String> info = new HashMap<String,String>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
        ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if(!fileItem.isFormField()){
					
					//服务器端存储图片的绝对路径
					String savapath = request.getServletContext().getRealPath("images");
					System.out.println(savapath);

					//设置图片URL
					String image_url = request.getRequestURL().delete(request.getRequestURL().length() - request.getRequestURI().length(), request.getRequestURL().length()).append("/").toString()+"xinguan/images/";
					
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
						
						String image_name = UUID.randomUUID().toString() + "." + filetype;
						
						System.out.println("文件名称:"+fileItem.getName());
						
						fileItem.write(new File(savapath,image_name));
						
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
