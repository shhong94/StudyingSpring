<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
	
	  $('#info').css("cursor", "pointer");
	  $('#info').click(function(){
		  $( "#dialog" ).dialog();
	  })
    
  } );
  </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center" id="info">${chef }님의 레시피</h3>
			<div class="row">
				<div class="text-right">
					<input type=text id=column size=20 class="input-sm">
					<input type=button id=findBtn class="btn btn-sm btn-primary" value="검색">
				</div>
			</div>
			<h5 class="text-right">${curpage } page / ${totalpage } pages</h5>
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
			      <div class="panel panel-info">
				      <div class="panel-heading">${vo.title }</div>
				      <div class="panel-body">
					      	<div class="thumbnail"> 
					      		<img src="${vo.poster }" width=100%>
				      		</div>
				      </div>
			      </div>     
				  
			    </div>
			</c:forEach>
		</div>
		<div class="row">
			<ul class="pager">
			    <li class="previous"><a href="list.do?page=${curpage>1?curpage-1:curpage }&chef=${chef}">이전</a></li>
			    <li class="next"><a href="list.do?page=${curpage<totalpage?curpage+1:curpage }&chef=${chef}">다음</a></li>
			  </ul>
		</div>
		<div id="dialog" title="${chef }님의 정보" style="display: none;">
		  <p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
		</div>
	</div>
</body>
</html>