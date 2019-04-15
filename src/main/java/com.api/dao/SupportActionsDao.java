package com.api.dao;

import com.api.database.HikariCPDataSource;
import com.api.model.Support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupportActionsDao implements BaseDao<Support> {
    @Override
    public List<Support> getAll() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        List<Support> list = new ArrayList<>();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM support_actions";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Support support = new Support();
                setSupport(support, rs);
                list.add(support);
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
    public Support getById(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Support support = new Support();

        try {
            conn = HikariCPDataSource.getConnection();
            String query = "SELECT * FROM messages WHERE id = ? ";
            pst = conn.prepareStatement(query);
            pst.setLong(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                setSupport(support, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closePreparedStatement(pst);
            closeResultSet(rs);
        }
        return support;
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = HikariCPDataSource.getConnection();
            String delete = "DELETE FROM support_actions WHERE id = ?";
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

    private void setSupport(Support support, ResultSet rs){
        try {
            support.setId(rs.getLong("id"));
            support.setUserId(rs.getLong("user_id"));
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
