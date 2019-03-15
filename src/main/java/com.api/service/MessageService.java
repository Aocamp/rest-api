package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MessageService {
    private static final Map<String, Message> allMessagesMap = new HashMap<>();
    private static final Map<String, Message> messageMap = new HashMap<>();

    public static List<Message> getAllMessages(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM messages");
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getString("id"));
                message.setRoomId(rs.getString("room_id"));
                message.setMessageText(rs.getString("message_text"));
                message.setMessageDate(rs.getDate("message_date"));
                message.setUserId(rs.getString("user_id"));
                allMessagesMap.put(message.getId(), message);
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
        Collection<Message> messages = allMessagesMap.values();
        List<Message> list = new ArrayList<>(messages);
        return list;
    }

    public static Message getMessageById(String messageId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, messageId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getString("id"));
                message.setRoomId(rs.getString("room_id"));
                message.setMessageText(rs.getString("message_text"));
                message.setMessageDate(rs.getDate("message_date"));
                message.setUserId(rs.getString("user_id"));
                messageMap.put(message.getId(), message);
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
        return messageMap.get(messageId);
    }

    public static Message getMessagesByRoomId(String roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE room_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, roomId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getString("id"));
                message.setRoomId(rs.getString("room_id"));
                message.setMessageText(rs.getString("message_text"));
                message.setMessageDate(rs.getDate("message_date"));
                message.setUserId(rs.getString("user_id"));
                messageMap.put(message.getRoomId(), message);
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
        return messageMap.get(roomId);
    }

    public static Message getMessagesByUserId(String userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE user_id = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getString("id"));
                message.setRoomId(rs.getString("room_id"));
                message.setMessageText(rs.getString("message_text"));
                message.setMessageDate(rs.getDate("message_date"));
                message.setUserId(rs.getString("user_id"));
                messageMap.put(message.getUserId(), message);
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
        return messageMap.get(userId);
    }

    public static void deleteMessagesInRoom(String roomId) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE room_id = ?";
            pst = conn.prepareStatement(delete);
            pst.setString(1, roomId);
            pst.executeUpdate();
            allMessagesMap.remove(roomId);
            messageMap.remove(roomId);
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

    public static void deleteUserMessages(String userId) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE user_id = ?";
            pst = conn.prepareStatement(delete);
            pst.setString(1, userId);
            pst.executeUpdate();
            allMessagesMap.remove(userId);
            messageMap.remove(userId);
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
