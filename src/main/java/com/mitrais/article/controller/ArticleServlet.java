package com.mitrais.article.controller;

import com.mitrais.article.model.Article;
import com.mitrais.article.service.ArticleService;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/article/*")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArticleService articleService;

    public ArticleServlet() {
        super();
    }

    public void init() {
        articleService = new ArticleService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath() + request.getPathInfo();

        try {
            switch (action) {
                case "/":
                    listArticle(request, response);
                    break;
                case "/article/new":
                    newArticle(request, response);
                    break;
                case "/article/view":
                    viewArticle(request, response);
                    break;
                case "/article/edit":
                    editArticle(request, response);
                    break;
                case "/article/delete":
                    deleteArticle(request, response);
                    break;
                case "/article/jpa/new":
                    jpaNewArticle(request, response);
                    break;
                case "/article/jpa/list":
//                    jpaListArticle(request, response);
                    jpqlListArticle(request, response);
                    break;
                case "/article/jpa/view":
                    jpaViewArticle(request, response);
                    break;
                case "/article/jpa/edit":
                    jpaEditArticle(request, response);
                    break;
                case "/article/jpa/delete":
                    jpaDeleteArticle(request, response);
                    break;
                default:
                    listArticle(request, response);
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
                case "/":
                    listArticle(request, response);
                    break;
                case "/article/create":
                    insertArticle(request, response);
                    break;
                case "/article/update":
                    updateArticle(request, response);
                    break;
                case "/article/jpa/create":
                    jpaInsertArticle(request, response);
                    break;
                case "/article/jpa/update":
                    jpaUpdateArticle(request, response);
                    break;
                default:
                    listArticle(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Article> listArticles = articleService.listArticle();
        request.setAttribute("listArticle", listArticles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/list.jsp");
        dispatcher.forward(request, response);
    }

    private void newArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/create.jsp");
        dispatcher.forward(request, response);
    }

    private void viewArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/view.jsp");
        Article article = articleService.showArticle(Integer.parseInt(request.getParameter("id")));
        System.out.println(article);
        // convert object to JSON for logging purpose
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(article));
        request.setAttribute("article", article);
        dispatcher.forward(request, response);
    }

    private void editArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/edit.jsp");
        Article article = articleService.showArticle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("article", article);
        dispatcher.forward(request, response);
    }

    private void insertArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Article article = new Article(title, content);
        articleService.save(article);
        response.sendRedirect("/Article");
    }

    private void updateArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        Article article = new Article(id, title, content, null);
        articleService.updateArticle(article);
        response.sendRedirect("/Article");
    }

    private void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));

        Article article = new Article(id, null, null, null);
        articleService.deleteArticle(article);
        response.sendRedirect("/Article");
    }

    // using JPA

    private void jpaNewArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/jpa_create.jsp");
        dispatcher.forward(request, response);
    }

    private void jpaListArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Article> listArticles = articleService.jpaListArticle();
        request.setAttribute("listArticle", listArticles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/jpa_list.jsp");
        dispatcher.forward(request, response);
    }

    private void jpaViewArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/jpa_view.jsp");
        Article article = articleService.jpaShowArticle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("article", article);
        dispatcher.forward(request, response);
    }

    private void jpaEditArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/jpa_edit.jsp");
        Article article = articleService.showArticle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("article", article);
        dispatcher.forward(request, response);
    }

    private void jpaInsertArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        articleService.jpaCreateArticle(title, content);
        response.sendRedirect("/Article/article/jpa/list");
    }

    private void jpaUpdateArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        articleService.jpaUpdateArticle(id, title, content);
        response.sendRedirect("/Article/article/jpa/list");
    }

    private void jpaDeleteArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));

        articleService.jpaRemoveArticle(id);
        response.sendRedirect("/Article/article/jpa/list");
    }

    // using JPQL
    private void jpqlListArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Article> listArticles = articleService.jpqlListArticle();
        request.setAttribute("listArticle", listArticles);
        listArticles.stream().forEach(System.out::print);
//        if (!listArticles.isEmpty()){
//            // convert object to JSON for logging purpose
//            ObjectMapper mapper = new ObjectMapper();
//            System.out.println(mapper.writeValueAsString(listArticles));
//        } else {
//            System.out.println("NO GOOD");
//        }
        // different view due to jpql uses array instead of object, to access the column, use index of the array
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/article/jpql_list.jsp");
        dispatcher.forward(request, response);
    }

}
