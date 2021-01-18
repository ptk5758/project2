<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<script>
	function sign_up(){
		usersignup.submit();
	}
</script>
			<div class="signUp">
				<div class="signUp_container">
					<h1>회원가입</h1>
					<div class="signUp_border">
						<div class="signUp_text">
							<div class="signUp_text_item">아이디</div>
							<div class="signUp_text_item">비밀번호</div>
							<div class="signUp_text_item">비밀번호 확인</div>
							<div class="signUp_text_item">이름</div>
							<div class="signUp_text_item">전화번호</div>
							<div class="signUp_text_item">닉네임</div>
							<div class="signUp_text_item">나이</div>
							<div class="signUp_text_item">성별</div>
							<div class="signUp_text_item">우편번호</div>
							<div class="signUp_text_item">주소1</div>
							<div class="signUp_text_item">주소2</div>
						</div>
						<form id="usersignup" class="signUp_input" name="usersignup" action="SignUp.do" method="post">
							<div class="signUp_input_item"><input name="userid" placeholder="아이디"></div>
							<div class="signUp_input_item"><input id="userpassword" name="userpassword" placeholder="비밀번호"></div>
							<div class="signUp_input_item"><input id="userpassword2" name="userpassword2" placeholder="비밀번호확인"></div>
							<div class="signUp_input_item"><input name="username" placeholder="이름"></div>
							<div class="signUp_input_item"><input name="userphone" placeholder="전화번호"></div>
							<div class="signUp_input_item"><input name="usernickname" placeholder="닉네임"></div>
							<div class="signUp_input_item"><input name="userage" placeholder="나이"></div>
							<div class="signUp_input_item"><input name="usergender" placeholder="성별"></div>
							<div class="signUp_input_item"><input name="useremail" placeholder="이메일"></div>
							<div class="signUp_input_item"><input name="useraddress" type="text" id="sample6_postcode" placeholder="우편번호"></div>
							<div class="signUp_input_item"><input name="useraddress_1" type="text" id="sample6_address" placeholder="주소"></div>
							<div class="signUp_input_item"><input name="useraddress_2" type="text" id="sample6_detailAddress" placeholder="상세주소"></div>						
						</form>
						<div class="signUp_option">
							<div class="signUp_option_item">아이디는 ~</div>
							<div class="signUp_option_item">비밀번호는 ~</div>
							<div class="signUp_option_item">비멀본호 확인~</div>
							<div class="signUp_option_item">닉네임은~</div>
							<div class="signUp_option_item">아이디는 ~</div>
							<div class="signUp_option_item">비밀번호는 ~</div>
							<div class="signUp_option_item">비멀본호 확인~</div>
							<div class="signUp_option_item"><input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br></div>
							<div class="signUp_option_item">비멀본호 확인~</div>
							<div class="signUp_option_item"><input type="text" id="sample6_extraAddress" placeholder="참고항목"></div>				
						</div>
					</div>
					<div class="signUp_button"><input type="button" value="회원가입" onclick="sign_up()"></div>
				</div>
			</div>
			
<%@ include file="/include/footer.jsp" %>


<!------------------------------------맵 API------------------------------------------->

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>