package com.xinguan.utils;

/**
 * 分页信息实体类
 * @author cirno
 *
 */
public class Page {
	
	private int everyPage;	//每页显示消息条数
	private int totalCount;	//总消息数
	private int totalPage;		//总页数
	private int currentPage;	//当前页
	private int beginIndex;	//查询起始位置
	private boolean hasPrePage;	//判断是否有上一页
	private boolean hasNextPage;	//判断是否有下一页
	
	/**
	 * 构造函数
	 * @param everyPage
	 * @param totalCount
	 * @param totalPage
	 * @param currentPage
	 * @param beginIndex
	 * @param hasPrePage
	 * @param hasNextPage
	 */
	public Page(int everyPage,int totalCount,int totalPage,int currentPage, int beginIndex,boolean hasPrePage,boolean hasNextPage){
		this.everyPage = everyPage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	
	public int getEveryPage() {
		return everyPage;
	}

	public void setEveryPage(int everyPage) {
		this.everyPage = everyPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

}