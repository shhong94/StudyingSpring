<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:350px;
}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.btn').click(function(){
		let id = $('#id').val();
		if(id.trim() == ""){			// id 공백시 포커스
			$('#id').focus();
			return;
		}
		
		let pwd = $('#pwd').val();		// pwd 공백시 포커스
		if(pwd.trim() == ""){
			$('#pwd').focus();
			return;
		}
		
		$('#frm').submit();				// id, pwd 입력했으면 submit 수행
	})
});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">로그인</h1>
			<form method="post" action="login_ok.do" id=frm>
			<table class="table">
				<tr>
					<td width=20% class="text-right">ID</td>
					<td width=80%>
						<input type=text name=id size=15 id=id>
					</td>
				</tr>
				<tr>
					<td width=20% class="text-right">PW</td>
					<td width=80%>
						<input type=password name=pwd size=15 id=pwd>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=button value=로그인 class="btn btn-sm btn-primary">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	
	
</body>
</html>