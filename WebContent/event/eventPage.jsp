<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
	<div class="eventPage">
		<h1 align="center">진행중인 이벤트</h1>
		<c:forEach var="eventList" items="${v }">
		<div class="eventPage_Item">
			<div class="eventBanner">
				<c:if test="${eventList.active == 1 }">
					<img class="test" onclick="viewPageGo('${eventList.eventuid}')" src="/admin/event/img/${eventList.file_B }">
					<div class="event_Text">보러가기</div>
				</c:if>
				<c:if test="${eventList.active == 0 }">
					<img class="endEvent" src="/admin/event/img/${eventList.file_B }">
					<div class="endEvent_Text">종료된 이벤트</div>
				</c:if>
			</div>
			<div class="eventSubject">
				<span>이벤트 : ${eventList.subject }</span>
				<span style="float: right">
					<span>기한</span>
					<span>${fn:substring(eventList.period1,0,4) }년 ${fn:substring(eventList.period1,5,7) }월 ${fn:substring(eventList.period1,8,10) }일</span>
					<span>~</span>
					<span>${fn:substring(eventList.period2,0,4) }년 ${fn:substring(eventList.period2,5,7) }월 ${fn:substring(eventList.period2,8,10) }일</span>
				</span>
			</div>
		</div>
		</c:forEach>
	</div>
	<script>
		function viewPageGo(eventuid){
			location.href = "/eventViewPage.go?eventuid="+eventuid;
		}
		$(document).ready(function(){
			$('.test').mouseover(function(){
				$(this).stop(true).animate({opacity:0.5},300,'swing',
				function(){
					$(this).next().animate({opacity:1,'font-size':'52px'},200);
				});
			});
			$('.test').mouseout(function(){
				$(this).stop(true).animate({opacity:1},100,'swing',
				function(){
					$(this).next().animate({opacity:0,'font-size':'0px'},100);
				});
			})
		});
	</script>
<%@ include file="/include/footer.jsp" %>
