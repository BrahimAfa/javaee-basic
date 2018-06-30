package com.mitrais.article.service;

import com.mitrais.article.model.Article;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ArticleService {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConntection;

    public ArticleService() {
        ResourceBundle database = PropertyResourceBundle.getBundle("database");
        this.jdbcURL = database.getString("javax.persistence.jdbc.url");
        this.jdbcUsername = database.getString("javax.persistence.jdbc.user");
        this.jdbcPassword = database.getString("javax.persistence.jdbc.password");
    }

    protected void connect() throws SQLException {
        if (jdbcConntection == null || jdbcConntection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConntection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConntection != null && !jdbcConntection.isClosed()) {
            jdbcConntection.close();
        }
    }

    public boolean save(Article article) throws SQLException {
        boolean rowInserted = false;
        connect();
        try (PreparedStatement ps = jdbcConntection.prepareStatement("INSERT INTO articles (title, content) VALUES (?, ?)")) {//try- with-resource

            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());

            rowInserted = ps.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            disconnect();
        }

        return rowInserted;
    }

    public List<Article> listArticle() throws SQLException {
        List<Article> listArticle = new ArrayList<>();
        connect();
        try (
                PreparedStatement ps = jdbcConntection.prepareStatement("SELECT * FROM articles");
                ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");

                Article article = new Article(id, title, content, null);
                listArticle.add(article);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            disconnect();
        }
        return listArticle;

    }

    public Article showArticle(int ids) throws SQLException {

        connect();
        try (
                PreparedStatement ps = jdbcConntection.prepareStatement("SELECT * FROM articles where id = ?");
        ) {

            ps.setInt(1, ids);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    Long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Article article = new Article(id, title, content, null);

                    return article;
                }
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }

        return null;
    }

    public boolean deleteArticle(Article article) throws SQLException {
        boolean rowUpdated;
        connect();
        try (PreparedStatement ps = jdbcConntection.prepareStatement("DELETE FROM articles WHERE id = ?");

        ){
            ps.setLong(1, article.getId());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }

        return rowUpdated;
    }

    public boolean updateArticle(Article article) throws SQLException {
        boolean rowDeleted;
        connect();
        try (PreparedStatement ps = jdbcConntection.prepareStatement("UPDATE articles SET title = ?, content = ? WHERE id = ?")) {
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setLong(3, article.getId());

            rowDeleted = ps.executeUpdate() > 0;
        } finally {
            disconnect();
        }

        return rowDeleted;
    }

    // using JPA

    public List<Article> jpaListArticle() throws SQLException {
        List<Article> listArticle = new ArrayList<>();
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager em = emFactory.createEntityManager();

        // there are no find all in JPA so bear with it
        Article article1 = em.find(Article.class, (long) 1);
        Article article2 = em.find(Article.class, (long) 2);
        Article article3 = em.find(Article.class, (long) 3);
        Article article4 = em.find(Article.class, (long) 4);
        Article article5 = em.find(Article.class, (long) 5);
        listArticle.add(article1);
        listArticle.add(article2);
        listArticle.add(article3);
        listArticle.add(article4);
        listArticle.add(article5);

        return listArticle;
    }

    public Article jpaShowArticle(int ids) throws SQLException {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        Article article = null;
        try {
            emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
            em = emFactory.createEntityManager();
            article = em.find(Article.class, (long) ids);
        } finally {
            em.close();
            emFactory.close();
        }

        return article;
    }

    public void jpaCreateArticle(String title, String content) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
            em = emFactory.createEntityManager();
            em.getTransaction().begin();

            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);

            em.persist(article);
            em.getTransaction().commit();
        } finally {
            em.close();
            emFactory.close();
        }

    }

    public void jpaUpdateArticle(Long id, String title, String content) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Article article = em.find(Article.class, id);

            article.setTitle(title);
            article.setContent(content);

            em.getTransaction().commit();
        } finally {
            em.close();
            emFactory.close();
        }
    }

    public void jpaRemoveArticle(Long id) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
            em = emFactory.createEntityManager();
            em.getTransaction().begin();
            Article article = em.find(Article.class, id);

            em.remove(article);
            em.getTransaction().commit();
        } finally {
            em.close();
            emFactory.close();
        }
    }

    // using JPQL
    public List<Article> jpqlListArticle() throws SQLException {
        List<Article> listArticle = new ArrayList<>();
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence.createEntityManagerFactory("PERSISTENCE");
            em = emFactory.createEntityManager();

            // table name first letter must be in uppercase
            Query query = em.createQuery("SELECT id,title,content FROM Article");
            listArticle = (List<Article>)query.getResultList();
        } finally {
            em.close();
            emFactory.close();
        }

        return listArticle;
    }
}
