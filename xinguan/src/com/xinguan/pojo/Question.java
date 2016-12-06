package com.xinguan.pojo;

import java.sql.Date;
/**
 * 问题实体类
 * @author cirno
 *
 */
public class Question {
	
	//问题主键自增
	private int quesID;
	//标题
	private String quesTitle;
	//内容
	private String quesContent;
	//图片路径
	private String quesImagePath;
	//发布时间
	private Date quesPublishTime;
	//发布人外键
	private int memberID;
	
	private String memberName;
	
	public int getQuesID() {
		return quesID;
	}
	public void setQuesID(int quesID) {
		this.quesID = quesID;
	}
	public String getQuesTitle() {
		return quesTitle;
	}
	public void setQuesTitle(String quesTitle) {
		this.quesTitle = quesTitle;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
	public String getQuesImagePath() {
		return quesImagePath;
	}
	public void setQuesImagePath(String quesImagePath) {
		this.quesImagePath = quesImagePath;
	}
	public Date getQuesPublishTime() {
		return quesPublishTime;
	}
	public void setQuesPublishTime(Date quesPublishTime) {
		this.quesPublishTime = quesPublishTime;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	public String toString(){
		return "id:" + this.quesID + "title:" + this.quesTitle;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
}