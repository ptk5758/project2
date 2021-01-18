<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>
<script>
	function bukkit_item_delete(bukkituid){
		location.href="/bukkit_item_delete.do?bukkituid="+bukkituid+"";
	}
	function view_Page(shopuid){
		location.href="/shop_View_Page.go?shopuid="+shopuid+"";
	}
</script>

	<div class="shop_bukkit">
		<div class="shop_bukkit_border">
			<div style="height: 200px;">
				<img src="/img/bukkit2_1.png">
			</div>
			<div class="bukkit_border">
				<div class="shop_bukkit_item bukkittop">
					<div class="bukkit_item_num">번호</div>
					<div class="bukkit_item_img">이미지</div>	
					<div class="bukkit_item_name">상품명</div>
					<div class="bukkit_item_count">개수</div>
					<div class="bukkit_item_price">개당 가격</div>
					<div class="bukkit_item_totalparice">총가격</div>
					<div class="bukkit_item_postinguser">판매자 정보</div>
				</div>	
				<c:set var="total_list_num" value="${total_list }"/>
				<c:set var="totalmoney" value="0"/>
				<c:forEach var="item" items="${v }">
				<fmt:formatNumber type="currency" var="money" value="${item.itemprice }"/>
				<fmt:formatNumber type="currency" var="allmoney" value="${item.itemprice * item.itemcount }"/>
				<div class="shop_bukkit_item">
					<div class="bukkit_item_num">${total_list_num }</div>
					<div class="bukkit_item_img"><img onclick="view_Page('${item.shopuid}')" src="/shopUpload/${item.img_s }"/></div>
					<div class="bukkit_item_name">
						<div onclick="view_Page('${item.shopuid}')">${item.itemname }</div>
						<div><img onclick="bukkit_item_delete('${item.bukkituid}')" class="bukkit_item_delete" src="/img/delete.png"></div>
					</div>
					<div class="bukkit_item_count">${item.itemcount }</div>
					<div class="bukkit_item_price">${money }</div>
					<div class="bukkit_item_totalparice">${allmoney}</div>
					<div class="bukkit_item_postinguser">${item.postinguser }</div>
				</div>
				<c:set var="totalmoney" value="${totalmoney + (item.itemprice * item.itemcount) }" />
				<fmt:formatNumber type="currency" var="totalmoney_fmt" value="${totalmoney }"/>
				<c:set var="total_list_num" value="${total_list_num-1 }"/>
				</c:forEach>		
			</div>
			<div class="bukkit_footer">
				<div class="bukkit_footer_item">총금액 : ${totalmoney_fmt}</div>
				<div class="bukkit_footer_item"><input type="button" value="결제하기"></div>
			</div>
			
		</div>
	</div>
<%@ include file="/include/footer.jsp"%>