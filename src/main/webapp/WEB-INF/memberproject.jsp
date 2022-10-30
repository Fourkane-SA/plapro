<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Membres</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>Membres :</h4>
			<p>
				Nombre de contributeurs :
				<c:out value="${ nMembers }" />
			</p>
			<p>
				Dernier membre actif : <a
					href='publicprofile?id=<c:out value="${ lastActiveMember.id }"/>'><c:out
						value="${ lastActiveMember.pseudo }" /></a>
			</p>
			<p>
				Membre le plus actif : <a
					href='publicprofile?id=<c:out value="${ mostActiveMember.id }"/>'><c:out
						value="${ mostActiveMember.pseudo }" /></a>
			</p>
			Membres : <br>
			<div class="container">
				<div class="row" style="text-align:center;">
					<c:forEach var="user" items="${ users }">
						<div class="col-2">
							<svg xmlns="http://www.w3.org/2000/svg" width="60" height="60"
								fill="currentColor" class="bi bi-person-circle"
								viewBox="0 0 16 16"
								onclick="location.href = 'publicprofile?id=<c:out value="${ user.id }"/>'">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
						  <path fill-rule="evenodd"
									d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
						</svg>
							<br>
							<c:out value="${ user.pseudo }" />
						</div>
					</c:forEach>
				</div>
			</div>
			<br>
			<button class="btn btn-outline-dark"
				onclick="window.location.href='addmemberproject?id=<c:out value="${ id }" />'">
				Ajouter un membre</button>
			<br> <br>
			<button class="btn btn-outline-dark"
				onclick="window.location.href='deletememberproject?id=<c:out value="${ id }" />'">
				Supprimer un membre</button>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>