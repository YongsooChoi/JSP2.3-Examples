package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			int total = articleDao.selectCount(conn);
			// 두 번째 파라미터는 조회할 레코드의 시작 행. 시작행은 0번 기준 (3페이지 요청시, 21번째 레코드부터 10개의 레코드)
			List<Article> content = articleDao.select(conn, (pageNum-1)*size, size);
			return new ArticlePage(total, pageNum, size, content);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
