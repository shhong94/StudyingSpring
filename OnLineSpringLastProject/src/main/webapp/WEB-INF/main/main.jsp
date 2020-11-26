<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>SIS Recipe & Food</title>

    <!-- Bootstrap core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!--

TemplateMo 546 Sixteen Clothing

https://templatemo.com/tm-546-sixteen-clothing

-->

    <!-- Additional CSS Files -->
    <link rel="stylesheet" href="../assets/css/fontawesome.css">
    <link rel="stylesheet" href="../assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="../assets/css/owl.css">
    <link rel="stylesheet" href="../assets/css/food.css">
    
    <style type="text/css">
    	.modal-content{
    		margin-top: 300px;
    	}
    </style>

  </head>

  <body>

    <!-- ***** Preloader Start ***** -->
    <div id="preloader">
        <div class="jumper">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>  
    <!-- ***** Preloader End ***** -->

    <!-- 헤더 ------------------------------------------------------------------------------------------------------------------------------------------------------ 헤더 -->
    <tiles:insertAttribute name="header"/>

    <!-- content -------------------------------------------------------------------------------------------------------------------------------------------------- content -->
    <tiles:insertAttribute name="content"/>

    
    <!-- 푸터 ------------------------------------------------------------------------------------------------------------------------------------------------ 푸터 -->
    <tiles:insertAttribute name="footer"/>


    <!-- Bootstrap core JavaScript -->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


    <!-- Additional Scripts -->
    <script src="../assets/js/custom.js"></script>
    <script src="../assets/js/owl.js"></script>
    <script src="../assets/js/slick.js"></script>
    <script src="../assets/js/isotope.js"></script>
    <script src="../assets/js/accordions.js"></script>


    <script language = "text/Javascript"> 
      cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
      function clearField(t){                   //declaring the array outside of the
      if(! cleared[t.id]){                      // function makes it static and global
          cleared[t.id] = 1;  // you could use true and false, but that's more typing
          t.value='';         // with more chance of typos
          t.style.color='#fff';
          }
      }
    </script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$('#logBtn').click(function(){
    			let id = $('#id').val();
    			if(id.trim() == ""){
    				$('#id').focus();
    				return;
    			}
    			
    			let pwd = $('#pwd').val();
    			if(pwd.trim() == ""){
    				$('#pwd').focus();
    				return;
    			}
    			
    			$.ajax({
    				type: 'post',
    				url: '../main/login.do',
    				data: {"id" : id, "pwd" : pwd},
    				success: function(res){
    					let no = res.trim();
    					if(no == 1){
    						$('#result').text("아이디가 존재하지 않습니다");
    						$('#id').val("");		// 아이디 입력창을 공백으로
    						$('#pwd').val("");		// 비밀번호 입력창을 공백으로
    						$('#id').focus();		// 아이디 입력창에 포커스
    					}
    					else if(no == 2){
    						$('#result').text("비밀번호가 틀립니다");
    						$('#pwd').val("");		// 비밀번호 입력창을 공백으로
    						$('#pwd').focus();		// 비밀번호 입력창에 포커스
    					}
    					else{
    						$('#myModal').modal("hide");
    						$('#id').val("");
    						$('#pwd').val("");
    						$('#result').text("");
    					}
    				}
    			});
    		})
    	})
    </script>
    
    
    <!-- 로그인 모달 ------------------------------------------------------------------------------------------------------------------------------ 로그인 모달 -->
    <!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	      	<h3>로그인</h3>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	      <div class="modal-body">
	        <table class="table">
	        	
	        	<tr>
	        		<td width=25% class="text-right">ID</td>
	        		<td width=75%>
	        			<input type=text id="id" class="input-sm" size=15 placeholder="아이디 입력">
	        		</td>
	        	</tr>
	        	<tr>
	        		<td width=25% class="text-right">PW</td>
	        		<td width=75%>
	        			<input type=password id="pwd" class="input-sm" size=15 placeholder="비밀번호 입력">
	        		</td>
	        	</tr>
	        	<tr>
	        		<td class="text-center" colspan="2">
	        			<span style="color: red;" id="result"></span>
	        		</td>
	        	</tr>
	        </table>
	      </div>
	      <div class="modal-footer">
	      	<input type="button" class="btn btn-success" value="로그인" id="logBtn">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>

  </div>
</div>


  </body>

</html>