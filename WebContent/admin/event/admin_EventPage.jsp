	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
<script>
	function eventPosting(){
		location.href="/eventPosting.go";
	}
	function viewEvent(eventuid){
		location.href="/eventViewPage.go?eventuid="+eventuid;
	}
	function updateEvent(eventuid){
		location.href="/eventUpdatePage.go?eventuid="+eventuid;
	}
</script>
	<h1 align="center">이벤트관리</h1>
	<div class="admin_border">
		<div class="admin_border_left">
			<div class="admin_left_menu">이벤트목록</div>
			<div class="admin_left_menu">이벤트목록</div>
			<div class="admin_left_menu">이벤트목록</div>
			<div class="admin_left_menu">이벤트목록</div>
			<div class="admin_left_menu">이벤트목록</div>
			<div class="admin_left_menu">이벤트목록</div>
		</div>
		<div class="admin_border_right">
			<h2 align="center">진행중인 이벤트 목록</h2>
			<div class="admin_Event">
				<div class="admin_Event_Button">
					<div class="admin_Event_Button_Item" onclick="eventPosting()">이벤트 등록하기</div>
					<div class="admin_Event_Button_Item" onclick="threadUP()">노출순서 올리기</div>
					<div class="admin_Event_Button_Item" onclick="threadDown()">노출순서 내리기</div>
					<div class="admin_Event_Button_Item" onclick="eventEnd()">이벤트 종료하기</div>
					<div class="admin_Event_Button_Item" onclick="eventDelete()">이벤트 삭제</div>
				</div>
				<div class="admin_Event_List">
					<div class="admin_Event_Num top">번호</div>
					<div class="admin_Event_Title top">제목</div>
					<div class="admin_Event_Signdate top">이벤트 기간</div>
					<div class="admin_Event_Thread top">노출순서</div>
					<div class="admin_Event_Option top">옵션</div>
					<div class="admin_Event_Ref top">조회수</div>
					<div class="admin_Event_Check top">체크</div>
				</div>
				<form id="eventForm">
				<c:set var="eventNum" value="${eventCount }"/>
				<c:forEach var="eventList" items="${v }">
				<div class="admin_Event_List">
					<div class="admin_Event_Num">No.<b>${eventNum }</b></div>
					<div class="admin_Event_Title" onclick="viewEvent('${eventList.eventuid}')">${eventList.subject }</div>
					<div class="admin_Event_Signdate">
						<c:if test="${eventList.active == 1 }">
							<span>${fn:substring(eventList.period1,0,10) }</span>
							<span>~</span>
							<span>${fn:substring(eventList.period2,0,10) }</span>
						</c:if>
						<c:if test="${eventList.active == 0 }">
							<span>종료된 이벤트</span>
						</c:if>
					</div>
					<div class="admin_Event_Thread">${eventList.thread }</div>
					<div class="admin_Event_Option">
						<sapn style="cursor:pointer" onclick="viewEvent('${eventList.eventuid}')">보기</sapn>
						<sapn>|</sapn>
						<sapn style="cursor:pointer" onclick="updateEvent('${eventList.eventuid}')">수정</sapn>
					</div>
					<div class="admin_Event_Ref">${eventList.ref }</div>
					<div class="admin_Event_Check"><input name="eventuid" type="checkbox" value="${eventList.eventuid }"></div>
				</div>
				<c:set var="eventNum" value="${eventNum-1 }"/>
				</c:forEach>
				</form>
			</div>
		</div>
	</div>
	<h1>${eventuid[0] }</h1>
	<script src="/js/main.js"></script>
<%@ include file="/admin/include/footer.jsp" %>