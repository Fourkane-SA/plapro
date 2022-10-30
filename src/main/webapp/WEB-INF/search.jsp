<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rechercher</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>Recherche</h4>
			<br>
			<form action="" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="search" name="search"
						placeholder="Entrez votre recherche">
				</div>
				<br>
				<button type="submit" class="btn btn-outline-dark">Rechercher</button>
			</form>
			<c:if test="${ empty projects }">
				<c:if test="${ not empty search }">
					<p>Aucun projet n'a été trouvé</p>
				</c:if>
			</c:if>
			<%@ include file="forEachlistProject.jsp"%>
			<c:if test="${ empty users }">
				<c:if test="${ not empty search }">
					<p>Aucun utilisateur n'a été trouvé</p>
				</c:if>
			</c:if>
			<%@ include file="forEachlistUser.jsp"%>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>