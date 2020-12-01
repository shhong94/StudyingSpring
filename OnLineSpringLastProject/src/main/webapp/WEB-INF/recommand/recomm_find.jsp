<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="vo" items="${fList }">
          <div class="col-md-4">
            <div class="product-item">
              <a href="../food/food_detail.do?no=${vo.no }"><img src="${vo.poster }" alt=""></a>
              <div class="down-content">
                <a href="../food/food_detail.do?no=${vo.no }"><h4>${vo.title }</h4></a>
              </div>
            </div>
          </div>
         </c:forEach>
</body>
</html>