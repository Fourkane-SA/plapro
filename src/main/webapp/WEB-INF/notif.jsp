<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notifications</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>Liste de vos notifications :</h4>
			<c:forEach var="notif" items="${ notifs }">
				<div class="notif<c:out value="${ notif.seen }"/>"
					onclick="location.href = '<c:out value="${ notif.link }"/>'">
					<c:out value="${ notif.text }" />
					<br>
					<c:out value="${ notif.createdAt }" />
				</div>
			</c:forEach>





		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>