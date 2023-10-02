package by.news.listener;

import by.news.dao.connectionPool.ConnectionPool;
import by.news.dao.connectionPool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ConnectionPoolListner
 *
 */
@WebListener
public class ConnectionPoolListener implements ServletContextListener {

	public ConnectionPoolListener() {
		// TODO Auto-generated constructor stub
	}

	public void contextInitialized(ServletContextEvent e) {

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}

	public void contextDestroyed(ServletContextEvent e) {
		ConnectionPool.getInstance().dispose();

	}

}
