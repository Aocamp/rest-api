package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserService {
    private Map<Long, User> allUsersMap = new HashMap<>();

    private void setUser(ResultSet rs){
        User user = new User();
        try {
            user.setId(rs.getLong("id"));
            user.setUserLogin(rs.getString("user_login"));
            user.setSupport(rs.getInt("support"));
            allUsersMap.put(user.getId(), user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection conn, PreparedStatement pst, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection conn, PreparedStatement pst){
        try {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users");
            rs = pst.executeQuery();
            while (rs.next()) {
                setUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<User> users = allUsersMap.values();
        List<User> list = new ArrayList<>(users);
        return list;
    }

    public User getUserById(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                setUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        System.out.println(allUsersMap.get(userId));
        return allUsersMap.get(userId);
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
                String add = "INSERT INTO users (user_login) VALUES (?)";
                pst = conn.prepareStatement(add);
                pst.setString(1, user.getUserLogin());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        return user;
    }

    public User updateUser(User user){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String update = "UPDATE users SET user_login = ? WHERE id = ?";
            pst = conn.prepareStatement(update);
            pst.setString(1, user.getUserLogin());
            pst.setLong(2, user.getId());
            pst.executeUpdate();
            allUsersMap.put(user.getId(), user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst);
        }
        return user;
    }

    public void deleteUser(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM users WHERE id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, userId);
            pst.executeUpdate();
            allUsersMap.remove(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst);
        }
    }
}
