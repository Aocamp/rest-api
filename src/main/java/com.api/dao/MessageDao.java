package com.api.dao;

import com.api.database.HikariCPDataSource;
import com.api.model.Message;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MessageDao implements BaseDao<Message> {
    @Override
    public List<Message> getAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Message> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT *, user_login FROM messages INNER JOIN users u ON messages.user_id = u.id";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }

        return list;
    }

    @Override
    public Message getById(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Message message = new Message();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT *, user_login FROM messages INNER JOIN users u ON messages.user_id = u.id WHERE messages.id = ? ";
            pst = conn.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                setMessage(message, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return message;
    }

    public List<Message> getMessagesByRoomId(Long roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Message> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT *, user_login FROM messages INNER JOIN users u ON messages.user_id = u.id WHERE room_id = ?";
            pst = conn.prepareStatement(query);
            pst.setLong(1, roomId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                list.add(message);
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

    public List<Message> getMessagesByUserId(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Message> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT *, user_login FROM messages INNER JOIN users u ON messages.user_id = u.id WHERE user_id = ?";
            pst = conn.prepareStatement(query);
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                list.add(message);
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

    public List<Message> getMessagesBySupportId(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Message> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages INNER JOIN support_actions s ON messages.user_id = s.user_id WHERE s.id = ?";
            pst = conn.prepareStatement(query);
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                setMessage(message, rs);
                list.add(message);

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
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE id = ?";
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

    public void deleteMessagesInRoom(Long roomId) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM messages WHERE room_id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, roomId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
        }
    }

    private void setMessage(Message message, ResultSet rs){
        try {
            Timestamp timestamp = rs.getTimestamp("message_date");
            Date date = new Date(timestamp.getTime());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String time = df.format(date);

            message.setId(rs.getLong("id"));
            message.setRoomId(rs.getLong("room_id"));
            message.setMessageText(rs.getString("message_text"));
            message.setMessageDate(time);
            message.setUserId(rs.getLong("user_id"));
            message.setUserLogin(rs.getString("user_login"));
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
