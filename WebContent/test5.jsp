<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% 
	/* String df ="fdsfsd";
	df.length(); */
%>
<body>
	<a href="/Test5Servlet"><h1>JSON 테스트</h1></a>
	<h1>AJAX click</h1>
	<h2 id="test">여기:</h2>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#test').click(function(){
				$.ajax({
					type : "GET",
					url : "/Test5Servlet",
					data : "name="+"박태광&tel="+"010-5530-4542",
					dataType : "json",
					error: function(){
						alert("연결실패");
					},
					success: function(data){
						var JSONArray = data.userinfo.split(',');
						/* var result = "";
						var JSONvalue = JSONArray.split(','); */
						console.log(JSONArray[0]);
						/* for(var i=0; i<JSONArray.length; i++){
							result += "<h1>"+JSONArray[0]+"</h1>";
						}
						var result2 = "<div>"+result+"</div>";
						$('#test').html(result2); */
					}
				});
			});
		})
	</script>
</body>
</html>