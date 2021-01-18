	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
<script type="text/javascript" language="javascript">
	function userInfoGo(useruid){
		location.href="/admin_UserInfo.go?useruid="+useruid+"";
	}
	function msg_warning(){
		console.log("gdgd");
		userInfo.action="/user_Warning_Msg.do";
		userInfo.method="post";
		userInfo.submit();
	}
	function shopList_Delete(){
		console.log("리스트 삭제");
		shopList.action="/admin_ShopList_Delete.do";
		shopList.method="post";
		shopList.submit();
	}
	function shopSearch(searchInput){
		var searchSelect = document.searchform.searchSelect.value;
	    $(document).ready(function(){
	        $.ajax({             
	            type : "GET",
	            url : "/admin/shop/ShopList_Search_controller.jsp?searchInput="+searchInput+"&searchSelect="+searchSelect,
	            dataType : "text",
	            error : function(){
	                alert('연결실패!!');
	            },
	            success : function(data){
	                $("#searchList").html(data) ;
	                $("#searchText").html(searchInput+'에 대한 검색결과');
	                $("#searchText").css("font-weight", "bold");
	            }             
	        }); 
	    }); 
	}
	function selectDate(){
		$(document).ready(function(){
			var signdate1 = $('#signdate1').val();
			var signdate2 = $('#signdate2').val();
			 $.ajax({             
		            type : "GET",
		            url : "/admin/shop/ShopList_DateSelect_controller.jsp?signdate1="+signdate1+"&signdate2="+signdate2,
		            dataType : "text",
		            error : function(){
		                alert('연결실패!!');
		            },
		            success : function(data){
		            	$("#searchList").html(data);
		            }
			});
		})
	}
	$(document).ready(function(){
		$('#adminBlind').click(function(){
			document.getElementById('shopList').action="/admin_Shop_Blind.do";
			document.getElementById('shopList').method="post";
			document.getElementById('shopList').submit();
		})
		$('#blindCancel').click(function(){
			document.getElementById('shopList').action="/admin_Cancel_Blind.do";
			document.getElementById('shopList').method="post";
			document.getElementById('shopList').submit();
		})
		
	})
	
</script>
	<h1 align="center">굿즈샵 관리</h1>
	<div class="admin_border">
		<div class="admin_border_left">
			<div class="admin_left_menu">게시판목록</div>
			<div class="admin_left_menu">게시판목록</div>
			<div class="admin_left_menu">게시판목록</div>
			<div class="admin_left_menu">게시판목록</div>
			<div class="admin_left_menu">게시판목록</div>
			<div class="admin_left_menu">게시판목록</div>
		</div>
		<div class="admin_border_right">
			<div class="admin_UserOption_Button">
				<div class="UserOption_Button_Item" id="adminBlind">블라인드</div>
				<div class="UserOption_Button_Item" id="blindCancel">블라인드 헤제</div>
				<div class="UserOption_Button_Item" onclick="shopList_Delete()">삭제</div>				
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
				<div class="UserOption_Button_Item">경고주기</div>
			</div>
			
			<form class="admin_Shop_Search" name="searchform">
			<div class="admin_Shop_Search_select">
				<select name="searchSelect">
					<option value="itemname">상품이름</option>
					<option value="user">작성자</option>
				</select>
			</div>
			<div class="admin_Shop_Search_input"><input name="searchInput" onkeyup="shopSearch(this.value)"></div>
			<div class="admin_Shop_Search_button"><input type="submit" value="검색하기"></div>
			<div id="searchText" class="admin_Shop_Search_option">에 대한 검색결과</div>
			<div class="admin_Shop_Search_option">기간으로 검색</div>
			<div class="admin_Shop_Search_Date"><input id="signdate1" type="date"></div>
			<div class="admin_Shop_Search_option">~~</div>
			<div class="admin_Shop_Search_Date"><input id="signdate2" type="date" onchange="selectDate()"></div>
			</form>
			<div class="admin_ShopBorder">
				<div class="admin_ShopList admin_user_list_top">
					<div class="admin_Shop_Num">번호</div>
					<div class="admin_Shop_Img">상품이미지</div>
					<div class="admin_Shop_Name">상품명</div>
					<div class="admin_Shop_User">작성자</div>
					<div class="admin_Shop_Option">옵션</div>
					<div class="admin_Shop_Price">상품가격</div>
					<div class="admin_Shop_Signdate">등록날짜</div>
					<div class="admin_Shop_Ref">조회수</div>	
					<div class="admin_Shop_Like">좋아요</div>	
					<div class="admin_Shop_Check">체크</div>
				</div>
				<form id="shopList">
					<div id="searchList">
					<c:set var="i" value="0"/>
					<c:forEach var="shopList" items="${v }">
						<div class="admin_ShopList">
							<div class="admin_Shop_Num">No.1</div>
							<div class="admin_Shop_Img">
								<c:if test="${shopList.active == '1'}"><img src="/shopUpload/${shopList.img_1 }"></c:if>
								<c:if test="${shopList.active == '2'}">
									<img style="filter: blur(3px)" src="/shopUpload/${shopList.img_1 }">
									<div class="blind">블라인드 처리됀 글입니다.</div>
								</c:if>
							</div>
							<div class="admin_Shop_Name">${shopList.itemname }</div>
							<div class="admin_Shop_User">${shopList.user }</div>
							<div class="admin_Shop_Option">-</div>
							<div class="admin_Shop_Price">${shopList.itemprice }</div>
							<div class="admin_Shop_Signdate">${fn:substring(shopList.signdate,0,10) }</div>
							<div class="admin_Shop_Ref"><b>${shopList.ref }</b></div>	
							<div class="admin_Shop_Like"><b>${likeCount[i] }</b></div>
							<div class="admin_Shop_Check"><input type="checkbox" id="shopuid" name="shopuid" value="${shopList.shopuid }"></div>	
						</div>
					<c:set var="i" value="${i+1 }" />
					</c:forEach>
					</div>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/admin/include/footer.jsp" %>
