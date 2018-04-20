package com.mitrais.article.controller;

import com.mitrais.article.model.Article;
import com.mitrais.article.model.User;
import com.mitrais.article.service.AccountService;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/auth/*")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountService accountService;

    public AccountServlet() {
        super();
    }

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        accountService = new AccountService(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/auth/login":
                    viewLogin(request, response);
                    break;
                case "/auth/logout":
                    doLogout(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/auth/login":
                    doLogin(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void viewLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/account/login.jsp");
        dispatcher.forward(request, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User userLogin = new User(username, password);
        User account = accountService.checkLogin(userLogin);
        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("uid", account.getId());
            session.setAttribute("username", account.getUsername());
        }
        response.sendRedirect("/Article");
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session=request.getSession();
        session.invalidate();
        response.sendRedirect("/Article");
    }

}