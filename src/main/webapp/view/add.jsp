<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mới mặt bằng</title>
</head>
<body>

<h3>Thêm mới mặt bằng</h3>

<form method="post" action="matbang" onsubmit="return validate()">

    Mã mặt bằng (*):
    <input name="ma"><br><br>

    Diện tích (*):
    <input name="dienTich"><br><br>

    Trạng thái (*):
    <select name="trangThai">
        <option>Trống</option>
        <option>Hạ tầng</option>
        <option>Đầy đủ</option>
    </select><br><br>

    Tầng (*):
    <select name="tang">
        <c:forEach begin="1" end="15" var="i">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select><br><br>

    Loại văn phòng (*):
    <select name="loai">
        <option>Cho thuê</option>
        <option>Trọn gói</option>
    </select><br><br>

    Mô tả chi tiết:
    <textarea rows="3" cols="40"></textarea><br><br>

    Giá cho thuê (*):
    <input name="gia"> VND<br><br>

    Ngày bắt đầu (*):
    <input type="date" name="start" placeholder="dd/MM/yyyy">

    Ngày kết thúc (*):
    <input type="date" name="end" placeholder="dd/MM/yyyy"><br><br>

    <button type="submit">Lưu</button>
    <button type="button" onclick="window.location='matbang'">Hủy</button>

</form>
<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
</body>

</html>