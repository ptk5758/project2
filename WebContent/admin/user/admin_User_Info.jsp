<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
<script>
	function userInfoGo(useruid){
		location.href="/admin_UserInfo.go?useruid="+useruid+"";
	}
</script>
	<h1 align="center">유저정보</h1>
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
			<div class="UserInfo_border">
				<div class="UserInfo_border_left">
				<c:set var="user" value="${userbean }" />
					<h2 align="center">유저정보</h2>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">이름: </div>
						<div>${user.username }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">아이디: </div>
						<div>${user.userid }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">닉네임: </div>
						<div>${user.usernickname }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">연락처: </div>
						<div>${user.userphone }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">이메일주소: </div>
						<div>${user.useremail }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">성별: </div>
						<div>${user.usergender }</div>
					</div>
					<form class="UserInfo_Item" action="/admin_UserLevel_Change.do" method="post">
					<input type="hidden" name="useruid" value="${user.useruid }">
						<div class="UserInfo_Item_text">등급: </div>
						<div>${user.userlevel }</div>
						<div><input class="levelinput" name="userlevel" type="number" min="0" max="11" value="${user.userlevel }"></div>
						<div><input class="levelbutton" type="submit" value="등급변경"></div>
					</form>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">나이: </div>
						<div>${user.userage }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">우편번호: </div>
						<div>${user.useraddress }</div>
					</div>
					<div class="UserInfo_Item">
						<div class="UserInfo_Item_text">집주소: </div>
						<div>${user.useraddress_1 }</div>
					</div>
				</div>
				<div class="UserInfo_border_right">
					<h2 align="center">활동정보</h2>
					<div class="UserActive_Info">
						<div class="UserActive_Info_Item">
							<div class="UserActive_Info_Text">굿즈샵 활동정보</div>
							<div class="UserActive_overflow">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">번호</div>
									<div class="overflow_Subject">제목</div>
									<div class="overflow_Signdate">작성일자</div>
									<div class="overflow_Ref">조회수</div>
									<div class="overflow_Like">좋아요</div>
									<div class="overflow_check">체크</div>
								</div>
								<c:set var="shopCount" value="${shop_count }" />
								<c:set var="i" value="0" />
								<c:forEach var="shop_list" items="${shop_v }">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">${shopCount }</div>
									<div class="overflow_Subject" onclick="shop_View_Page_go('${shop_list.shopuid}')">${shop_list.itemsubject }</div>
									<div class="overflow_Signdate">${fn:substring(shop_list.signdate,0,10)}</div>
									<div class="overflow_Ref">${shop_list.ref }</div>
									<div class="overflow_Like">${likeStack[i] }</div>
									<div class="overflow_check"><input type="checkbox"></div>
								</div>
								<c:set var="shopCount" value="${shopCount-1 }" />
								<c:set var="i" value="${i+1 }" />
								</c:forEach>
							</div>
						</div>
						<div class="UserActive_Info_Item">
							<div class="UserActive_Info_Text">굿즈샵 활동정보</div>
							<div class="UserActive_overflow">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">번호</div>
									<div class="overflow_Subject">제목</div>
									<div class="overflow_Signdate">작성일자</div>
									<div class="overflow_Ref">조회수</div>
									<div class="overflow_Like">좋아요</div>
									<div class="overflow_check">체크</div>
								</div>
								<c:set var="shopCount" value="${shop_count }" />
								<c:set var="i" value="0" />
								<c:forEach var="shop_list" items="${shop_v }">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">${shopCount }</div>
									<div class="overflow_Subject" onclick="shop_View_Page_go('${shop_list.shopuid}')">${shop_list.itemsubject }</div>
									<div class="overflow_Signdate">${fn:substring(shop_list.signdate,0,10)}</div>
									<div class="overflow_Ref">${shop_list.ref }</div>
									<div class="overflow_Like">${likeStack[i] }</div>
									<div class="overflow_check"><input type="checkbox"></div>
								</div>
								<c:set var="shopCount" value="${shopCount-1 }" />
								<c:set var="i" value="${i+1 }" />
								</c:forEach>
							</div>
						</div>
						<div class="UserActive_Info_Item">
							<div class="UserActive_Info_Text">굿즈샵 활동정보</div>
							<div class="UserActive_overflow">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">번호</div>
									<div class="overflow_Subject">제목</div>
									<div class="overflow_Signdate">작성일자</div>
									<div class="overflow_Ref">조회수</div>
									<div class="overflow_Like">좋아요</div>
									<div class="overflow_check">체크</div>
								</div>
								<c:set var="shopCount" value="${shop_count }" />
								<c:set var="i" value="0" />
								<c:forEach var="shop_list" items="${shop_v }">
								<div class="UserActive_overflow_Item">
									<div class="overflow_Num">${shopCount }</div>
									<div class="overflow_Subject" onclick="shop_View_Page_go('${shop_list.shopuid}')">${shop_list.itemsubject }</div>
									<div class="overflow_Signdate">${fn:substring(shop_list.signdate,0,10)}</div>
									<div class="overflow_Ref">${shop_list.ref }</div>
									<div class="overflow_Like">${likeStack[i] }</div>
									<div class="overflow_check"><input type="checkbox"></div>
								</div>
								<c:set var="shopCount" value="${shopCount-1 }" />
								<c:set var="i" value="${i+1 }" />
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/admin/include/footer.jsp" %>
