package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PagingVO<T> {
	//제너릭타입을 지정하지 않으면 페이징처리가 필요할 때마다 페이지VO를 생성해서 사용해야한다. 그것을 방지하기 위해서
	//제너릭타입을 지정해서 사용할 수 있도록 한다.
	private int totalRecord;
	private int currentPage;
	private int screenSize = 10;
	private int blockSize = 5;
	private int totalPage;
	
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	private SearchVO simpleCondition;
	private T detailCondition;
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord+(screenSize-1)) / screenSize;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage*screenSize;
		startRow = endRow - (screenSize-1);
		endPage = blockSize*((currentPage + (blockSize-1))/blockSize);
		startPage = endPage - (blockSize - 1);
	}
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	String pattern = "<li class='page-item'><a class='page-link' href='#' data-page='%d'>%s</a></li>";
	String curPattern = "<li class='page-item active' aria-current='page'><a class='page-link' href='#' data-page='%d'>%s</a></li>";
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination'>");
		endPage = endPage > totalPage ? totalPage : endPage;
		if(startPage>blockSize) {
			html.append(String.format(pattern, startPage-blockSize,"이전"));
		}
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append(String.format(curPattern, page,page));
			}else {
				html.append(String.format(pattern, page, page));
			}
		}
		if(endPage < totalPage) {
			html.append(String.format(pattern, endPage+1, "다음"));
		}
		html.append("</ul>");
		return html.toString();
	}
	
}
