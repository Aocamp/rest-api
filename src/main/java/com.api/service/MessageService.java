package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MessageService {
    private Map<Long, Message> allMessagesMap = new HashMap<>();
    private Map<Long, Message> roomMessageMap = new HashMap<>();
    private Map<Long, Message> userMessageMap = new HashMap<>();


    private void setMessage(Message message, ResultSet rs){
        try {
            message.setId(rs.getLong("id"));
            message.setRoomId(rs.getLong("room_id"));
            message.setMessageText(rs.getString("message_text"));
            message.setMessageDate(rs.getDate("message_date"));
            message.setUserId(rs.getLong("user_id"));

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

    public List<Message> getAllMessages(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM messages");
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                allMessagesMap.put(message.getId(), message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<Message> messages = allMessagesMap.values();
        List<Message> list = new ArrayList<>(messages);
        return list;
    }

    public Message getMessageById(Long messageId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE id = ?";
            pst = conn.prepareStatement(query);
            pst.setLong(1, messageId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                allMessagesMap.put(message.getId(), message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        return allMessagesMap.get(messageId);
    }

    public List<Message> getMessagesByRoomId(Long roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE room_id = ? ORDER BY id";
            pst = conn.prepareStatement(query);
            pst.setLong(1, roomId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                roomMessageMap.put(message.getId(), message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<Message> messages = roomMessageMap.values();
        List<Message> list = new ArrayList<>(messages);
        return list;
    }

    public List<Message> getMessagesByUserId(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE user_id = ?";
            pst = conn.prepareStatement(query);
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                userMessageMap.put(message.getId(), message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<Message> messages = userMessageMap.values();
        List<Message> list = new ArrayList<>(messages);
        return list;
    }

    public void deleteMessagesInRoom(Long roomId) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE room_id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, roomId);
            pst.executeUpdate();
            allMessagesMap.remove(roomId);
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

    public void deleteUserMessages(Long userId) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE user_id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, userId);
            pst.executeUpdate();
            allMessagesMap.remove(userId);
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
