<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${count == 0 }">
		<h3 class="text-center">검색 결과가 없습니다</h3>
	</c:if>
	<c:if test="${count > 0 }">
		
		<c:forEach var="vo" items="${list }">
			<div class="col-md-3">
				<div class="product-item">
					<img src="${vo.poster }" title="${vo.title }" alt="Nature" style="width:100px; height:100px;">
			    </div>
		    </div>
		</c:forEach>
		
	
	</c:if>
	
</body>
</html>