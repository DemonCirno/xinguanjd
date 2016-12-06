package com.xinguan.question.dao;

import java.util.List;

import com.xinguan.pojo.Question;
import com.xinguan.utils.Page;

public interface IQuesDao {
	
	/**
	 * 提问
	 * @param ques
	 * @return
	 */
	public boolean quiz(Question ques);
	
	/**
	 * 查询问题总数
	 * @return
	 */
	public int findAllQuesCount();
	
	/**
	 * 根据分页信息返回一个问题列表
	 * @param page
	 * @return 问题列表
	 */
	public List<Question> findQuesByPage(Page page);
	
	/**
	 * 返回最新的5条消息
	 * @return
	 */
	public List<Question> findQuesNew();
	
	/**
	 * 根据ID号查找问题
	 * @param quesID
	 * @return
	 */
	public Question findQuesByID(int quesID);
}