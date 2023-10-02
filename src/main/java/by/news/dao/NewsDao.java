package by.news.dao;

import java.util.List;

import by.news.bean.News;

public interface NewsDao {

	public void saveNews(News news) throws DaoExeption;

	public List<News> getNews() throws DaoExeption;

}
