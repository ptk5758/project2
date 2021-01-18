<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%
	String itemtitle = request.getParameter("itemtitle");
	if(itemtitle==null || itemtitle==""){
		itemtitle="other";
	}
%>
<script>
	function shop_list_Update(){
		shop_Update.submit();
	}
</script>
	<div class="shop_posting">
		<form name="shop_Update" action="/shop_list_Update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="shopuid" value="${shopuid }">
		<div class="shop_posting_item">
			<div><input name="itemsubject" value="${bean.itemsubject}"></div>
		</div>
		<div class="shop_posting_item">
			<div><input name="itemname" value="${bean.itemname }"></div>
		</div>
		<div class="shop_posting_item">
			<div><input name="itemprice" value="${bean.itemprice }"></div>
		</div>
		<div class="shop_posting_item">
			<div><textarea name="itemcomment">${bean.itemcomment }</textarea></div>
		</div>
		<div class="shop_posting_item">
			<div>대표이미지</div>
			<div><input name="img_1" type="file"></div>
		
			<c:if test="${bean.img_1 != ''}">
				<div>기존 대표이미지</div>
				<div><img src="/shopUpload/${bean.img_1 }"></div>
			</c:if>
		</div>
		<div class="shop_posting_item">
			<div>추가이미지1</div>
			<div><input name="img_2" type="file"></div>
			<c:if test="${bean.img_2 != ''}">
				<div>기존 추가이미지</div>
				<div><img src="/shopUpload/${bean.img_2 }"></div>
			</c:if>			
		</div>
		<div class="shop_posting_item">
			<div>추가이미지2</div>
			<div><input name="img_3" type="file"></div>
			<c:if test="${bean.img_3 != ''}">
				<div>기존 추가이미지</div>
				<div><img src="/shopUpload/${bean.img_3 }"></div>
			</c:if>			
		</div>
		</form>
		<div class="shop_posting_item"><input type="button" value="수정하기" onclick="shop_list_Update()"></div>
	</div>
<%@ include file="/include/footer.jsp" %>