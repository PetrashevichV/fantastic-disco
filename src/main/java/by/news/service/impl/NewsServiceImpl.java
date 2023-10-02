package by.news.service.impl;

import java.util.List;

import by.news.bean.News;
import by.news.dao.DaoExeption;
import by.news.dao.DaoProvider;
import by.news.dao.NewsDao;

import by.news.service.NewsService;
import by.news.service.ServiceExeption;

public class NewsServiceImpl implements NewsService {

	private static final DaoProvider provider = DaoProvider.getInstance();
	private static final NewsDao newsDao = provider.getNewsDao();

	@Override
	public void addNews(News news) {
		// TODO Auto-generated method stub

	}

	@Override
	public void upDate(News news) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<News> getNews() throws ServiceExeption {
		List<News> list = null;
		try {
			list = newsDao.getNews();
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
		return list;
	}

}
