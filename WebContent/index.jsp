<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>
	<div class="index_Main">
		<div id="mainbutton" style="position:absolute; background: linear-gradient(#e66465, #9198e5); width: 550px;height: 150px;left: 27%;top: 38%; z-index: 10; border-radius: 15px; overflow: hidden; cursor: pointer; opacity: 1;"><!--메인화면으로 -->
			<div id="maintext" style="color:#474747; position: absolute; left: 16%; top: 30%; font-size: 48px; font-family: '넥슨고딕';">메인화면으로가기</div>
		</div>
		<div class="circle"><!--럭기짱  -->
			<img class="circle_item" src="/img/indeximg2.jpg">
		</div>
		<div class="circle2"><!-- 나혼자만 레벨업  -->
			<img class="circle_item2" src="/img/indeximg3.jpg">
		</div>
		<div class="circle" style="width: 380px;height: 500px;left: 64%;top: 20%; z-index: 9;"><!--달빛 조각사-->
			<img class="circle_item" style="position: absolute; left: 0%; top: -10%;" src="/img/indeximg4.jpg">
		</div>
		<div class="circle" style="width: 330px;height: 450px;left: 4%;top: 26%; z-index: 8;"><!--전독시 -->
			<img class="circle_item" style="position: absolute; left: -28%; top: -8%;" src="/img/indeximg5.jpg">
		</div>
		<div class="circle" style="width: 400px;height: 250px;left: 65%;top: 6%; z-index: 7;"><!--북두의권 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: -1%;" src="/img/indeximg6.jpg">
		</div>
		<div class="circle" style="width: 330px;height: 200px;left: 37%;top: 3%; z-index: 7;"><!--붕어된썰 푼다 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: -7%;" src="/img/indeximg7.jpg">
		</div>
		<div class="circle" style="width: 250px;height: 350px;left: 28%;top: 27%; z-index: 7;"><!--더복서 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: 0%;" src="/img/indeximg8.jpg">
		</div>
		<div class="circle" style="width: 120px;height: 100px;left: 27%;top: 68%; z-index: 9;"><!--인생존망 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: 0%;" src="/img/indeximg10.jpg">
		</div>
		<div class="circle" style="width: 250px;height: 200px;left: 78%;top: 71%; z-index: 6;"><!--테러맨 -->
			<img class="circle_item" style="position: absolute; left: -7%; top: 0%;" src="/img/indeximg11.jpg">
		</div>
		<div class="circle" style="width: 500px;height: 400px;left: -8%;top: 65%; z-index: 8;"><!--안나라수마나라 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: 0%;" src="/img/indeximg14.jpg">
		</div>
		<div class="circle" style="width: 250px;height: 400px;left: 38%;top: 45%; z-index: 8;"><!--삼봉이발소 -->
			<img class="circle_item" style="position: absolute; left: -15%; top: 0%;" src="/img/indeximg15.jpg">
		</div>
		<div class="circle" style="width: 200px;height: 300px;left: -5%;top: -4%; z-index: 8;"><!--신석기녀 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: 0%;" src="/img/indeximg12.jpg">
		</div>
		<div class="circle" style="width: 300px;height: 150px;left: 53%;top: 80%; z-index: 7;"><!--마음의소리 -->
			<img class="circle_item" style="position: absolute; left: 0%; top: 0%;" src="/img/indeximg16.jpg">
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#mainbutton').click(function(){
			location.href="main.jsp";
		});
		$('#mainbutton').mouseover(function(){
			$('#maintext').stop().animate({"font-size":"52px","left":"13%","top":"28%","opacity":1},1000,'swing',
					function(){
						$('#maintext').css("color","#fff");
					});
			});
		$('#mainbutton').mouseout(function(){
			$('#maintext').stop().animate({"font-size":"48px","left":"16%","top":"30%","opacity":0.9},1000,'swing',
					function(){
						$('#maintext').css("color","#474747");
					});
			});
		});
</script>

</html>	