<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");

	String session_id = (String)session.getAttribute("session_id");
	String session_name = (String)session.getAttribute("session_name");
	String session_nickname = (String)session.getAttribute("session_nickname");
	String session_level = (String)session.getAttribute("session_level");
	if(session_level == null){session_level = "1";}
	String nowuri = (String)request.getRequestURI();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컬쳐쇼크페이지</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
<script>
	function logout(){
		location.href="/user/user_logout.jsp";
	}
	function community_go(title){
		location.href="/community/community_list.go?title="+title+""
	}
	function shop_go(title){
		location.href="/shop_list.go?title="+title+"";
	}
	function myPage_Go(){
		location.href="/myPage.go";
	}
	function admin_page_go(){
		location.href="/admin/admin_Main.jsp";
	}
	function shop_View_Page_go(shopuid){
		location.href="/shop_View_Page.go?shopuid="+shopuid+"";
	}
	function attend_go(){
		location.href="/attend.view";
	}
	function eventPage_go(){
		location.href="/eventPage.go";
	}
	function contents_go(title) {
		location.href = "/book/book_page.go?title="+title+"";
	}
</script>
</head>
<body>
	<div class="main_container">
		<div class="header">
			<div class="header_menu">
				<div class="header_menu_list">
					<div class="header_show_menu">커뮤니티</div>
					<div class="header_hidden_menu">
						<div onclick="community_go('notice')">공지사항</div>
						<div onclick="community_go('free')">자유게시판</div>
						<div onclick="community_go('qna')">Q&A게시판</div>
						<div onclick="community_go('fun')">유머게시판</div>
					</div>
				</div>
				<div class="header_menu_list">
					<div class="header_show_menu">소설/웹툰</div>
					<div class="header_hidden_menu">
						<div onclick="contents_go('webnv')">연재 웹소설</div>
						<div onclick="contents_go('webtoon')">연재 웹툰</div>
						<div onclick="contents_go('endwebnv')">완결 웹소설</div>
						<div onclick="contents_go('endwebtoon')">완결 웹툰</div>
					</div>
				</div>
				<div class="header_menu_list"> 
					<%if(session_level.equals("9")){ %>
					<div onclick="admin_page_go()">관리자페이지</div>
					<%}else{ %>
					<img style="width: 200px; height: 29px;" src="/img/logobutton2_1.png">
					<%} %>
				</div>
				<div class="header_menu_list">
					<div class="header_show_menu">굿즈샵</div>
						<div class="header_hidden_menu">
						<div onclick="shop_go('')">전체</div>
						<div onclick="shop_go('accessories')">악세서리</div>
						<div onclick="shop_go('clothes')">의류</div>
						<div onclick="shop_go('household')">생활용품</div>
						<div onclick="shop_go('other')">기타</div>
					</div>
				</div>
				<div class="header_menu_list">
					<div class="header_show_menu">이벤트</div>
					<div class="header_hidden_menu">
						<div onclick="attend_go()">출석체크</div>
						<div onclick="eventPage_go()">진행중인이벤트</div>
					</div>
				</div>
			</div>
			<div class="header_logo"><a href="/main.jsp"><img src="/img/logo1_1.png"></a></div>
		</div>
		<div class="middle">
			<div class="aside">
				<%if(session_id==null){ %>
				<div class="aside_login">
					<form method="post" action="User_Login.do">
						<input type="hidden" name="uri" value="<%=nowuri%>">
						<div class="aside_login_border"><input name="userid" placeholder="아이디"></div>
						<div class="aside_login_border"><input name="userpassword" placeholder="비밀번호"></div>
						<div class="aside_login_button"><input type="submit" value="로그인"></div>
					</form>
				</div>
				<%}else{ %>
				<div class="aside_usersession">
					<div class="aside_user_id">이름: <%=session_name %></div>
					<div class="aside_user_id">아이디: <%=session_id %></div>					
					<div class="aside_user_id">닉네임: <%=session_nickname %></div>
					<div class="aside_user_id">등급: <%=session_level %></div>				
				</div>
				<%} %>
				<%if(session_id == null){ %>
				<div class="aside_menu">
					<a class="aside_menu_item" href="/user/userid_Search.jsp">아이디찾기</a>
					<a class="aside_menu_item" href="/user/userPassword_Search.jsp">비밀번호찾기</a>
					<a class="aside_menu_item" href="/user/user_SignUp.jsp">회원가입</a>
					<!-- <a class="">로그아웃</a> -->
				</div>
				<%}else{ %>
				<div class="aside_menu">
					<div class="aside_menu_item" onclick="myPage_Go()">마이페이지</div>
					<a class="aside_menu_item" href="/shop_Show_Bukkit.go">장바구니</a>
					<a class="aside_menu_item" href="/msg/mailbox.go">쪽지</a>
					<!-- <a class="">로그아웃</a> -->
				</div>
				<%} %>
				<%if(session_id != null){ %>
				<div class="aside_logout" onclick="logout()">로그아웃</div>
				<%} %>
				<div class="aside_bookmark">
					<div class="aside_bookmark_item">
						<div class="aside_bookmark_text">즐겨찾기한책</div>
						<div class="aside_bookmark_item_text">1.고양이</div>
						<div class="aside_bookmark_item_text">2.웃음</div>
						<div class="aside_bookmark_item_text">3.하루10분코딩</div>
					</div>
				</div>
				<div class="aside_benner">
					<div class="aside_benner_img"><img src="/img/benner_2.png"></div>
				</div>
			</div>