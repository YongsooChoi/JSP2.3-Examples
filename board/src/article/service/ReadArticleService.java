package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	// 게시글 읽기 기능, 게시글 수정 기능에서 사용하는 메서드로 필요에 따라 조회수를 증가시킬 수 있도록 두번째 파라미터를 사용한다.
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			// article 테이블에서 지정한 번호의 Article 객체를 구한다.
			Article article = articleDao.selectById(conn, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			
			// article_content 테이블에서 지정한 번호의 ArticleContent 객체를 구한다.
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			
			if(increaseReadCount) { // increaseReadCount가 true 이면, 조회수를 증가시킨다.
				articleDao.increaseReadCount(conn, articleNum);
			}
			return new ArticleData(article, content); // ArticleData 객체를 리턴한다.
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
