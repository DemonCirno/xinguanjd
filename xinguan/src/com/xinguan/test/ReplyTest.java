package com.xinguan.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import com.xinguan.pojo.Reply;
import com.xinguan.reply.dao.IReplyDao;
import com.xinguan.reply.dao.ReplyFactory;
import com.xinguan.utils.Page;
import com.xinguan.utils.PageUtil;

public class ReplyTest {
	
	@Test
	public void testaddReply(){
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		for(int i=0;i<10;i++){
			Reply reply = new Reply();
			reply.setReplyContent("hello");
			reply.setReplyTime(new Date());
			reply.setQuesID(101);
			replydao.reply(reply);
		}
	}
	
	@Test
	public void testCount(){
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		int count = replydao.countReply(12);
		System.out.println(count);
	}
	
	@Test
	public void testFindByPageID(){
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		
		Page page = PageUtil.createPage(5, replydao.countReply(0), 1);
		List<Reply> list = replydao.findReplyByPageID(0, page);
		for (Reply reply : list) {
			System.out.println(reply.getReplyID());
		}
	}
	
	@Test
	public void testUpdateCount(){
		IReplyDao replydao = ReplyFactory.createReplyImplInstance();
		replydao.updateCount(1);
	}
}
