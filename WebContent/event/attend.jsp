<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<div class="attend_container">
	<table class="attend">
		<tr class="tr1">
			<td id="monthBack">이전</td>
			<td id="now_Month" colspan="5">2020-12</td>
			<td id="monthNext">다음</td>
		</tr>
		<tr class="tr2">
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
			<c:set var="i" value="0"/>
			<c:forEach var="attend" items="${v }">
				<c:if test="${i == 0 }">
					<tr>
				</c:if>
				<td>
					<div>${attend.attend_comment }</div>
					<div>
						<c:if test="${attend_Count[i] != 0}"><b>${attend_Count[i]}</b>명</c:if>
						<c:if test="${attend_Count[i] == 0}">-</c:if>
					</div>
				</td>
				<c:set var="i" value="${i+1 }"/>
				<c:if test="${(i%7) == 0 }">
					</tr>
				</c:if>
			</c:forEach>
	</table>
	<div class="attend_Input_Main">
		<form class="attend_Input_Form" action="/attend.do" method="post">
			<div class="attend_Input_User">
				<c:choose>
					<c:when test="${session_id != ''}">${session_id }</c:when>
					<c:otherwise>로그인을 해주세요</c:otherwise>
				</c:choose>
			</div>
			<div class="attend_Input_Input"><input name="attend_comment" placeholder="놀땐놀고 할땐하자"></div>
			<div class="attend_Input_Button"><input type="submit" value="출석하기"></div>
		</form>
	</div>
	<div id="ajax_Calendar"></div><!----------------------------------------------------  -->
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var nowMonth = ($('#now_Month').html());
		$('#monthBack').click(function(){
			$.ajax({
	            type : "GET",
	            url : "/event/attend_Ajax.jsp?monthOption=back&nowMonth="+nowMonth,
	            dataType : "text",
	            error : function(){
	                alert('연결실패!!');
	            },
	            success : function(data){
	            	$('#ajax_Calendar').html(data) ;
	            }             
	        }); 
		});
		$('#monthNext').click(function(){
			$.ajax({
	            type : "GET",
	            url : "/event/attend_Ajax.jsp?monthOption=next&nowMonth="+nowMonth,
	            dataType : "text",
	            error : function(){
	                alert('연결실패!!');
	            },
	            success : function(data){
	            	$('#ajax_Calendar').html(data) ;
	            }             
	        }); 
		});
	});
</script>
<%@ include file = "/include/footer.jsp" %>
<!-- <form action="/attend.do" method="post">
					<td><input name="attend_comment" value="시간은 금이다."></td>
					<td><input type="submit" value="출석체크"></td>
				</form> -->
				
				