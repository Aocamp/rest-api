package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserService {
    private static final Map<String, User> allUsersMap = new HashMap<>();
    private static final Map<String, User> userMap = new HashMap<>();

    public static List<User> getAllUsers(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users");
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUserLogin(rs.getString("user_login"));
                allUsersMap.put(user.getId(), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        Collection<User> users = allUsersMap.values();
        List<User> list = new ArrayList<>(users);
        return list;
    }

    public static User getUserById(String userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUserLogin(rs.getString("user_login"));
                userMap.put(user.getId(), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        System.out.println(userMap.get(userId));
        return userMap.get(userId);
    }

    public static User addUser(User user){
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
        return user;
    }

    public static User updateUser(User user){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String update = "UPDATE users SET user_login = ? WHERE id = ?";
            pst = conn.prepareStatement(update);
            pst.setString(1, user.getUserLogin());
            pst.setString(2, user.getId());
            pst.executeUpdate();
            allUsersMap.put(user.getId(), user);
            userMap.put(user.getId(), user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return user;
    }

    public static void deleteUser(String userId){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM users WHERE id = ?";
            pst = conn.prepareStatement(delete);
            pst.setString(1, userId);
            pst.executeUpdate();
            allUsersMap.remove(userId);
            userMap.remove(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
}
