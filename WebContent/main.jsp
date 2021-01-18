<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
	<div class="mainContainer">
		<div class="mainContainer_Item">
			<div class="mainContainer_Title">커뮤니티</div>
			<div class="mainContainer_Title_item">
				<span onclick="community_go('notice')">공지사항</span>
				<span onclick="community_go('free')">자유게시판</span>
				<span onclick="community_go('qna')">Q&A게시판</span>
				<span onclick="community_go('fun')">유머게시판</span>
			</div>
		</div>
		<div class="mainContainer_Item">
			<div class="mainContainer_Title">소설/웹툰</div>
			<div class="mainContainer_Title_item">
				<span onclick="contents_go('webnv')">연재웹소설</span>
				<span onclick="contents_go('webtoon')">연재웹툰</span>
				<span onclick="contents_go('endwebnv')">완결웹소설</span>
				<span onclick="contents_go('endwebtoon')">완결웹툰</span>
			</div>
		</div>
		<div class="mainContainer_Item">
			<div class="mainContainer_Title">이벤트</div>
			<div class="mainContainer_Title_item">
				<span onclick="attend_go()">출석체크</span>
				<span onclick="eventPage_go()">진행중인이벤트</span>
			</div>		
		</div>
		<div class="mainContainer_Item">
			<div class="mainContainer_Title">굿즈샵</div>
			<div class="mainContainer_Title_item">
				<span onclick="shop_go('')">전체</span>
				<span onclick="shop_go('accessories')">악세서리</span>
				<span onclick="shop_go('clothes')">의류</span>
				<span onclick="shop_go('household')">생활용품</span>
				<span onclick="shop_go('other')">기타</span>
			</div>
		</div>
	</div>	
<%@ include file="/include/footer.jsp" %>