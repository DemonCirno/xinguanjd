package com.xinguan.reply.dao;

import java.util.List;

import com.xinguan.pojo.Reply;
import com.xinguan.utils.Page;

public interface IReplyDao {
	
	/**
	 * 回复问题
	 * @param reply
	 */
	public void reply(Reply reply);
	
	/**
	 * 根据问题ID查找回复的条数
	 * @param quesID
	 * @return
	 */
	public int countReply(int quesID);
	
	/**
	 * 根据问题ID和分页信息返回问题列表
	 * @param quesID
	 * @param page
	 * @return
	 */
	public List<Reply> findReplyByPageID(int quesID,Page page);
	
	/**
	 * 加一
	 */
	public Boolean updateCount(int replyID);
}
