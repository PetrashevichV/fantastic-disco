package by.news.controller.impl;

import java.io.IOException;

import by.news.bean.User;
import by.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {

			response.sendRedirect("frontController?command=authorization");

			return;
		}

		User user = (User) session.getAttribute("user");

		if (user == null) {

			response.sendRedirect("frontController?command=authorization");

			return;
		}

		if (!"admin".equals(user.getRole())) {

			session.removeAttribute("user");
			// log

			response.sendRedirect("frontController?command=authorization");

			return;
		}

		// work

	}

}
