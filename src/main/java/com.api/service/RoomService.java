package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoomService {
    private Map<Long, Room> allRoomsMap = new HashMap<>();
    private Map<Long, Room> userRoomsMap = new HashMap<>();

    private void setRoom(Room room, ResultSet rs){
        try {
            room.setId(rs.getLong("id"));
            room.setRoomName(rs.getString("room_name"));
            room.setUserId(rs.getLong("user_id"));
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

    public List<Room> getAllRooms(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms");
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                setRoom(room, rs);
                allRoomsMap.put(room.getId(), room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<Room> rooms = allRoomsMap.values();
        List<Room> list = new ArrayList<>(rooms);
        return list;
    }

    public Room getRoomById(Long roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where id = ?");
            pst.setLong(1, roomId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                setRoom(room, rs);
                allRoomsMap.put(room.getId(), room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        System.out.println(allRoomsMap.get(roomId));
        return allRoomsMap.get(roomId);
    }

    public List<Room> getRoomsByUserId(Long userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where user_id = ?");
            pst.setLong(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                setRoom(room, rs);
                userRoomsMap.put(room.getId(), room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn, pst, rs);
        }
        Collection<Room> rooms = userRoomsMap.values();
        List<Room> list = new ArrayList<>(rooms);
        return list;
    }

    public void deleteRoom(Long roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM rooms WHERE id = ?";
            pst = conn.prepareStatement(delete);
            pst.setLong(1, roomId);
            pst.executeUpdate();
            allRoomsMap.remove(roomId);
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

