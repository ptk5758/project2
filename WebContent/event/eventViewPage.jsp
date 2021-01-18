<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
	<div class="eventViewPage">
		<div class="eventView_Border">
			<div class="eventView_subject">
				<span>${bean.subject }</span>				
			</div>
			<div class="eventView_signdate">
				<span>기한</span>
				<span>${bean.period1 }</span>
				<span>~</span>
				<span>${bean.period2 }</span>				
			</div>
			<div class="eventView_img">
				<c:if test="${bean.file_1 != ''}"><div align="center"><img class="eventImg" src="/admin/event/img/${bean.file_1 }"></div></c:if>
				<c:if test="${bean.file_2 != ''}"><div align="center"><img class="eventImg" src="/admin/event/img/${bean.file_2 }"></div></c:if>
				<c:if test="${bean.file_3 != ''}"><div align="center"><img class="eventImg" src="/admin/event/img/${bean.file_3 }"></div></c:if>				
			</div>
			<div class="eventView_contents">
				<span style="color:${bean.font_Color};font-family:${bean.font_Family } ;font-size:${bean.font_size}px">${bean.contents }</span>				
			</div>
		</div>
	</div>
<%@ include file="/include/footer.jsp" %>