package vn.codegym.module3.controller;

import vn.codegym.module3.dao.MatBangDAO;
import vn.codegym.module3.model.MatBang;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/matbang")
public class MatBangServlet extends HttpServlet {

    MatBangDAO dao = new MatBangDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            req.getRequestDispatcher("/view/add.jsp").forward(req, resp);
        }else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                dao.delete(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("matbang");
        } else {
            String loai = req.getParameter("loai");
            String tang = req.getParameter("tang");
            String gia = req.getParameter("gia");
            String start = req.getParameter("start");
            String end = req.getParameter("end");

            List<MatBang> list = null;
            try {
                list = dao.find(loai, tang, gia, start, end);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("list", list);
            req.getRequestDispatcher("view/list.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            String ma = req.getParameter("ma");

            if (dao.exists(ma)) {
                req.setAttribute("error", "Mã mặt bằng vừa thêm đã tồn tại");
                req.getRequestDispatcher("/view/add.jsp").forward(req, resp);
                return;
            }

            Date start = java.sql.Date.valueOf(req.getParameter("start"));
            Date end = java.sql.Date.valueOf(req.getParameter("end"));

            Calendar calStart = Calendar.getInstance();
            calStart.setTime(start);
            calStart.add(Calendar.MONTH, 6);

            if (calStart.getTime().after(end)) {
                req.setAttribute("error", "Phải thuê ít nhất 6 tháng");
                req.getRequestDispatcher("/view/add.jsp").forward(req, resp);
                return;
            }

            MatBang m = new MatBang();
            m.setMaMatBang(ma);
            m.setTrangThai(req.getParameter("trangThai"));
            m.setDienTich(Double.parseDouble(req.getParameter("dienTich")));
            m.setTang(Integer.parseInt(req.getParameter("tang")));
            m.setLoaiMatBang(req.getParameter("loai"));
            m.setGia(Double.parseDouble(req.getParameter("gia")));
            m.setNgayBatDau(start);
            m.setNgayKetThuc(end);

            dao.insert(m);

            resp.sendRedirect(req.getContextPath() + "/matbang");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/view/add.jsp").forward(req, resp);
        }
    }
}
