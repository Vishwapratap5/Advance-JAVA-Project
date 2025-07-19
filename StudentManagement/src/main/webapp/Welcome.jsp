
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student| Welcome</title>
</head>
<body>
<h2>Welcome, <%= session.getAttribute("email") %> 😎</h2>
<a href="Logout">Logout</a>
</body>
</html>
