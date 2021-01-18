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
	function shopList_Delete(shopuid){
		location.href="/shopList_Delete.do?shopuid="+shopuid+"";
	}
	function shopList_Update(shopuid){
		location.href="/shoplist_Update.do?shopuid="+shopuid+"";
	}
</script>
	<div class="mypage">
		<div><h1>상품관리</h1></div>
		<div class="mypage_border">
			<div class="mypage_left">
				<div class="mypage_left_item" onclick="myPage_Go()">회원정보</div>
				<div class="mypage_left_item">책관리</div>
				<div class="mypage_left_item">게시물관리</div>
				<div class="mypage_left_item" onclick="myShopList()">상품관리</div>			
			</div>
			<div class="mypage_right">
				<div class="mypage_Shoplist">
					<div class="mypage_ShopList_num list_top">번호</div>
					<div class="mypage_ShopList_img list_top">이미지</div>
					<div class="mypage_ShopList_name list_top">상품명</div>
					<div class="mypage_ShopList_option list_top">수정/삭제</div>
					<div class="mypage_ShopList_price list_top">가격</div>
					<div class="mypage_ShopList_signdate list_top">등록 날짜</div>
					<div class="mypage_ShopList_ref list_top">조회수</div>
				</div>
				<c:set var="count" value="${total_shop_list }" />
				<c:forEach var="shoplist" items="${v }">
				<fmt:formatNumber type="currency" var="money" value="${shoplist.itemprice }"/>
				<div class="mypage_Shoplist">
					<div class="mypage_ShopList_num">${count }</div>
					<div class="mypage_ShopList_img"><img src="/shopUpload/${shoplist.img_s }"></div>
					<div class="mypage_ShopList_name">${shoplist.itemname}</div>
					<div class="mypage_ShopList_option">
						<div><b style="cursor: pointer;" onclick="shopList_Update('${shoplist.shopuid}')">수정</b></div>
						<div>|</div>
						<div style="cursor: pointer;" onclick="shopList_Delete('${shoplist.shopuid}')"><b>삭제</b></div>
					</div>
					<div class="mypage_ShopList_price">${money}</div>
					<div class="mypage_ShopList_signdate">${fn:substring(shoplist.signdate,0,10)}</div>
					<div class="mypage_ShopList_ref">${shoplist.ref }</div>
				</div>
				<c:set var="count" value="${count-1 }" />
				</c:forEach>
			</div>
		</div>
	</div>
<%@ include file="/include/footer.jsp"%>