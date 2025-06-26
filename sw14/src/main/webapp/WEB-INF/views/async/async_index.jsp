<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
    //DOM문서(HTML)문서가 로드가 완료되면 수행!
	document.addEventListener("DOMContentLoaded",function(){
	    console.log("DOMContentLoaded");
	
	    //전송 버튼 이벤트 처리
	    $("#sendBtn").on("click",function(){
	    	console.log("sendBtn click");
	    	asyncSend();
	    });
	    
	    //함수
	    function asyncSend(){
	    	
	    	$.ajax({
	    		type:"POST",    //GET/POST
	    		url:"/ehr/async/async_result.do", //서버측 URL
	    		asyn:"true",    //비동기
	    		dataType:"html",//서버에서 받을 데이터 타입
	    		data:{          //파라메터
	    			"userId": $("#userId").val(),
	    			"password": $("#password").val()
	    		},
	    		success:function(response){//요청 성공
	    			console.log("success:"+response)
	    			var result_str = response;
	    			$("#result").html(result_str);
	    		},
	    		error:function(response){//요청 실패
	    			console.log("error:"+response)
	    		}		
	    	});
	    }
	});
</script>
</head>
<body>
    <h2>비동기(Asynchronous)</h2>
    <hr/>
    <form action="asyncForm">
        <label for="userId">아이디</label>
        <input type="text" name="userId" id="userId">
        <label for="password">비밀번호</label>
        <input type="password" name="password" id="password">
        <input type="button" id="sendBtn" value="전송">    
    </form>
    <div id="result"></div>   
    
    
</body>
</html>