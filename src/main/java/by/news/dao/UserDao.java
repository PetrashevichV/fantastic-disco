package by.news.dao;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;

public interface UserDao {

	public void saveRegistrationInfo(RegistrationInfo registrationInfo) throws DaoExeption;

	public RegistrationInfo getRegistrationInfo(String login) throws DaoExeption;

}
