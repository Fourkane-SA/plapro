<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<div class="row">
				<div class="col-4 center">
					<svg xmlns="http://www.w3.org/2000/svg" width="auto" height="auto"
						fill="currentColor" class="bi bi-person-circle"
						viewBox="0 0 16 16">
					  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
					  <path fill-rule="evenodd"
							d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
					</svg>
				</div>
				<div class="col-8">
					<h4>
						<c:out value="${ sessionScope.user.pseudo }" />
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-pencil-square"
							viewBox="0 0 16 16" onclick="window.location.href='editprofile'">
						  <path
								d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
						  <path fill-rule="evenodd"
								d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
						</svg>
					</h4>
					<h5>
						<c:out value="${ sessionScope.user.firstName }"></c:out>
						<c:out value="${ sessionScope.user.lastName }"></c:out>
					</h5>
					<p>
						<a href="follows?id=<c:out value="${ sessionScope.user.id }"/>">
							<c:out value="${ following }" /> abonnements
						</a> ● <a
							href="followers?id=<c:out value="${ sessionScope.user.id }"/>">
							<c:out value="${ followers }" /> abonnées
						</a> ●
						<c:out value="${ project }" />
						projets
					</p>
					<p>
						<c:out value="${ sessionScope.user.description }"></c:out>
					</p>
					<h6>
						Membre depuis le
						<c:out value="${ createdAt }"></c:out>
					</h6>
				</div>
			</div>
			<%@ include file="forEachlistProject.jsp"%>
			<div class="center">
				<button class="btn btn-outline-dark"
					onclick="window.location.href='logout'">Déconnection</button>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>