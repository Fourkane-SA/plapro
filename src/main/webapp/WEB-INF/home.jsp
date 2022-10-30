<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<p>
				Bienvenue
				<c:out value="${ sessionScope.user.pseudo }"> !</c:out>
			</p>
			<button class="btn btn-outline-dark"
				onclick="window.location.href='newproject'">Nouveau projet</button>
			<h3>Vos projets :</h3>
			<%@ include file="forEachlistProject.jsp"%>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>