<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ user.pseudo }" /></title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<c:if test="${ not empty userProfile }">
			<meta http-equiv="refresh" content="0;URL=profile">
		</c:if>
		<c:if test="${ empty userProfile }">
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
							<c:out value="${ user.pseudo }" />
						</h4>
						<h5>
							<c:out value="${ user.firstName }"></c:out>
							<c:out value="${ user.lastName }"></c:out>
						</h5>
						<p>
							<a href="follows?id=<c:out value="${ user.id }"/>"> <c:out
									value="${ following }" /> abonnements
							</a> ● <a href="followers?id=<c:out value="${ user.id }"/>"> <c:out
									value="${ followers }" /> abonnées
							</a> ●
							<c:out value="${ project }" />
							projets
						</p>
						<p>
							<c:out value="${ user.description }"></c:out>
						</p>
						<h6>
							Membre depuis le
							<c:out value="${ user.createdAt }"></c:out>
						</h6>
						<c:if test="${ isFollowing == true }">
							<button type="button" class="btn btn-outline-dark"
								onclick="location.href = 'publicprofile?id=<c:out value="${ user.id }"/>&unfollow=true'">Se
								désabonner</button>
						</c:if>
						<c:if test="${ isFollowing == false }">
							<button type="button" class="btn btn-outline-dark"
								onclick="location.href = 'publicprofile?id=<c:out value="${ user.id }"/>&follow=true'">S'abonner</button>
						</c:if>
						<button type="button" class="btn btn-outline-dark" disabled>Contacter</button>
					</div>
				</div>
				<%@ include file="forEachlistProject.jsp"%>
			</div>

			<%@ include file="footer.jsp"%>
		</c:if>
	</c:if>
</body>
</html>