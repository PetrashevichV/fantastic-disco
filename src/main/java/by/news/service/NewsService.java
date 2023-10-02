package by.news.service;

import java.util.List;

import by.news.bean.News;

public interface NewsService {

	public void addNews(News news) throws ServiceExeption;

	public void upDate(News news) throws ServiceExeption;

	public List<News> getNews() throws ServiceExeption;
}
