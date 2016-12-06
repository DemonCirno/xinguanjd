package com.xinguan.question.dao;

public class QuestionFactory {
	
	public static IQuesDao createQuestionImplInstance(){
		return new QuestionImplDao();
	}
}
