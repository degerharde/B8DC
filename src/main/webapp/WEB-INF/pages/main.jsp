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
<textarea name="incData" rows="20" cols="60" placeholder="Скопировать пакет сюда..."/>
</textarea>
<br><br>
<input type="submit" value="Send Request">
<br><br>
<h2>RESULT:</h2>
<textarea name="incData" rows="20" cols="60" placeholder="Результат..." readonly/>
</textarea>
</body>
</html>