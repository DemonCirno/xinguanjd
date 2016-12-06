package com.xinguan.question.dao;

import java.util.List;

import com.xinguan.pojo.Question;
import com.xinguan.utils.Page;

public interface IQuesDao {
	
	/**
	 * ����
	 * @param ques
	 * @return
	 */
	public boolean quiz(Question ques);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public int findAllQuesCount();
	
	/**
	 * ���ݷ�ҳ��Ϣ����һ�������б�
	 * @param page
	 * @return �����б�
	 */
	public List<Question> findQuesByPage(Page page);
	
	/**
	 * �������µ�5����Ϣ
	 * @return
	 */
	public List<Question> findQuesNew();
	
	/**
	 * ����ID�Ų�������
	 * @param quesID
	 * @return
	 */
	public Question findQuesByID(int quesID);
}