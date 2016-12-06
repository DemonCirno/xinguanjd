package com.xinguan.test;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.xinguan.pojo.Question;
import com.xinguan.question.dao.IQuesDao;
import com.xinguan.question.dao.QuestionFactory;
import com.xinguan.utils.Page;
import com.xinguan.utils.PageUtil;

public class QuestionTest {
	
	@Test
	public void testquiz(){
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		Question ques = new Question();
		for (int i = 0; i <= 50; i++) {
			ques.setQuesTitle(i+"");
			ques.setQuesContent(i+"");
			ques.setQuesPublishTime(new java.sql.Date(new Date().getTime()));
			ques.setMemberID(i);
			boolean flag = quesdao.quiz(ques);
			System.out.println(flag);
		}
	}
	
	@Test
	public void testselectAll(){
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		int count = quesdao.findAllQuesCount();
		System.out.println(count);
	}
	
	@Test
	public void testFindQuesByPage(){
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		Page page = PageUtil.createPage(5, quesdao.findAllQuesCount(), 3);
		List<Question> list = quesdao.findQuesByPage(page);
		for (Question question : list) {
			System.out.println(question.getQuesPublishTime());
		}
	}
	
	@Test
	public void testFindQuesNew(){
		IQuesDao quesdao = QuestionFactory.createQuestionImplInstance();
		List<Question> list = quesdao.findQuesNew();
		for (Question question : list) {
			System.out.println(question.getMemberID());
		}
	}
}
