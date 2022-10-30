<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ project.name }" /></title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>
				<c:out value="${ project.name }" />
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16" onclick="window.location.href='editproject?id=<c:out value="${ project.id }" />'">
  				<path
						d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
  				<path fill-rule="evenodd"
						d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
				</svg>
			</h4>
			
			<h6><c:out value="${ project.shortDescription }" /></h6>
			<h4>Description :</h4>
			<p>
				<c:out value="${ project.description }" />
			</p>
			<h4>Informations :</h4>
			<p>
				Visibilité :
				<c:out value="${ project.visibility }" />
				<br> Date de création :
				<c:out value="${ project.creationDate }" />
				<br> Date de derniere mise à jour :
				<c:out value="${ project.updateTime }" />
				<br> Stutut :
				<c:out value="${ project.status }" />
				<br>
			</p>
			<h4>Statistiques :</h4>
			<p>
				Nombre de taches :
				<c:out value="${ n }" />
				(
				<c:out value="${ nLate }" />
				en retard ) <br> Taches non commencés :
				<c:out value="${ tasksToDo }" />
				(
				<c:out value="${ countTasksToDoLate }" />
				en retard) <br> Taches en cours :
				<c:out value="${ currentTasks }" />
				(
				<c:out value="${ countTasksInProgressLate }" />
				en retard) <br> Taches terminés :
				<c:out value="${ taskFinished }" />
				(
				<c:out value="${ countTasksCompletedLate }" />
				terminés en retard)
			</p>
			<h4>Membres :</h4>
			<div class="container">
				<div class="row" style="text-align: center;">
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
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>