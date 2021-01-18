<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="height:500px;"></div>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.4.1.min.js"></script>

<script type="text/javascript" language="javascript"> 
function a(aaa){
    $(document).ready(function(){
        $.ajax({             
            type : "GET",
            url : "test4.jsp?type="+aaa,
            dataType : "text",
            error : function(){
                alert('연결실패!!');
            },
            success : function(data){
                $("#dataArea").html(data) ;
            }             
        }); 
    }); 
}
</script>
<form>
<select name="category" onchange="a(this.value)">
	<option value="">=대분류=</option>
	<option value="화장품">화장품</option>
	<option value="컴퓨터">컴퓨터</option>
</select>

&nbsp;

<span id="dataArea">
	<select name="category2" onchange="b(this.value)">
		<option value="">=대분류 선택하세요=</option>
	</select>
</span>

</form>
</body>
</html>