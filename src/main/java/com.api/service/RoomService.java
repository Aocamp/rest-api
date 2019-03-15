package com.api.service;

import com.api.database.HikariCPDataSource;
import com.api.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RoomService {
    private static final Map<String, Room> allRoomsMap = new HashMap<>();
    private static final Map<String, Room> roomMap = new HashMap<>();

    public static List<Room> getAllRooms(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms");
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getString("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setUserId(rs.getString("user_id"));
                allRoomsMap.put(room.getId(), room);
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
        Collection<Room> rooms = allRoomsMap.values();
        List<Room> list = new ArrayList<>(rooms);
        return list;
    }

    public static Room getRoomById(String roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where id = ?");
            pst.setString(1, roomId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getString("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setUserId(rs.getString("user_id"));
                roomMap.put(room.getId(), room);
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
        System.out.println(roomMap.get(roomId));
        return roomMap.get(roomId);
    }

    public static Room getRoomByUserId(String userId){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = HikariCPDataSource.getConnection();
            pst = conn.prepareStatement("SELECT * FROM rooms where user_id = ?");
            pst.setString(1, userId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getString("id"));
                room.setRoomName(rs.getString("room_name"));
                room.setUserId(rs.getString("user_id"));
                roomMap.put(room.getUserId(), room);
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
        System.out.println(roomMap.get(userId));
        return roomMap.get(userId);
    }

    public static void deleteRoom(String roomId){
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM rooms WHERE id = ?";
            pst = conn.prepareStatement(delete);
            pst.setString(1, roomId);
            pst.executeUpdate();
            allRoomsMap.remove(roomId);
            roomMap.remove(roomId);
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

