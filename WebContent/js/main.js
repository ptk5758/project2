/**
 * 
 */

	function test(){
		console.log("gdgd");
	}
	
	$(document).ready(function(){
		
		$('#fontFace').change(function(){
			var fontFace = $('#fontFace option:selected').html();
			$('#eventContents').css("font-family",fontFace);
		});
		
		$('#font_Option_FontSize').change(function(){
			var fontSize = $('#fontSize option:selected').html();
			$('#eventContents').css("font-size",fontSize+"px");
		});
		
		$('#fontColor').change(function(){
			var fontColor = $('#fontColor').val();
			$('#eventContents').css("color",fontColor);
		});
	});
	
	function threadUP(){
		var eventForm = document.getElementById('eventForm');
		eventForm.method = "post";
		eventForm.action = "/event_threadUP.do";
		eventForm.submit();
	}
	
	function threadDown(){
		var eventForm = document.getElementById('eventForm');
		eventForm.method = "post";
		eventForm.action = "/event_threadDown.do";
		eventForm.submit();
	}
	
	function eventEnd(){
		var eventForm = document.getElementById('eventForm');
		eventForm.method = "post";
		eventForm.action = "/event_end.do";
		eventForm.submit();
	}
	
	function eventDelete(){
		var eventForm = document.getElementById('eventForm');
		eventForm.method = "post";
		eventForm.action = "/event_Delete.do";
		eventForm.submit();
	}
	
	