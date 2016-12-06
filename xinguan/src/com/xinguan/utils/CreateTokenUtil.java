package com.xinguan.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;

public class CreateTokenUtil {
	
	@Test
	public void sendToken(){
		
		Map<String,String> map = new HashMap<String,String>();
		int flag = (int) ((Math.random()*9+1)*100000);
		map.put("name", "”√ªß");
		map.put("token",flag+"");
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());
		
	}
}
