<%@page import="DAO.EventDAO"%>
<%@page import="model.AttendBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");
	String monthOption = request.getParameter("monthOption");
	String nowMonth = request.getParameter("nowMonth");
	int newYear = 2020;
	int newMonth = 1;
	int newFirst = 1;
	int newLast = 1;
	int newFirstDay = 0;
	int week = 5;
	int j = 0;
	String attendDay[] = new String[42];
	int attendCount[] = new int[42];
	//이전달 처리
	if(monthOption.equals("back")){
		newYear = Integer.parseInt(nowMonth.substring(0, 4));//2020-12
		newMonth = Integer.parseInt(nowMonth.substring(5, 7))-1;//2020-12
		if(newMonth == 0){
			newMonth = 12;
			newYear--;
		}
		Calendar cal = Calendar.getInstance();
		//
		cal.set(Calendar.YEAR , newYear);
		cal.set(Calendar.MONTH, newMonth-1);
		newFirst = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		newLast = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, newFirst);
		newFirstDay = cal.get(Calendar.DAY_OF_WEEK);
	}
	//다음달 처리
	if(monthOption.equals("next")){
		newYear = Integer.parseInt(nowMonth.substring(0, 4));//2020-12
		newMonth = Integer.parseInt(nowMonth.substring(5, 7))+1;//2020-12
		if(newMonth == 13){
			newMonth = 1;
			newYear++;
		}
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR , newYear);
		cal.set(Calendar.MONTH, newMonth-1);
		newFirst = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		newLast = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, newFirst);
		newFirstDay = cal.get(Calendar.DAY_OF_WEEK);
	}
	int setday = 01;
	int lastday = 7;
	ArrayList<AttendBean> v = new ArrayList<AttendBean>();///////////////////////////
	EventDAO dao = new EventDAO();
	for(int i=0; i<=week; i++){
		while(newFirstDay != 1){
			attendDay[j] = newMonth-1+"월";
			attendCount[j] = 0;
			AttendBean bean = new AttendBean();
			bean.setSigndate_option("backMonth");
			v.add(bean);
			newFirstDay--;
			lastday--;
			j++;
		}
		while(lastday != 0){
			if(setday > newLast){
				attendDay[j] =  newMonth+1+"월";
				attendCount[j] = 0;
				AttendBean bean = new AttendBean();
				bean.setSigndate_option("nextMonth");
				v.add(bean);
				setday++;
				lastday--;
				j++;
			}else{
				attendDay[j] = String.format("%02d", setday);
				String calendarDay = newYear+"-"+String.format("%02d", newMonth)+"-"+String.format("%02d", setday);
				attendCount[j] = dao.showAttend(calendarDay);
				AttendBean bean = new AttendBean();
				bean.setSigndate_option("normal");
				v.add(bean);
				setday++;
				lastday--;
				j++;
			}
		}
		lastday = 7;
	}
	request.setAttribute("attendCount", attendCount);
	request.setAttribute("v", v);
	request.setAttribute("attendDay", attendDay);
	request.setAttribute("newMonth", newMonth);
	request.setAttribute("newYear", newYear);
	
%>
	<table class="attend">
		<tr class="tr1">
			<fmt:formatNumber var="nowMonth" value="${newMonth }" pattern="00"/>
			<td style="background-color: #fff;" onclick="backMonth()" id="monthBack">이전</td>
			<td id="now_Month" colspan="5">${newYear}-${nowMonth }</td>
			<td style="background-color: #fff;" onclick="nextMonth()" id="monthNext">다음</td>
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
