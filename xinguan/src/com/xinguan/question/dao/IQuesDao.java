package com.xinguan.question.dao;

import java.util.List;

import com.xinguan.pojo.Question;
import com.xinguan.utils.Page;

public interface IQuesDao {
	
	/**
	 * 提问
	 * @param ques
	 * @return 布尔
	 */
	public boolean quiz(Question ques);
	
	/**
	 * 查询问题总数
	 * @return int
	 */
	public int findAllQuesCount();
	
	/**
	 * 根据分页信息返回一个问题列表
	 * @param page
	 * @return 分页问题列表
	 */
	public List<Question> findQuesByPage(Page page);
	
	/**
	 * 返回最新的5条消息
	 * @return 最新五条问题
	 */
	public List<Question> findQuesNew();
	
	/**
	 * 根据ID号查找问题
	 * @param quesID
	 * @return 
	 */
	public Question findQuesByID(int quesID);
	
	/**
	 * 根据成员ＩＤ查询最新的五条问题
	 * @param memberID
	 * @return List<Question>
	 */
	public List<Question> findQuesListByMemberID(int memberID);
	
	/**
	 * 根据成员ID和分页信息返回问题列表
	 * @param memberID
	 * @param page
	 * @return List<Question>
	 */
	public List<Question> findQuesListByMemberID_Page(int memberID,Page page);
	
	/**
	 * 根据问题ID删除问题
	 * @param quesID
	 * @return
	 */
	public boolean deleteQuesByID(int quesID);
}