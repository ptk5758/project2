	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
<script>
	function userInfoGo(useruid){
		location.href="/admin_UserInfo.go?useruid="+useruid+"";
	}
	function msg_warning(){
		console.log("gdgd");
		userInfo.action="/user_Warning_Msg.do";
		userInfo.method="post";
		userInfo.submit();
	}
</script>
	<h1 align="center">유저관리</h1>
	<div class="admin_border">
		<div class="admin_border_left">
			<div class="admin_left_menu">유저목록</div>
			<div class="admin_left_menu">유저목록</div>
			<div class="admin_left_menu">유저목록</div>
			<div class="admin_left_menu">유저목록</div>
			<div class="admin_left_menu">유저목록</div>
			<div class="admin_left_menu">유저목록</div>
		</div>
		<div class="admin_border_right">
			<div class="admin_UserOption_Button">
				<div class="UserOption_Button_Item" onclick="msg_warning()">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
			</div>
			<div class="admin_user_list admin_user_list_top">
				<div class="admin_user_list_num">번호</div>
				<div class="admin_user_list_name">이름</div>
				<div class="admin_user_list_nickname">닉네임</div>
				<div class="admin_user_list_id">아이디</div>
				<div class="admin_user_list_level">등급</div>
				<div class="admin_user_list_signdate">생성일</div>
				<div class="admin_user_list_active">활동사항</div>
				<div class="admin_user_list_warning">경고회수</div>
				<div class="admin_user_list_check">체크</div>
			</div>
			<c:set var="total_list" value="${count }"/>
			<c:set var="i" value="0" />
			<form name="userInfo">
			<c:forEach var="user" items="${v }">			
			<div class="admin_user_list">
				<div class="admin_user_list_num">No.${total_list }</div>
				<div class="admin_user_list_name">${user.username }</div>
				<div class="admin_user_list_nickname" onclick="userInfoGo('${user.useruid}')">${user.usernickname }</div>
				<div class="admin_user_list_id">${user.userid }</div>
				<div class="admin_user_list_level">${user.userlevel }</div>
				<div class="admin_user_list_signdate">${user.signdate }</div>
				<div class="admin_user_list_active">커뮤니티 글:5 책 추천 :3 굿즈샵 글:<b>${shopListCount[i] }</b></div>
				<div class="admin_user_list_warning">${warningCount[i] }</div>
				<div class="admin_user_list_check"><input name="userid" type="checkbox" value="${user.userid }"></div>
			</div>
			<c:set var="i" value="${i+1}" />
			<c:set var="total_list" value="${total_list - 1 }" />
			</c:forEach>
			</form>
		</div>
	</div>
<%@ include file="/admin/include/footer.jsp" %>
