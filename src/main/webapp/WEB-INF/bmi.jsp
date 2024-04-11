<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BMI計算機</title>
</head>
<body>
	<h1>身長と体重を入力してください</h1>
	
	<form action="./" method="post">
	    身長(feet)：<input type="text" name="feetHeight" value="${current.height}"><br>
		体重（pounds）：<input type="text" name="poundsWeight" value="${current.weight}"><br>
		<button>計算</button><br
	</form>
	BMI：${current.bmi}
	<h2>計算履歴</h2>
	<ul>
	<c:forEach var="bmi" items="${history}">
		<li>[${ bmi.createdDate }] ${ bmi.height }feet, ${ bmi.weight }pound, BMI: ${ bmi.bmi }</li>
	</c:forEach>
	</ul>
</body>
</html>
