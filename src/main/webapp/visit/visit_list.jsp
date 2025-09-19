<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL Core : forEach,if,Choose,... -->
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JSTL Functions : substring,indexOf,length,... -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  
  <!-- Bootstrap 3.x ver -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <style type="text/css">
     #box{
        width: 600px;
        margin: auto;
        margin-top: 50px;
     }
     
     #title{
        text-align: center;
        font-weight: bold;
        font-size: 28px;
        
        color: rgb(51,122,183);
        text-shadow: 1px 1px 1px black; 
     }
     
     #error_msg{
        text-align: center;
        color: red;
        
        font-size: 20px;
        font-weight: bold;
     }
     
     .common{
        border: 1px solid #dddddd;
        padding: 5px;
        margin-bottom: 5px;
        box-shadow: 1px 1px 1px #aaaaaa;
     }
     
     .content{
        min-height: 80px;
     }
  </style>
  
  
  <script type="text/javascript">
  
  	function del(f){
  		
  		let idx		=  f.idx.value;          //게시물번호
  		let c_pwd	=  f.c_pwd.value.trim(); //확인비번
  		
  		if(c_pwd==""){
  			alert("삭제할 게시물의 비밀번호를 입력하세요");
  			f.c_pwd.value="";
  			f.c_pwd.focus();
  			return;
  		}
  		
  		//Ajax 이용해서 비밀번호 체크
  		$.ajax({
  			
  			url		:	"check_pwd.do",               // VisitCheckPwdAction
  			data	:	{"idx":idx , "c_pwd":c_pwd},  // check_pwd.do?idx=5&c_pwd=1234
  			dataType:	"json",                       // 수신데이터 Type
  			success	:	function(res_data){
  				
  				// res_data = {"result":true} or {"result":false}
  				if(res_data.result==false){
  					alert("삭제 비밀번호가 틀립니다");
  					return;
  				}
  				
  				//삭제진행
  				//삭제확인
		  		if(confirm("정말 삭제 하시겠습니까?")==false) return;
		  		//삭제처리요청
		  		location.href = "delete.do?idx=" + idx; // VisitDeleteAction
  				
  				
  			},
  			error	:	function(err){
  				alert(err.responseText);
  			}
  		});
  		
  		
  		
  		
  	}//end:del()
  	
  	
  	function modify_form(f){
  		
  		let idx		=  f.idx.value;          //게시물번호
  		let c_pwd	=  f.c_pwd.value.trim(); //확인비번
  		
  		if(c_pwd==""){
  			alert("수정할 게시물의 비밀번호를 입력하세요");
  			f.c_pwd.value="";
  			f.c_pwd.focus();
  			return;
  		}
  		
  		//Ajax 이용
  		$.ajax({
  			
  			url		:	"check_pwd.do",               // VisitCheckPwdAction
  			data	:	{"idx":idx , "c_pwd":c_pwd},  // check_pwd.do?idx=5&c_pwd=1234
  			dataType:	"json",                       // 수신데이터 Type
  			success	:	function(res_data){
  				
  				// res_data = {"result":true} or {"result":false}
  				if(res_data.result==false){
  					alert("수정 비밀번호가 틀립니다");
  					return;
  				}
  				
  			    //수정폼 띄우기
  		  		location.href = "modify_form.do?idx=" + idx;
  		  		
  			},
  			error	:	function(err){
  				alert(err.responseText);
  			}
  		});
  		
  		
  	}//end:modify_form()
  	
  	
  	function find(){
  		
  		let search		= $("#search").val();
  		let search_text = $("#search_text").val().trim();
  		
  		// 전체보기가 아닌데 검색어가 비어있는 경우
  		if(search!="all" && search_text==""){
  			alert("검색어를 입력하세요!");
  			$("#search_text").val("");
  			$("#search_text").focus();
  			return;
  		}
  		
  		//자바스크립에서 페이지 이동 : JSP내에서 \${}표현을 자바스크립트변수로 사용하려면 앞에 \
  		//location.href=`list.do?search=\${ search }&search_text=` + search_text;
  		location.href="list.do?search=" + search + "&search_text=" 
  				                        + encodeURIComponent(search_text , "utf-8");
  		
  		
  		
  	}//end:find()
  
  
  </script>
  
  
 <script type="text/javascript">
  // document내의 컨트롤이 배치완료 되면 호출 전체보기가 초기화
		  $(document).ready(function(){
		    // EL 표현식은 `$(document)`와 관련이 없으므로 공백을 제거합니다.
		    if ("${not empty param.search}" == "true") {
		      $("#search").val("${param.search}");
		    }
		    
		  //전체보기면
		    if ("${param.search}" === "all") { // 여기를 수정해주세요.
		        $("#search_text").val(""); // 공백(" ") 대신 빈 문자열("")로 수정하는 것이 좋습니다.
		    }
		  });
  
  </script>
  
  
</head>
<body>
  <div id="box">
      <h1 id="title">:::: 방명록 ::::</h1>
      
      
      <div class="row"  style="margin-top: 30px; margin-bottom: 5px;">
		  <div class="col-sm-3">
		     <input class="btn btn-primary" type="button" value="글쓰기"
                onclick="location.href='insert_form.do'"  >
		  </div>
		  
		  <!-- 검색메뉴 -->
		  <div class="col-sm-9" style="text-align: right;">
		     <form class="form-inline">
		        <select  class="form-control" id="search">
		             <option value="all">전체보기</option>
		             <option value="name">이름</option>
		             <option value="content">내용</option>
		             <option value="name_content">이름+내용</option>
		        </select>
		        <input class="form-control" id="search_text" value="${param.search_text }">
		        <input class="btn btn-primary" type="button" value="검색" onclick="find();">		         
		     </form>
		  </div>
		  
	  </div>
      
         
      
      <!-- 데이터 -->
      
      <!-- 데이터가 없을 경우 -->
      <c:if test="${ empty requestScope.list }">
         <div id="error_msg">
            등록된 게시물이 없습니다
         </div>
      </c:if>
      
      <!-- for(VisitVo vo : list) 동일함.  -->
      <c:forEach var="vo"  items="${ list }">
		 <div class="panel panel-primary">
			
			<!-- 제목 -->
			<div class="panel-heading">
			   <h4><b>${ vo.name }</b>님의 글</h4>
			</div>
			
			<!-- 내용 -->
			<div class="panel-body">
			   <form class="form-inline">
			       
			       <input type="hidden"  name="idx"  value="${ vo.idx }">
			       
				   <div class="common content">${ vo.content }</div>
				   <div class="common regdate">
				      <%--  
				                     012345678901234567890 
				          regdate = "2025-09-01 11:08:15.0"  
				      --%>
				      <label>등록일자</label> : ${  fn:substring(vo.regdate,0,16) }
				                                (${ vo.ip })
				   </div>
				   <div class="common pwd">
				      <label>비밀번호(${ vo.pwd })</label> : 
				               <input class="form-control"   type="password" name="c_pwd">
				               <input class="btn btn-success"   type="button" value="수정"
				                      onclick="modify_form(this.form);"> 
				               <input class="btn btn-danger"    type="button" value="삭제"
				                      onclick="del(this.form);"> 
				   </div>
			   </form>
			</div>
		 </div>
	  </c:forEach>
      
        
  </div>
</body>
</html>







