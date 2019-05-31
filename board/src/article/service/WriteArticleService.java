package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest req) {	// 파라미터를 이용해 게시글을 등록하고, 결과로 게시글 번호를 리턴한다.
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(req);	// WriteRequest로부터 Article 객체를 생성한다.
			Article savedArticle = articleDao.insert(conn, article); // 메서드 실행 결과를 savedArticle에 할당.
			if(savedArticle == null) {	// article 테이블에 삽입한 레코드가 없을 때,
				throw new RuntimeException("fail to insert article");
			}
			ArticleContent content = new ArticleContent(	// 게시물 내용을 위한 객체 생성
					savedArticle.getNumber(),				// 파라미터로 savedArticle의 게시글 번호를 사용한다.
					req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);	// 테이블에 데이터를 삽입하고, 할당.
			if(savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			return savedArticle.getNumber();	// 새로 추가한 게시글 번호를 리턴한다.
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Article toArticle(WriteRequest req) {
		Date now = new Date();													// insert 실행해야 id를 알 수 있으므로
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);	// number 값으로 null 전달
	}
}
