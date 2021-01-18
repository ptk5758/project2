<%@page import="model.EventBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
<%
	EventBean beanJSP = (EventBean)request.getAttribute("bean");
	int font_Size = beanJSP.getFont_size();
	String font_Family = beanJSP.getFont_Family();
%>
	<div class="eventPosting">
		<div><h1>이벤트 수정하기</h1></div>
		<form class="eventPosting_Border" action="/eventUpdate.do" method="post">
		<input type="hidden" name="eventuid" value="${eventuid }">
			<div class="eventPosting_title">
				<div>제목</div>
				<div><input name="subject" value="${bean.subject }"></div>
			</div>
			<div class="eventPosting_contents">
				<div>내용 : </div>
				<div id="font_Option">
					<div id="font_Option_Text">서식</div>
					<div id="font_Option_FontFamily">
						<span>글꼴</span>
						<select name="font_Family" id="fontFace">
							<option>기본</option>
							<option <%if(font_Family.equals("궁서")){%>selected="selected"<%}%> style="font-family: '궁서'">궁서</option>
							<option <%if(font_Family.equals("굴림")){%>selected="selected"<%}%> style="font-family: '굴림'">굴림</option>
							<option <%if(font_Family.equals("돋움")){%>selected="selected"<%}%> style="font-family: '돋움'">돋움</option>
							<option <%if(font_Family.equals("바탕")){%>selected="selected"<%}%> style="font-family: '바탕'">바탕</option>
							<option <%if(font_Family.equals("메이플")){%>selected="selected"<%}%> style="font-family: '메이플'">메이플</option>
							<option <%if(font_Family.equals("배찌")){%>selected="selected"<%}%> style="font-family: '배찌'">배찌</option>
							<option <%if(font_Family.equals("넥슨고딕")){%>selected="selected"<%}%> style="font-family: '넥슨고딕'">넥슨고딕</option>
							<option <%if(font_Family.equals("휴먼엽서체")){%>selected="selected"<%}%> style="font-family: '휴먼엽서체'">휴먼엽서체</option>
							<option <%if(font_Family.equals("Impact")){%>selected="selected"<%}%> style="font-family: 'Impact'">Impact</option>
						</select>
					</div>
					<div id="font_Option_FontSize">
						<span>사이즈</span>
						<select name="font_Size" id="fontSize">
							<option <%if(font_Size == 12){%>selected="selected"<%}%>>12</option>
							<option <%if(font_Size == 14){%>selected="selected"<%}%>>14</option>
							<option <%if(font_Size == 16){%>selected="selected"<%}%>>16</option>
							<option <%if(font_Size == 18){%>selected="selected"<%}%>>18</option>
							<option <%if(font_Size == 22){%>selected="selected"<%}%>>22</option>
							<option <%if(font_Size == 24){%>selected="selected"<%}%>>24</option>
							<option <%if(font_Size == 26){%>selected="selected"<%}%>>26</option>
							<option <%if(font_Size == 28){%>selected="selected"<%}%>>28</option>
							<option <%if(font_Size == 32){%>selected="selected"<%}%>>32</option>
							<option <%if(font_Size == 36){%>selected="selected"<%}%>>36</option>
							<option <%if(font_Size == 44){%>selected="selected"<%}%>>44</option>
						</select>
					</div>
					<div id="font_Option_FontColor">
						<span>컬러</span>
						<input name="font_Color" id="fontColor" type="color" value="${bean.font_Color }">						
					</div>
				</div>
				<div class="eventPeriod">
					<div>기한</div>
					<div class="eventDate">
						<div><input name="period1" type="date" value="${bean.period1 }"></div>
						<div>~</div>
						<div><input name="period2" type="date" value="${bean.period2 }"></div>
					</div>
				</div>
				<div><textarea name="contents" id="eventContents" placeholder="내용">${bean.contents }</textarea> </div>
			</div>
			<!-- <div class="eventPosting_img">
				<div>*배너:</div>
				<div><input name="file_B" type="file"></div>
				<div>권장사이즈(1000*220)</div>
			</div>
			<div class="eventPosting_img">
				<div>첨부파일_1:</div>
				<div><input name="file_1" type="file"></div>
			</div>
			<div class="eventPosting_img">
				<div>첨부파일_2:</div>
				<div><input name="file_2" type="file"></div>
			</div>
			<div class="eventPosting_img">
				<div>첨부파일_3:</div>
				<div><input name="file_3" type="file"></div>
			</div> -->
			<div class="eventPosting_button">
				<div><input type="submit" value="수정하기"></div>	
			</div>
		</form>
	</div>
<script src="js/main.js" type="text/javascript"></script>
<%@ include file="/admin/include/footer.jsp" %>

