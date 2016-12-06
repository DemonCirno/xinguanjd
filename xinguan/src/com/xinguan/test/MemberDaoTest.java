package com.xinguan.test;

import org.junit.Test;

import com.xinguan.member.dao.IMemberDao;
import com.xinguan.member.dao.MemberDaoFactory;
import com.xinguan.pojo.Member;

public class MemberDaoTest {
	
	@Test
	public void testAddMember(){
		
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		
		for(int i=20145400; i<=20145450; i++){
			Member member = new Member();
			member.setMemberStuID(i);
			member.setPassword(i+"");
			memberdao.register(member);
		}
	}
	@Test
	public void testFingMemberByID(){
		
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Member member = memberdao.findMemberByID(20145425);
		System.out.println(member.getPassword());
	}
	
	@Test
	public void testFindMemberByPhone(){
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Member member = memberdao.findMemberByPhone("15204696480");
		System.out.println(member.getPassword());
	}
	
	@Test
	public void testResetPassword(){
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		memberdao.resetPassword(25, "weiyao19960305");

	}
	
	@Test
	public void testUpdateInfo(){
		IMemberDao memberdao = MemberDaoFactory.createMemberDaoImpl();
		Member member = new Member();
		member.setMemberNickname("콜옵콜옵콜");
		member.setMemberSex(false);
		memberdao.updateMemberInfo(25, member);
	}
	
}