/*
 * 게시글 목록을 제공하는 서비스 클래스 구현
 */

package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {

	private int total;
	private int currentPage;		// 사용자가 요청한 페이지 번호
	private List<Article> content;	// 화면에 출력할 게시글 목록 보관
	private int totalPages;			
	private int startPage;			// 하단 페이지 이동 링크의 시작 번호
	private int endPage;			// 하단 페이지 이동 링크의 끝 번호
	
	public ArticlePage(int total, int currentPage, int size, List<Article> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total/size;
			if(total % size > 0) {
				totalPages++;		// 34/10은 4 페이지, 60/10은 6 페이지
			}
			int modVal = currentPage % 5;			
			startPage = currentPage / 5 * 5 + 1;	// 현재 페이지 3이면 시작 페이지 1, 10이면 시작페이지 6
			if(modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if(endPage > totalPages) endPage = totalPages;
		}
	}
	
	public int getTotal() {
		return total;
	}
	
	public boolean hasNoArticles() {
		return total == 0;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public List<Article> getContent() {
		return content;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
