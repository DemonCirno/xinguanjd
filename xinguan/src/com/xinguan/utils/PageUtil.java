package com.xinguan.utils;
/**
 * ����page��ҳ��Ϣ�Ĺ�����
 * @author cirno
 *
 */
public class PageUtil {
	/**
	 * 
	 * @param everyPage
	 * @param totalCount
	 * @param currentPage
	 * @return page����
	 */
	public static Page createPage(int everyPage,int totalCount,int currentPage){
		everyPage = getEveryPage(everyPage);
		currentPage = getCurrentPage(currentPage);
		int totalPage = getTotalPage(everyPage,totalCount);
		int beginIndex = getBeginIndex(everyPage,currentPage);
		boolean hasPrePage = getPrePage(currentPage);
		boolean hasNextPage = getNextPage(totalPage,currentPage);
		
		return new Page(everyPage, totalCount, totalPage, currentPage, beginIndex, hasPrePage, hasNextPage);
	}
	
	public static  int getEveryPage(int everyPage) {		//���ÿҳ��ʾ��Ϣ
		return everyPage == 0 ? 10 : everyPage;
	}
	
	public static int getCurrentPage(int currentPage) {	//��õ�ǰҳ
		return currentPage == 0 ? 1 : currentPage;
	}
	
	public static int getTotalPage(int everyPage, int totalCount) {		//�����ҳ��
		int totalPage = 0;
		if(totalCount!=0 && totalCount % everyPage == 0){
			totalPage = totalCount/everyPage;
		}else{
			totalPage = totalCount/everyPage+1;
		}
		return totalPage;
	}
	
	public static int getBeginIndex(int everyPage, int currentPage) {	//��ÿ�ʼλ��
		return (currentPage-1)*everyPage;
	}

	public static boolean getPrePage(int currentPage) {	//�Ƿ�����һҳ
		// TODO Auto-generated method stub
		return currentPage == 1 ? false : true;
	}
	
	public static boolean getNextPage(int totalPage, int currentPage) {	//�Ƿ�����һҳ
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
}
