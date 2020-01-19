<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml"
     xmlns:th="http://www.thymeleaf.org">
<head>
<link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
<title>B8DC</title>
</head>
<body>
<h2>INPUT:</h2>
<c:url value="/" var="var"/>
<form action="${var}" method="POST">
<textarea name="convert" rows="20" cols="60" placeholder="Скопировать пакет сюда..."/>
</textarea>
<input type="submit" value="Отправить" >
</form>
<h2>RESULT:</h2>
<textarea name="outData" rows="20" cols="60" placeholder="Результат..." readonly/>
"${outcome}"
</textarea>
</body>
</html>