package com.xinguan.member.dao;

import com.xinguan.pojo.Member;

public interface IMemberDao {

	/**
	 * 注册
	 * @param member
	 */
	public void register(Member member);
	
	/**
	 * 通过学号查找成员
	 * @param stuId
	 * @return
	 */
	public Member findMemberByID(int stuId);
	
	/**
	 * 通过手机号查找成员
	 * @param phonenum
	 * @return
	 */
	public Member findMemberByPhone(String phonenum);
	
	/**
	 * 重置密码
	 * @param memberID
	 * @param password
	 * @return 
	 */
	public boolean resetPassword(int memberID,String password);
	
	/**
	 * 更新个人信息
	 * @param memberID
	 * @param member
	 */
	public void updateMemberInfo(int memberID,Member member);
}	
