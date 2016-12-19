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
	 * ����memberID���ҳ�Ա
	 * @param memberID
	 * @return
	 */
	public Member findMemberByMemID(int memberID);
	
	/**
	 * ��������
	 * @param memberID
	 * @param password
	 * @return 
	 */
	public boolean resetPassword(int memberID,String password);
	
	/**
	 * ���¸�����Ϣ(�ǳƣ��Ա�)
	 * @param memberID
	 * @param member
	 */
	public boolean updateMemberInfo(int memberID,Member member);
	
	/**
	 * ����ͷ��(�ǳƣ��Ա�)
	 * @param memberID
	 * @param member
	 */
	public void updateMemberImage(int memberID,Member member);
}	
