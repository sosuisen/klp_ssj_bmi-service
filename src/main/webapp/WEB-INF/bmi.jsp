<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BMI計算機</title>
</head>
<body>
	<h1>身長と体重を入力してください</h1>
	
	<form action="./" method="post">
	    身長(cm)：<input type="text" name="cmHeight" value="${current.height}"><br>
		体重(kg)：<input type="text" name="kgWeight" value="${current.weight}"><br>
		<button>計算</button><br
	</form>
	BMI：${current.bmi}
	<h2>計算履歴</h2>
	<ul>
	<c:forEach var="bmi" items="${history}">
		<li>[${ bmi.createdDate }] ${ bmi.height }cm, ${ bmi.weight }kg, BMI: ${ bmi.bmi }</li>
	</c:forEach>
	</ul>
</body>
</html>
