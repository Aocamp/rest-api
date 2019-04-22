package com.api.dao;

import com.api.database.HikariCPDataSource;
import com.api.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao implements BaseDao<Room> {
    @Override
    public List<Room> getAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Room> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms");
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                setRoom(room, rs);
                list.add(room);
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
    public Room getById(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Room room = new Room();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where id = ?");
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                setRoom(room, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return room;
    }

    public Room getRoomByUserId(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Room room = new Room();

        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where user_id = ?");
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                setRoom(room, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }

        return room;
    }

    public Room addRoom(Room room){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT id FROM rooms WHERE room_name = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, room.getRoomName());
            rs = pst.executeQuery();
            if (rs.wasNull()) {
                String add = "INSERT INTO rooms (room_name, user_id) VALUES (?, ?)";
                pst = conn.prepareStatement(add);
                pst.setString(1, room.getRoomName());
                pst.setLong(2, room.getUserId());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return room;
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM rooms WHERE id = ?";
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

    private void setRoom(Room room, ResultSet rs){
        try {
            room.setId(rs.getLong("id"));
            room.setRoomName(rs.getString("room_name"));
            room.setUserId(rs.getLong("user_id"));
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
