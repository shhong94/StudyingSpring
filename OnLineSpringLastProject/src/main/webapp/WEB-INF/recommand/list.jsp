<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.recomm').hover(function(){
		$(this).css("cursor", "pointer");
	}, function(){
		$(this).css("cursor", "");
	})
	
	$('.recomm').click(function(){
		let no = $(this).attr('value');
		
		$.ajax({
			type: 'post',
			url: '../recommand/recomm_list.do',
			data: {"no":no},
			success: function(res){
				$('#print1').html(res);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="latest-products">
		<div style="height: 130px;"></div>
      	<div class="container">
			<div class="row">
				<div class="text-center">
					<span class="btn btn-lg btn-primary recomm" value="1">상황</span>
					<span class="btn btn-lg btn-success recomm" value="2">감성</span>
					<span class="btn btn-lg btn-info recomm" value="3">스타일</span>
					<span class="btn btn-lg btn-danger recomm" value="4">날씨/계절</span>
				</div>
			</div>
			<div style="height: 10px;"></div>
			<div class="row" id="print1">
				
			</div>
		</div>
	</div>
</body>
</html>