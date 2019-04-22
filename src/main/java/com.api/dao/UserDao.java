package com.api.dao;

import com.api.database.HikariCPDataSource;
import com.api.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements BaseDao<User> {
    @Override
    public List<User> getAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<User> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users");
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                setUser(user, rs);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return list;
    }

    @Override
    public User getById(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        User user = new User();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                setUser(user, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return user;
    }

    public User getByLogin(String login) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        User user = new User();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users WHERE user_login= ?");
            pst.setString(1, login);
            rs = pst.executeQuery();
            while (rs.next()) {
                setUser(user, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return user;
    }

    public User addUser(User user){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM users WHERE user_login = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, user.getUserLogin());
            rs = pst.executeQuery();
            if (rs.wasNull()) {
                String add = "INSERT INTO users (user_login, support) VALUES (?, ?)";
                pst = conn.prepareStatement(add);
                pst.setString(1, user.getUserLogin());
                pst.setInt(2, user.getSupport());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return user;
    }

    public User updateUser(User user){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String update = "UPDATE users SET user_login = ?, support = ? WHERE id = ?";
            pst = conn.prepareStatement(update);
            pst.setString(1, user.getUserLogin());
            pst.setInt(2, user.getSupport());
            pst.setLong(3, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM users WHERE id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
        }
    }

    private void setUser(User user, ResultSet rs){
        try {
            user.setId(rs.getLong("id"));
            user.setUserLogin(rs.getString("user_login"));
            user.setSupport(rs.getInt("support"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection conn){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closePreparedStatement(PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
