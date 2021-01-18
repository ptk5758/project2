<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<script>
	function userUpdate(){
		location.href="/userUpdate.do";
	}
	function myShopList(){
		location.href="/myShopList.go";
	}
</script>
	<div class="mypage">
		<div><h1>마이페이지</h1></div>
		<div class="mypage_border">
			<div class="mypage_left">
				<div class="mypage_left_item" onclick="myPage_Go()">회원정보</div>
				<div class="mypage_left_item">책관리</div>
				<div class="mypage_left_item">게시물관리</div>
				<div class="mypage_left_item" onclick="myShopList()">상품관리</div>			
			</div>
			<div class="mypage_right">
				<div class="mypage_right_item">
					<p>이름</p>
					<p><b>${userbean.username}</b></p>
				</div>
				<div class="mypage_right_item">
					<p>아이디</p>
					<p><b>${userbean.userid}</b></p>
				</div>
				<div class="mypage_right_item">
					<p>닉네임</p>
					<p><b>${userbean.usernickname}</b></p>
				</div>
				<div class="mypage_right_item">
					<p>등급</p>
					<p><b>${userbean.userlevel}</b></p>
				</div>
				<div class="mypage_right_item userupdateButton">
					<div onclick="userUpdate()">회원정보 수정하러가기</div>
				</div>
				<div class="mypage_right_item">
					<div>내가 등록한 게시물수 : 1</div>
				</div>
				<div class="mypage_right_item">
					<div>내가 등록한 책 수 : 1</div>
				</div>
				<div class="mypage_right_item">
					<div>내가 등록한 상품수 : ${total_shoplist }</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/include/footer.jsp"%>