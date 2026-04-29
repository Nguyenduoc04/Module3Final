package vn.codegym.module3.dao;


import vn.codegym.module3.model.MatBang;
import vn.codegym.module3.utils.DBConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class MatBangDAO {
    Connection conn = DBConnectionUtil.getConnection();

        public MatBangDAO() {
            conn = DBConnectionUtil.getConnection();
        }

        // CHECK TRÙNG MÃ
        public boolean exists(String ma) throws SQLException {
            String sql = "SELECT * FROM matbang WHERE maMatBang=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }

        // INSERT
        public void insert(MatBang m) throws SQLException {
            String sql = "INSERT INTO matbang(maMatBang,trangThai,dienTich,tang,loaiMatBang,gia,ngayBatDau,ngayKetThuc) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getMaMatBang());
            ps.setString(2, m.getTrangThai());
            ps.setDouble(3, m.getDienTich());
            ps.setInt(4, m.getTang());
            ps.setString(5, m.getLoaiMatBang());
            ps.setDouble(6, m.getGia());
            ps.setDate(7, new java.sql.Date(m.getNgayBatDau().getTime()));
            ps.setDate(8, new java.sql.Date(m.getNgayKetThuc().getTime()));
            ps.executeUpdate();
        }

        // LIST + SEARCH
        public List<MatBang> find(String loai, String tang, String gia, String start, String end) throws SQLException {
            String sql = "SELECT * FROM matbang WHERE 1=1";

            if (loai != null && !loai.isEmpty()) sql += " AND loaiMatBang=?";
            if (tang != null && !tang.isEmpty()) sql += " AND tang=?";
            if (gia != null && !gia.isEmpty()) sql += " AND gia<=?";
            if (start != null && !start.isEmpty()) {
                sql += " AND ngayBatDau >= ?";
            }
            if (end != null && !end.isEmpty()) {
                sql += " AND ngayKetThuc <= ?";
            }
            sql += " ORDER BY dienTich ASC";

            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;
            if (loai != null && !loai.isEmpty()) ps.setString(index++, loai);
            if (tang != null && !tang.isEmpty()) ps.setInt(index++, Integer.parseInt(tang));
            if (gia != null && !gia.isEmpty()) ps.setDouble(index++, Double.parseDouble(gia));
            if (start != null && !start.isEmpty())
                ps.setDate(index++, java.sql.Date.valueOf(start));

            if (end != null && !end.isEmpty())
                ps.setDate(index++, java.sql.Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            List<MatBang> list = new ArrayList<>();
            while (rs.next()) {
                MatBang m = new MatBang();
                m.setId(rs.getInt("id"));
                m.setMaMatBang(rs.getString("maMatBang"));
                m.setTrangThai(rs.getString("trangThai"));
                m.setDienTich(rs.getDouble("dienTich"));
                m.setTang(rs.getInt("tang"));
                m.setLoaiMatBang(rs.getString("loaiMatBang"));
                m.setGia(rs.getDouble("gia"));
                m.setNgayBatDau(rs.getDate("ngayBatDau"));
                m.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                list.add(m);
            }
            return list;
        }

        // DELETE
        public void delete(int id) throws SQLException {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM matbang WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

