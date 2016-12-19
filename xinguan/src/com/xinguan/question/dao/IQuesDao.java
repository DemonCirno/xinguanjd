package com.xinguan.question.dao;

import java.util.List;

import com.xinguan.pojo.Question;
import com.xinguan.utils.Page;

public interface IQuesDao {
	
	/**
	 * ����
	 * @param ques
	 * @return ����
	 */
	public boolean quiz(Question ques);
	
	/**
	 * ��ѯ��������
	 * @return int
	 */
	public int findAllQuesCount();
	
	/**
	 * ���ݷ�ҳ��Ϣ����һ�������б�
	 * @param page
	 * @return ��ҳ�����б�
	 */
	public List<Question> findQuesByPage(Page page);
	
	/**
	 * �������µ�5����Ϣ
	 * @return ������������
	 */
	public List<Question> findQuesNew();
	
	/**
	 * ����ID�Ų�������
	 * @param quesID
	 * @return 
	 */
	public Question findQuesByID(int quesID);
	
	/**
	 * ���ݳ�Ա�ɣĲ�ѯ���µ���������
	 * @param memberID
	 * @return List<Question>
	 */
	public List<Question> findQuesListByMemberID(int memberID);
	
	/**
	 * ���ݳ�ԱID�ͷ�ҳ��Ϣ���������б�
	 * @param memberID
	 * @param page
	 * @return List<Question>
	 */
	public List<Question> findQuesListByMemberID_Page(int memberID,Page page);
	
	/**
	 * ��������IDɾ������
	 * @param quesID
	 * @return
	 */
	public boolean deleteQuesByID(int quesID);
}