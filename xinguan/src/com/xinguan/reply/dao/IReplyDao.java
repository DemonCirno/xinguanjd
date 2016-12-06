package com.xinguan.reply.dao;

import java.util.List;

import com.xinguan.pojo.Reply;
import com.xinguan.utils.Page;

public interface IReplyDao {
	
	/**
	 * �ظ�����
	 * @param reply
	 */
	public void reply(Reply reply);
	
	/**
	 * ��������ID���һظ�������
	 * @param quesID
	 * @return
	 */
	public int countReply(int quesID);
	
	/**
	 * ��������ID�ͷ�ҳ��Ϣ���������б�
	 * @param quesID
	 * @param page
	 * @return
	 */
	public List<Reply> findReplyByPageID(int quesID,Page page);
	
	/**
	 * ��һ
	 */
	public Boolean updateCount(int replyID);
}
