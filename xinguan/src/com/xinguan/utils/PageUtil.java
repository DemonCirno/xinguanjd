package com.xinguan.utils;
/**
 * 生成page分页信息的工具类
 * @author cirno
 *
 */
public class PageUtil {
	/**
	 * 
	 * @param everyPage
	 * @param totalCount
	 * @param currentPage
	 * @return page对象
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
	
	public static  int getEveryPage(int everyPage) {		//获得每页显示消息
		return everyPage == 0 ? 10 : everyPage;
	}
	
	public static int getCurrentPage(int currentPage) {	//获得当前页
		return currentPage == 0 ? 1 : currentPage;
	}
	
	public static int getTotalPage(int everyPage, int totalCount) {		//获得总页数
		int totalPage = 0;
		if(totalCount!=0 && totalCount % everyPage == 0){
			totalPage = totalCount/everyPage;
		}else{
			totalPage = totalCount/everyPage+1;
		}
		return totalPage;
	}
	
	public static int getBeginIndex(int everyPage, int currentPage) {	//获得开始位置
		return (currentPage-1)*everyPage;
	}

	public static boolean getPrePage(int currentPage) {	//是否有上一页
		// TODO Auto-generated method stub
		return currentPage == 1 ? false : true;
	}
	
	public static boolean getNextPage(int totalPage, int currentPage) {	//是否有下一页
		return currentPage == totalPage || totalPage == 0 ? false : true;
	}
}
