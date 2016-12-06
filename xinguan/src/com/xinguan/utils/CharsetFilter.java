package com.xinguan.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="CharsetFilter",urlPatterns="/*")
public class CharsetFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		//��仰����˼�������������utf8���������ص�����  
		res.setHeader("Content-type", "text/html;charset=UTF-8");  
		//��仰����˼�Ǹ���servlet��UTF-8ת�룬��������Ĭ�ϵ�ISO8859  
		res.setCharacterEncoding("UTF-8");  
		chain.doFilter(request, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
