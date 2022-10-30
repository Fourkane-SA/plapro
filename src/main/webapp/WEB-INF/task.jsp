<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tache : <c:out value="${ t.name }" /></title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>
				<c:out value="${ t.name }" />
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16" onclick="window.location.href='edittask?id=<c:out value="${ t.id }" />'">
  				<path
						d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
  				<path fill-rule="evenodd"
						d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
				</svg>
			</h4>

			<p>
				<c:out value="${ t.description }" />
			</p>
			<h5>Avancement</h5>
			<div class="progress">
				<div class="progress-bar" role="progressbar"
					style="width: <c:out value="${ t.progress }"/>%; background-color:rgb(<c:out value="${ r }"/>,<c:out value="${ g }"/>,0);"
					aria-valuenow="<c:out value="${ t.progress }"/>" aria-valuemin="0"
					aria-valuemax="100">
					<c:out value="${ t.progress }" />
					%
				</div>
			</div>
			<br>
			<div>
				Date de début de la tache :
				<c:out value="${ t.start }" />
				<br> Date butoir de la tache :
				<c:out value="${ t.end }" />
				<br>
				<c:if test="${ t.progress == 100 }">
				Tache terminée <br>
				Date de fin de la tache : <c:out value="${ t.finish }" />
				</c:if>
				<c:if test="${ t.progress == 0 }">
				Tache non commencée <br>
				</c:if>
				<c:if test="${ t.progress < 100 && t.progress > 0 }">
				Tache en cours <br>
				</c:if>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>