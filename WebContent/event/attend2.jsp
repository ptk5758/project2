<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/include/header.jsp" %>
<div class="attend_container">
	<div id="attendTable">
		<table class="attend">
			<tr class="tr1">
				<fmt:formatNumber var="nowMonth" value="${newMonth }" pattern="00"/>
				<td style="background-color: #fff;" onclick="backMonth()" id="monthBack">이전</td>
				<td id="now_Month" colspan="5">${newYear}-${nowMonth }</td>
				<td onclick="nextMonth()" id="monthNext">다음</td>
			</tr>
			<tr class="tr2">
				<td style="background-color: #fff;">일</td>
				<td>월</td>
				<td>화</td>
				<td>수</td>
				<td>목</td>
				<td>금</td>
				<td style="background-color: #fff;">토</td>
			</tr>
			<c:set var="i" value="0" />
			<c:forEach var="attend" items="${v }">
				<c:if test="${i == 0}">
					<tr>
				</c:if>
					<c:if test="${attend.signdate_option == 'backMonth'}">
						<td style="background-color: #d8d8d8; color: #fff">
							<div>${attendDay[i] }</div>
							<div>${attend.signdate_option }</div>
							<div>${attendCount[i] }</div>
						</td>
					</c:if>
					<c:if test="${attend.signdate_option == 'normal'}">
						<td class="normal" onclick="thisToday(this)" style="color: #fff; font-family: Impact; font-size: 22px;">
							<div class="calDay">${attendDay[i] }</div>
							<div>${attend.signdate_option }</div>
							<div align="right">
								<c:if test="${attendCount[i] != 0}"><b style="color: #000000">${attendCount[i] }</b></c:if>
								<c:if test="${attendCount[i] == 0}">-</c:if>
							</div>
						</td>
					</c:if>
					<c:if test="${attend.signdate_option == 'nextMonth'}">
						<td style="background-color: #d8d8d8; color: #fff">
							<div>${attendDay[i] }</div>
							<div>${attend.signdate_option }</div>
							<div>${attendCount[i] }</div>
						</td>
					</c:if>
				<c:set var="i" value="${i+1 }"/>
				<c:if test="${(i%7)== 0 }">
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
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
	<div id="attend_Today" class="attend_Today">
		<!-- today_v -->
		<h1>${newYear}-${nowMonth }</h1>
		<c:forEach var="todayAttend" items="${today_v }">
			<div class="attend_Today_List">
				<div class="Today_List_user">
					<div class="Today_List_username">
						<div>닉네임: ${todayAttend.user_nickname }</div>
					</div>
					<div class="Today_List_signdate">
						<div>${todayAttend.attend_signdate }</div>
					</div>
				</div>
				<div class="Today_List_comment">
					<form action="/attend_Comment_Update.do" name="todayListForm" id="comment" style="align-self: center;" method="post">
						<div>내용 :${todayAttend.attend_comment }</div>
					</form>
					<c:if test="${todayAttend.attend_user_id==session_id }">
					<div class="Today_List_option">
						<div id="today_List_Update">
							<div>수정</div>
							<input type="hidden" id="attenduid" value="${todayAttend.attend_uid }">
						</div>
					</div>
				</c:if>
				</div>
				
			</div>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	function nextMonth(){
		$(document).ready(function(){
			var nowMonth = ($('#now_Month').html());
				$.ajax({
		            type : "GET",
		            url : "/event/attend_Ajax.jsp?monthOption=next&nowMonth="+nowMonth,
		            dataType : "text",
		            error : function(){
		                alert('연결실패!!');
		            },
		            success : function(data){
		            	$('#attendTable').html(data);
		            }
		        });
		});
	}
	function backMonth(){
		$(document).ready(function(){
			var nowMonth = ($('#now_Month').html());
				$.ajax({
		            type : "GET",
		            url : "/event/attend_Ajax.jsp?monthOption=back&nowMonth="+nowMonth,
		            dataType : "text",
		            error : function(){
		                alert('연결실패!!');
		            },
		            success : function(data){
		            	$('#attendTable').html(data);
		            }             
		        });
			});
	}
	function thisToday(item){
		$(document).ready(function(){
			var selectday = $(item).find('div.calDay').html();
			var yearMonth = $('#now_Month').html();
			var newsigndate = yearMonth+"-"+selectday;
			$.ajax({
	            type : "GET",
	            url : "/event/attend_Today_Ajax.jsp?signdate="+newsigndate,
	            dataType : "text",
	            error : function(){
	                alert('연결실패!!');
	            },
	            success : function(data){
	            	$('#attend_Today').html(data);
	            }             
	        });
		});
	}
	$('#today_List_Update').click(function(){
		var attenduid = $(this).children('#attenduid').val();
		console.log(attenduid);
		$(this).parent().parent().find('#comment').html("<input name='newComment'><input type='submit' value='수정하기'><input type='hidden' name='attenduid' value='"+attenduid+"'>");
	});
</script>
<%@ include file = "/include/footer.jsp" %>
<!-- <form action="/attend.do" method="post">
					<td><input name="attend_comment" value="시간은 금이다."></td>
					<td><input type="submit" value="출석체크"></td>
				</form> -->
				
				