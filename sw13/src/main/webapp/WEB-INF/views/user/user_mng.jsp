<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user_mng</title>
</head>
<body>
   <h2>사용자 관리</h2>
   <form action="/ehr/user/doSave.do" method="post">
      <label>아이디</label><input type="text" name="userId">
      <label>이름</label><input type="text" name="name">
      <input type="submit" value="전송">
   </form>
      
   ${userId } ${name }
</body>
</html>