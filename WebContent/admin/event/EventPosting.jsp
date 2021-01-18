<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header.jsp" %>
	<div class="eventPosting">
		<div><h1>이벤트 등록하기</h1></div>
		<form class="eventPosting_Border" action="/eventPostingInsert.do" method="post" enctype="multipart/form-data">
			<div class="eventPosting_title">
				<div>제목</div>
				<div><input name="subject" placeholder="제목"></div>
			</div>
			<div class="eventPosting_contents">
				<div>내용 : </div>
				<div id="font_Option">
					<div id="font_Option_Text">서식</div>
					<div id="font_Option_FontFamily">
						<span>글꼴</span>
						<select name="font_Family" id="fontFace">
							<option selected="selected">기본</option>
							<option style="font-family: '궁서'">궁서</option>
							<option style="font-family: '굴림'">굴림</option>
							<option style="font-family: '돋움'">돋움</option>
							<option style="font-family: '바탕'">바탕</option>
							<option style="font-family: '메이플'">메이플</option>
							<option style="font-family: '배찌'">배찌</option>
							<option style="font-family: '넥슨고딕'">넥슨고딕</option>
							<option style="font-family: '휴먼엽서체'">휴먼엽서체</option>
							<option style="font-family: 'Impact'">Impact</option>
						</select>
					</div>
					<div id="font_Option_FontSize">
						<span>사이즈</span>
						<select name="font_Size" id="fontSize">
							<option>12</option>
							<option>14</option>
							<option>16</option>
							<option>18</option>
							<option selected="selected">22</option>
							<option>24</option>
							<option>26</option>
							<option>28</option>
							<option>32</option>
							<option>36</option>
							<option>44</option>
						</select>
					</div>
					<div id="font_Option_FontColor">
						<span>컬러</span>
						<input name="font_Color" id="fontColor" type="color">						
					</div>
				</div>
				<div class="eventPeriod">
					<div>기한</div>
					<div class="eventDate">
						<div><input name="period1" type="date"></div>
						<div>~</div>
						<div><input name="period2" type="date"></div>
					</div>
				</div>
				<div><textarea name="contents" id="eventContents" placeholder="내용"></textarea> </div>
			</div>
			<div class="eventPosting_img">
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
			</div>
			<div class="eventPosting_button">
				<div><input type="submit" value="등록하기"></div>	
			</div>
		</form>
	</div>
<script src="js/main.js" type="text/javascript"></script>
<%@ include file="/admin/include/footer.jsp" %>

