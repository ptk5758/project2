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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>컬쳐쇼크페이지</title>
<link rel="stylesheet" type="text/css" href="/css/main.css">
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>
<script type="text/javascript">
	function logout(){
		location.href="/user/user_logout.jsp";
	}
	function community_go(title){
		location.href="/community_List.go?title="+title+""
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
	function admin_Userpage_go(){
		location.href="/admin_User.go";
	}
	function shop_View_Page_go(shopuid){
		location.href="/shop_View_Page.go?shopuid="+shopuid+"";
	}
	function admin_ShopPage_go(){
		location.href="/admin_Shop.go";
	}
	function admin_EventPage_go(){
		location.href="/admin_EventPage.go";
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
	function community_go(title){
		location.href="/community/community_list.go?title="+title+""
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
					<div onclick="admin_page_go()" style="cursor: pointer" >관리자페이지</div>
					<%}else{ %>
					<div>3</div>
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

	<div class="admin_main">
		<div class="admin_menu">
			<div class="admin_menu_item" onclick="admin_Userpage_go()">유저관리</div>
			<div class="admin_menu_item">일반게시물관리</div>
			<div class="admin_menu_item">책게시물관리</div>
			<div class="admin_menu_item" onclick="admin_ShopPage_go()">굿즈샵관리</div>
			<div class="admin_menu_item">베너관리</div>
			<div class="admin_menu_item">1</div>
			<div class="admin_menu_item" onclick="admin_EventPage_go()">이벤트관리</div>
		</div>
	</div>