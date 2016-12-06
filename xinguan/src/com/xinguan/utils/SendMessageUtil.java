package com.xinguan.utils;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import net.sf.json.JSONObject;


public class SendMessageUtil {
	
	public static void sendMessage(String phone,JSONObject json){
		
		TaobaoClient client = new DefaultTaobaoClient("https://eco.taobao.com/router/rest", "23542030", "d61caf1bfaa9a64a9ce68f4f2aab5f9a");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		//ǩ��
		req.setSmsFreeSignName("�Ź�APP");
		//req.setSmsParamString( "{name:'κ��',token:'123456'}" );
		//��������
		req.setSmsParamString(json.toString());
		//req.setRecNum( "15204696480" );
		//�����ֻ���
		req.setRecNum(phone);
		//����ģ�����
		req.setSmsTemplateCode("SMS_29115081");
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
