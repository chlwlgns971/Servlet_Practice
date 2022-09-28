package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PagingVO<T> {
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
	
	String pattern = "<a href='?page=%d'>%s</a>";
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		endPage = endPage > totalPage ? totalPage : endPage;
		if(startPage>blockSize) {
			html.append(String.format(pattern, startPage-blockSize,"이전"));
		}
		for(int page = startPage; page <= endPage; page++) {
			if(page == currentPage) {
				html.append(page);
			}else {
				html.append(String.format(pattern, page, page));
			}
		}
		if(endPage < totalPage) {
			html.append(String.format(pattern, endPage+1, "다음"));
		}
		return html.toString();
	}
	
}
