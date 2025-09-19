<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap3.x ver -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


	<style type="text/css">
		#box{
		width: 600px;
		margin: auto;
		margin-top: 100px;
		}
	textarea {
	resize: none;
	}
	
	th {				 /*부트스트랩보다 먼저 중요*/
	  vertical-align: middle !important;
	 
	}
	
	
	</style>
<script type="text/javascript">
	function send(f) {
		let name 	= f.name.value.trim();
		let content = f.content.value.trim();
		let pwd		= f.pwd.value.trim();
		
		if(name == ""){
			alert("이름을 입력하세요!");
			f.name.value="";
			f.name.focus();
			return;
		}
		
		if(content == ""){
			alert("내용을 입력하세요!");
			f.content.value="";
			f.content.focus();
			return;
		}
		
		if(pwd == ""){
			alert("비밀번호를 입력하세요!");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
		
		//수정확인
		if(confirm("정말 수정 하시겠습니까?")==false){
					
			//자신의 페이지를 다시 호출
			location.href= ""; //왼쪽처럼 이동시킬 페이지를 생략하면 자기자신 페이지를 호출해서 새로고침 효과를낸다
			
			return;
		}
		
		
		//전송대상
		f.action = "modify.do"; //VisitModifyAction
		
		//전송
		f.submit();
	}//end send


</script>
</head>
<body>

<form>

  <input type="hidden" name="idx" value="${vo.idx }">

	<div id="box">
	
		<div class="panel panel-primary">
		      <div class="panel-heading"><h4>::::수정폼::::</h4></div>
		      <div class="panel-body"></div>
		      
		      <table class="table">
				<tr>
				<th>작성자</th>
				<td><input class="form-control" name="name" value="${requestScope.vo.name }"></td>      
		      </tr>
		      
		      <tr>
		      <th>내용</th>
		      	<td>
			      	<textarea rows="5" cols="" class="form-control" name="content">${vo.content }</textarea>
		      	</td>
		      </tr>
		      
		      <tr>
		      	<th>비밀번호</th>
		     	<td><input class="form-control" type="password" name="pwd" value="${vo.pwd }"></td>
		      </tr>
		     
		      <tr>
		      	<td colspan="2" align="center">
		      		<input class="btn btn-primary "type="button" value="목록보기"
		      			   onclick="location.href='list.do'">
		      			   					
		      				
		      		<input class="btn btn-info"    type="button" value="수정하기"
		      				onclick="send(this.form);">
		      </td>
		      </tr>
		      </table>
	      
	    </div>
    
	</div>
</form>
	
</body>
</html>