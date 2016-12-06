package com.xinguan.reply.dao;

public class ReplyFactory {
	
	public static IReplyDao createReplyImplInstance(){
		return new ReplyDaoImpl();
	}
}
