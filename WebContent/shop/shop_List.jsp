<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function posting_go(){
		location.href="/shop/shop_item_posting.jsp";
	}
	function shop_go2(title){
		location.href="/shop_list.go?title="+title+"";
	}
</script>
	<div class="shop">
		<h1 align="center">굿즈샵</h1>
		<div class="shop_title">
			<div class="shop_title_item" onclick="shop_go2('')">
				<div class="cub">전체</div>
				<div class="water">&nbsp;</div>
			</div>
			<div class="shop_title_item" onclick="shop_go2('clothes')">
				<div class="cub">의류</div>
				<div class="water">&nbsp;</div>
			</div>
			<div class="shop_title_item" onclick="shop_go2('accessories')">
				<div class="cub">악세서리</div>
				<div class="water">&nbsp;</div>
			</div>
			<div class="shop_title_item" onclick="shop_go2('household')">
				<div class="cub">생활용품</div>
				<div class="water">&nbsp;</div>
			</div>
			<div class="shop_title_item" onclick="shop_go2('other')">
				<div class="cub">기타</div>
				<div class="water">&nbsp;</div>
			</div>
			<script>
				$(document).ready(function(){
					$('.cub').mouseover(function(){
						$(this).next().stop().animate({'top':'0px'},1000);
					});
					$('.cub').mouseout(function(){
						$(this).next().stop().animate({'top':'52px'},1000);
					});
				});
			</script>
		</div>
		<div class="shop_border">
			<div class="shop_category">
				<c:set var="title_category" value="${title }" />
				<c:if test="${title_category=='clothes'}">
				<div class="shop_category_item">모자</div>
				<div class="shop_category_item">후드</div>
				<div class="shop_category_item">신발</div>
				<div class="shop_category_item">맨투멘</div>
				</c:if>
				<c:if test="${title_category=='accessories'}">
				<div class="shop_category_item">스티커</div>		
				</c:if>
				<c:if test="${title_category=='household'}">
				<div class="shop_category_item">머그컵</div>
				<div class="shop_category_item">마우스패드</div>
				</c:if>
				<c:if test="${title_category==''}">
				<div class="shop_category_item">머그컵</div>
				<div class="shop_category_item">마우스패드</div>
				<div class="shop_category_item">스티커</div>
				<div class="shop_category_item">모자</div>
				<div class="shop_category_item">후드</div>
				<div class="shop_category_item">신발</div>
				<div class="shop_category_item">맨투멘</div>
				</c:if>
			</div>
			<div class="shop_list">
				<c:forEach var="list" items="${v }">
					<div class="shop_item" onclick="shop_View_Page_go('${list.shopuid}')">
						<div class="shop_item_img">
							<c:if test="${list.active == '1' }"><img src="/shopUpload/${list.img_s }"></c:if>
							<c:if test="${list.active == '2' }">
								<div>
									<img style="filter: blur(3px); position: relative;" src="/shopUpload/${list.img_s }">
									<div style="position: absolute; top:23%; left:28%; font-size: 22px; width: 150px; font-weight: bold; color:#fff">블라인드 처리된 글입니다.</div>
								</div>
							</c:if>
						</div>
						<div class="shop_item_Subject">
							<c:if test="${list.active == '1' }">${list.itemsubject }</c:if>
							<c:if test="${list.active == '2' }">블라인드 처리된 글입니다.</c:if>
						</div>
						<div class="shop_item_ItemPrice">
							<c:if test="${list.active == '1' }">
								<fmt:formatNumber var="itemprice" value="${list.itemprice }" type="currency"/>${itemprice }원
							</c:if>
							<c:if test="${list.active == '2' }">
								<div>0원</div>
							</c:if>
						</div>
						<img class="clip" src="/img/ccc_1.png">
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="shop_option">
			<input class="shop_option_posting" type="button" value="상품등록하러가기" onclick="posting_go()"></div>
		</div>
	</div>
<%@ include file="/include/footer.jsp" %>
