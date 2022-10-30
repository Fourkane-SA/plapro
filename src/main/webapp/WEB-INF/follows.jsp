<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Abonnements</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>Liste de vos abonnements :</h4>
			<br>
			<div class="container">
				<%@ include file="forEachlistUser.jsp"%>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>