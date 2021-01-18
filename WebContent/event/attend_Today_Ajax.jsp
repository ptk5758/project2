<%@page import="DAO.EventDAO"%>
<%@page import="model.AttendBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String signdate = request.getParameter("signdate");
	ArrayList<AttendBean> today_v = new ArrayList<AttendBean>();
	EventDAO dao = new EventDAO();
	today_v = dao.todayAttend(signdate);
	request.setAttribute("signdate", signdate);
	request.setAttribute("today_v", today_v);
%>
	<div id="attend_Today" class="attend_Today">
		<!-- today_v -->
		<h1>${newYear}${nowMonth }-${signdate }-</h1>
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
						<div id="today_List_Delete">삭제</div>
					</div>
				</c:if>
				</div>
				
			</div>
		</c:forEach>
	</div>