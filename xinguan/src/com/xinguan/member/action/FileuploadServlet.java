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
		
		//�������˱����ļ��ľ���·��
		String realpath = "";
					
		// ���ȼ���Ƿ����ļ��ϴ�����Ҫ����enctype��ֵ���ж�
		if(!ServletFileUpload.isMultipartContent(request)){
			info.put("error", "�� enctype���Ա���Ϊmultipart/form-data");
			JSONObject json = JSONObject.fromObject(info);
			response.getWriter().print(json.toString());
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
        factory.setSizeThreshold(1024*1024);
        
        System.err.println(System.getProperty("java.io.tmpdir"));
        
        // ������ʱ�ļ��е�Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
				if(!fileItem.isFormField()){
					
					config.load(JdbcUtils.class.getClassLoader().getResourceAsStream("images.properties"));
					
					//�������˴洢ͼƬ�ľ���·��
					String savapath = config.getProperty("savapath");
					//��ȡͼƬURL
					String image_url = config.getProperty("image_url");
					//��ȡ�ļ�����
					String name = fileItem.getName(); 
					//��ȡ�ļ���׺��
					String filetype = name.substring(name.lastIndexOf(".")+1);
					
					if(filetype == null || "".equals(filetype)){
//						System.out.println("�ϴ��ļ�����Ϊ��!");
						info.put("error", "�ϴ��ļ�����Ϊ��");
						JSONObject json = JSONObject.fromObject(info);
						response.getWriter().print(json.toString());
					}
					else{
						System.out.println(filetype);
						UUID uuid = UUID.randomUUID();
						String image_name = uuid.toString() + "." + filetype;
						realpath = savapath +"\\"+ image_name;
						System.out.println("�ļ�����:"+image_name);
						fileItem.write(new File(realpath));
						System.out.println(new Date());
						System.out.println("�ϴ��ɹ�");
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
