<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%
	String itemtitle = request.getParameter("itemtitle");
	if(itemtitle==null || itemtitle==""){
		itemtitle="other";
	}
%>

<script type="text/javascript" language="javascript">
	function shop_list_posting(){
		shop_posting.submit();
	}
	function select_change(){
		var itemtitle = document.getElementById('itemtitle').value;
		location.href="shop_item_posting.jsp?itemtitle="+itemtitle+"";
	}
	function select_title(title){
	    $(document).ready(function(){
	        $.ajax({             
	            type : "GET",
	            url : "shop_select.jsp?title="+title,
	            dataType : "text",
	            error : function(){
	                alert('연결실패!! error구문 실행');
	            },
	            success : function(data){
	                $("#category").html(data) ;
	            }             
	        }); 
	    }); 
	}
</script>
	<div class="shop_posting">
		<form name="shop_posting" action="/shop_list_posting.do" method="post" enctype="multipart/form-data">
		<div class="shop_posting_subject">
			<div><input name="itemsubject" placeholder="게시물제목"></div>
		</div>
		<div class="shop_posting_title">
			<div>상품분류</div>
			<div>
				<select id="itemtitle" name="itemtitle" onchange="select_title(this.value)">
					<option id="1" value="other">기타</option>
					<option id="2" value="accessories">악세서리</option>
					<option id="3" value="clothes">의류</option>
					<option id="4" value="household">생활용품</option>	
				</select>
			</div>
		</div>
		<div class="shop_posting_category">
			<div>카테고리</div>
			<div id="category">
				<select>
					<option>대분류를 선택하여주세요</option>
				</select>
			</div>
		</div>
		<div class="shop_posting_itemName">
			<div><input name="itemname" placeholder="상품이름"></div>
		</div>
		<div class="shop_posting_itemPrice">
			<div><input name="itemprice" placeholder="금액"></div>
		</div>
		<div class="shop_posting_itemcomment">
			<div><textarea name="itemcomment" placeholder="상품내용"></textarea></div>
		</div>
		<div class="shop_posting_item">
			<div>대표이미지</div>
			<div><input name="img_1" type="file"></div>			
		</div>
		<div class="shop_posting_item">
			<div>추가이미지1</div>
			<div><input name="img_2" type="file"></div>			
		</div>
		<div class="shop_posting_item">
			<div>추가이미지2</div>
			<div><input name="img_3" type="file"></div>			
		</div>
		</form>
		<div class="shop_posting_item"><input type="button" value="등록하기" onclick="shop_list_posting()"></div>
	</div>
<%@ include file="/include/footer.jsp" %>