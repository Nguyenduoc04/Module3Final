<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Danh sách mặt bằng</title>
</head>
<body>

<h3>Quản lý mặt bằng</h3>

<!-- SEARCH -->
<form method="get" action="matbang">
    Loại mặt bằng:
    <select name="loai">
        <option value="">--Chọn--</option>
        <option>Cho thuê</option>
        <option>Trọn gói</option>
    </select>

    &nbsp;&nbsp;

    Tầng:
    <select name="tang">
        <option value="">--Chọn--</option>
        <c:forEach begin="1" end="15" var="i">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select>

    <br><br>

    Ngày cho thuê:
    Từ <input type="date" name="start" placeholder="dd/MM/yyyy">
    Đến <input type="date" name="end" placeholder="dd/MM/yyyy">

    <br><br>

    Giá <= <input type="text" name="gia">

    <button type="submit">Tìm kiếm</button>
</form>

<br>


<br><br>

<!-- TABLE -->
<table border="1" cellpadding="5">
    <tr>
        <th>Mã MB</th>
        <th>Diện tích</th>
        <th>Trạng thái</th>
        <th>Tầng</th>
        <th>Loại văn phòng</th>
        <th>Giá cho thuê</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Xóa</th>
    </tr>

    <c:forEach var="m" items="${list}">
        <tr>
            <td>${m.maMatBang}</td>
            <td><fmt:formatNumber value="${m.dienTich}" pattern="#"/></td>
            <td>${m.trangThai}</td>
            <td>${m.tang}</td>
            <td>${m.loaiMatBang}</td>
            <td><fmt:formatNumber value="${m.gia}" pattern="#,###"/></td>
            <td>
                <fmt:formatDate value="${m.ngayBatDau}" pattern="dd/MM/yyyy"/>
            </td>
            <td>
                <fmt:formatDate value="${m.ngayKetThuc}" pattern="dd/MM/yyyy"/>
            </td>
            <td>
                <a href="matbang?action=delete&id=${m.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng ${m.maMatBang}?')">
                    Xóa
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="matbang?action=add">Thêm mới</a>
</body>
</html>