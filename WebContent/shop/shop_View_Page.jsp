<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function bukkit_Add(){
		bukkit.submit();
	}
	function size_up(item){
		item.style.width = 38+'px';
		item.style.height = 38+'px';
	}
	function size_down(item){
		item.style.width = 22+'px';
		item.style.height = 22+'px';
	}
	function usercomment_delete(commentuid, shopuid){
		location.href="/usercomment_Delete.do?commentuid="+commentuid+"&shopuid="+shopuid+"";
	}
	function shopLike(shopuid){
		location.href="/shopItem_like.do?shopuid="+shopuid+"";
	}
</script>
	<div class="shop_view">
		<div class="shop_view_item">
			<div class="view_item_stat">
				<div class="item_img"><img src="/shopUpload/${bean.img_1 }"></div>
				<div class="item_text">
					<div class="item_text_text">
						<div class="item_text_Subject">상품이름: </div>
						<div>${bean.itemname }</div>
					</div>
					<div class="item_text_text">
						<div class="item_text_Category">카테고리</div>
						<div>${bean.itemcategory }</div>
					</div>
					<div class="item_text_text">
						<div class="item_text_Price">가격: </div>
						<div><fmt:formatNumber var="price" value="${bean.itemprice }" type="currency"/>${price }원</div>
					</div>
					<div class="item_text_text">
						<div class="item_text_Like">좋아요 :</div>
						<div>${likestack }</div>
					</div>
					<div class="item_text_text">
						<div class="bukkit_count">
							<form action="/bukkit_Add.do" method="post" name="bukkit">
								<input type="number" name="itemcount" min="1" value="1" style="width:50px; height: 2em;">
								<input type="hidden" name="shopuid" value="${bean.shopuid }">
								<input type="hidden" name="url" value="/shop_View_Page.go">
							</form>
						</div>
						<div class="bukkit_button">구매하기</div>
						<div class="bukkit_button" onclick="bukkit_Add()">장바구니</div>
						<div class="bukkit_button" onclick="shopLike('${bean.shopuid}')">좋아요!</div>
					</div>
				</div>
			</div>
		</div>
		<h1>상품상세설명</h1>
		<div class="shop_view_item">
			<div class="shop_comment">
				<c:if test='${bean.img_2 != ""}'><div class="shop_comment_img"><img src="/shopUpload/${bean.img_2 }"></div></c:if>
				<c:if test='${bean.img_3 != ""}'><div class="shop_comment_img"><img src="/shopUpload/${bean.img_3 }"></div></c:if> 
				<c:if test='${bean.itemcomment != ""}'><div class="shop_comment_text">${bean.itemcomment }</div></c:if>
			</div>
		</div>
		<div class="shop_view_item">
			<div class="shop_view_user_border">
				<div class="shop_view_user">
					<div class="shop_view_user_text">작성자</div>
					<div class="shop_view_user_item">${bean.user }</div>
				</div>
				<div class="shop_view_user">
					<div class="shop_view_user_text">작성날짜</div>
					<div class="shop_view_user_item">${fn:substring(bean.signdate,0,10)}</div>
				</div>
			</div>
		</div>
		<h2>댓글</h2>
		<div class="shop_view_user_comment">
			<form class="shop_view_user_comment_form" method="post" action="/user_Comment.do" enctype="multipart/form-data">
			<input type="hidden" name="shopuid" value="${bean.shopuid }">
				<div class="shop_user_comment_user">${session_id } 님</div>
				<div class="shop_user_comment_comment"><textarea name="usercomment" class="user_comment_textarea"></textarea></div>
				<div class="shop_user_comment_option">
					<div><input type="file" name="img_1" value="파일첨부"></div>
					<div><input type="submit" value="글남기기"></div>
				</div>
			</form>
			<c:forEach var="usercomment" items="${v }">
			<div class="shop_view_user_comment_form">
				<div class="shop_user_comment_user">${usercomment.user }</div>
				<div class="shop_user_comment_comment">
					<c:if test="${usercomment.img_1 != ''}">
					<div class="shop_user_comment_img"><img src="/shopUpload/usercomment/${usercomment.img_1 }"></div>
					</c:if>
					<div style="padding-left: ${usercomment.reply == 0 ? 0:50}px">${usercomment.usercomment }</div>
					<c:if test="${usercomment.user == session_id }">
					<div class="shop_delete_option">
						<div><img class="usercomment_delete" src="/img/update_icon.png" onmouseover="size_up(this)" onmouseout="size_down(this)"></div>
						<div><img class="usercomment_delete" src="/img/delete.png" onclick="usercomment_delete('${usercomment.commentuid}','${shopuid }')" onmouseover="size_up(this)" onmouseout="size_down(this)"></div>
					</div>
					</c:if>
					<form method="post" action="/usercomment_reply.do">
					<input type="hidden" name="commentuid" value="${usercomment.commentuid}">
					<input type="hidden" name="shopuid" value="${bean.shopuid }">
					<input type="hidden" name="fid" value="${usercomment.fid }">
					<c:if test="${usercomment.reply == 0}">
					<div class="shop_reply">
						<div>${session_id }님</div>
						<div><input name="comment"></div>
						<div><input type="submit" value="답글달기"></div>
					</div>
					</c:if>
					</form>
				</div>
				<div class="shop_user_comment_option">${usercomment.signdate }</div>
			</div>
			</c:forEach>
		</div>
	</div>
<%@ include file="/include/footer.jsp" %>