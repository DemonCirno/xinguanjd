package com.xinguan.member.dao;

import com.xinguan.pojo.Member;

public interface IMemberDao {

	/**
	 * ע��
	 * @param member
	 */
	public void register(Member member);
	
	/**
	 * ͨ��ѧ�Ų��ҳ�Ա
	 * @param stuId
	 * @return
	 */
	public Member findMemberByID(int stuId);
	
	/**
	 * ͨ���ֻ��Ų��ҳ�Ա
	 * @param phonenum
	 * @return
	 */
	public Member findMemberByPhone(String phonenum);
	
	/**
	 * ��������
	 * @param memberID
	 * @param password
	 * @return 
	 */
	public boolean resetPassword(int memberID,String password);
	
	/**
	 * ���¸�����Ϣ
	 * @param memberID
	 * @param member
	 */
	public void updateMemberInfo(int memberID,Member member);
}	
