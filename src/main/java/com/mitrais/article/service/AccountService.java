package com.mitrais.article.service;

import com.mitrais.article.model.User;

import java.sql.*;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class AccountService {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConntection;

    public AccountService() {
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

    public User checkLogin(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        connect();

        try (PreparedStatement ps = jdbcConntection.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.first()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    User account = new User(id, username, password);

                    return account;
                }
            }
        } finally {
            disconnect();
        }

        return null;
    }
}
