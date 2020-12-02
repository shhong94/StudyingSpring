<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.recomm').click(function(){
		let no = $(this).attr('text');
		$.ajax({
			type: 'post',
			url: '',
			data: {"" : }
			success: function(result){
				$('#print').html(result)
			}
		});
	})
});
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="latest-products">
		<div style="height: 130px;"></div>
		<div class="container">
			
			<div class="row">
				<c:forEach var="s" items="${ss }">
					<span class="btn btn-xs btn-warning recomm"><a href="../recommand/find.do?fd=${s }">${s }</a></span>&nbsp;
				</c:forEach>
			</div>
		</div>
		<div style="height: 15px;"></div>
		<div class="row" id="print">
		
		</div>
	</div>
</body>
</html>