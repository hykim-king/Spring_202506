<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>회원목록</h2>
    
    <table border="1">
        <thead>
            <th>번호</th>
            <th>회원ID</th>
            <th>이름</th>
        </thead>
        <tbody>
            <c:forEach var="vo" items="${list }">
              <tr>
                <td>${vo.getNo() }</td>
                <td>${vo.userId }</td>
                <td>${vo.name }</td>
              </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>