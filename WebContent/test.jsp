<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
	<div>
		<form method="post" action="/test.do">
			<input type="checkbox" name="rice" value="돼지국밥">돼지국밥
			<input type="checkbox" name="rice" value="순대국밥">순대국밥
			<input type="checkbox" name="rice" value="콩나물국밥">콩나물국밥
			<input type="checkbox" name="rice" value="선지국밥">선지국밥
			<input type="submit" value="제출하기">
		</form>
	</div>
</body>
</html>
