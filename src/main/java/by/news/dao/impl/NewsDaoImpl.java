package by.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import by.news.bean.News;
import by.news.dao.DaoExeption;
import by.news.dao.NewsDao;
import by.news.dao.conectionPool.config.Config;
import by.news.dao.connectionPool.ConnectionPool;
import by.news.dao.connectionPool.ConnectionPoolException;

public class NewsDaoImpl implements NewsDao {

	public static final String SELECT_NEWS = "SELECT n.*, nc.n_category_name "
			+ "FROM news n INNER JOIN news_category nc ON nc.n_category_id = n.n_category ORDER BY n_date LIMIT ?";

	public NewsDaoImpl() {

	}

	@Override
	public void saveNews(News news) throws DaoExeption {
		// TODO Auto-generated method stub

	}

	@Override
	public List<News> getNews() throws DaoExeption {
		List<News> listOfNews = new LinkedList<News>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_NEWS);
			int limit = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
			ps.setInt(1, limit);

			rs = ps.executeQuery();

			while (rs.next()) {
				News news = new News();
				news.setId(rs.getLong("n_id"));
				news.setTitle(rs.getString("n_title"));
				news.setBrief(rs.getString("n_brief"));
				news.setContent(rs.getString("n_content"));
				news.setDate(rs.getTimestamp("n_date").toLocalDateTime());
				news.setCategory(rs.getString("n_category_name"));
				listOfNews.add(news);
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);

		}

		return listOfNews;
	}

}
