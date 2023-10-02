package by.news.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import by.news.bean.RegistrationInfo;
import by.news.dao.DaoExeption;
import by.news.dao.DaoProvider;
import by.news.dao.UserDao;
import by.news.service.ServiceExeption;
import by.news.service.UserService;

public class UserServiceImpl implements UserService {

	private static final DaoProvider provider = DaoProvider.getInstance();
	private static final UserDao userDao = provider.getUserDao();

	@Override
	public void registration(RegistrationInfo registrationInfo) throws ServiceExeption {
		try {
			registrationInfo.setPassword(hashPassword(registrationInfo.getPassword()));
			userDao.saveRegistrationInfo(registrationInfo);

		} catch (DaoExeption e) {
			throw new ServiceExeption(e);// TODO: handle exception
		}

		catch (NoSuchAlgorithmException e) {
			// TODO log
			throw new ServiceExeption(e);
		}

	}

	@Override
	public RegistrationInfo authorithation(String login, String password) throws ServiceExeption {

		RegistrationInfo registrationInfo = null;
		try {
			registrationInfo = userDao.getRegistrationInfo(login);

			if (registrationInfo == null) {
				return null;
			}
			if (registrationInfo.getPassword().equals(hashPassword(password))) {
				return registrationInfo;
			} else {
				return null;
			}
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}

		catch (NoSuchAlgorithmException e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	@Override
	public boolean isLoginEmpty(String login) throws ServiceExeption {

		try {
			RegistrationInfo registrationInfo = userDao.getRegistrationInfo(login);
			return registrationInfo == null;
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");

		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

		StringBuffer sb = new StringBuffer();
		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

}
