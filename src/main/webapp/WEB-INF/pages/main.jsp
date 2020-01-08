<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
<title>B8DC</title>
</head>
<body>
<h2>INPUT:</h2>
<br>
<textarea name="incData" rows="50" cols="33" placeholder="Скопировать пакет сюда..."/>
<br><br>
<input type="submit" value="Send Request">
<br><br>
<h2>RESULT:</h2>
<textarea name="incData" rows="50" cols="33" placeholder="Результат..." readonly/>
</body>
</html>