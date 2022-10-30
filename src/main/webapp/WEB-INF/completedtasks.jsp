<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Taches terminées</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h5>Taches terminées</h5>
			<c:forEach var="task" items="${ completedTasks }">
				<div class="row taskList">
					<div class="col-4"><c:out value="${ task.name }"/></div>
					<div class="col"><c:out value="${ task.description }"/></div>
					<div class="col-3">
						<button class="btn btn-outline-dark"
							onclick="window.location.href='task?id=<c:out value="${ task.id }"/>'">Voir
							la tache</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>