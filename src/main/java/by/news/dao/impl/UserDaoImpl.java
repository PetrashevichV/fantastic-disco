package by.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;
import by.news.dao.DaoExeption;
import by.news.dao.UserDao;
import by.news.dao.connectionPool.ConnectionPool;
import by.news.dao.connectionPool.ConnectionPoolException;

public class UserDaoImpl implements UserDao {
	private static final int USER_ROLE_ID = 1;

	public static final String INSERT_USER = "INSERT INTO user(u_login, u_password, u_email, u_role) VALUES(?,?,?,?)";

	public static final String SELECT_USER = "SELECT u.*, ur.u_role_name FROM user u "
			+ "INNER JOIN user_role ur ON ur.u_role_id = u.u_role WHERE u_login = ?";

	public UserDaoImpl() {

	}

	@Override
	public void saveRegistrationInfo(RegistrationInfo registrationInfo) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_USER);
			ps.setString(1, registrationInfo.getUser().getLogin());
			ps.setString(2, registrationInfo.getPassword());
			ps.setString(3, registrationInfo.getEmail());
			ps.setInt(4, USER_ROLE_ID);
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}

	}

	@Override
	public RegistrationInfo getRegistrationInfo(String login) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		RegistrationInfo registrationInfo = null;
		User user = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_USER);
			ps.setString(1, login);

			rs = ps.executeQuery();

			while (rs.next()) {
				registrationInfo = new RegistrationInfo();
				user = new User();
				user.setLogin(rs.getString("u_login"));
				user.setRole(rs.getString("u_role_name"));
				registrationInfo.setUser(user);

				registrationInfo.setEmail(rs.getString("u_email"));
				registrationInfo.setPassword(rs.getString("u_password"));
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}

		return registrationInfo;
	}

}
