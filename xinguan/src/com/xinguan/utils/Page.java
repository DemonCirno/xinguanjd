package com.xinguan.utils;

/**
 * ��ҳ��Ϣʵ����
 * @author cirno
 *
 */
public class Page {
	
	private int everyPage;	//ÿҳ��ʾ��Ϣ����
	private int totalCount;	//����Ϣ��
	private int totalPage;		//��ҳ��
	private int currentPage;	//��ǰҳ
	private int beginIndex;	//��ѯ��ʼλ��
	private boolean hasPrePage;	//�ж��Ƿ�����һҳ
	private boolean hasNextPage;	//�ж��Ƿ�����һҳ
	
	/**
	 * ���캯��
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