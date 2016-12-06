package com.xinguan.member.dao;

public class MemberDaoFactory {
	
	public static IMemberDao createMemberDaoImpl(){
		return new MemberDaoImpl();
	}
}
