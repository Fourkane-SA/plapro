<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String dataPoints = (String) request.getAttribute("dataPoints");
String dataPoints2 = (String) request.getAttribute("dataPoints2");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tableau de bord</title>
<script type="text/javascript">
	printChartDashboard(
<%out.print(dataPoints);%>
	,
<%out.print(dataPoints2);%>
	);
</script>
<script type="text/javascript"
	src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="mainLarge">
			<br>
			<h4>Tableau de bord</h4>
			<div class="album py-5">
				<div class="container">
					<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
						<div class="col">
							<div class="card shadow-sm">
								<div class="card-body">
									<h4>Nom du projet :</h4>
									<p>
										<c:out value="${ project.name }" />
									</p>
									<h4>Description :</h4>
									<p>
										<c:out value="${ project.shortDescription }" />
									</p>
									<button class="btn btn-outline-dark"
										onclick="window.location.href='projectsetting?id=<c:out value="${ project.id }"/>'">
										Voir plus</button>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<div class="card-body">
									<p>
										Nombre de taches :
										<c:out value="${ n }" />
									</p>
									<p>
										Taches à faire : <a
											href="taskstodo?id=<c:out value="${ id }"/>"> <c:out
												value="${ unstarted }" />
										</a>
									</p>
									<p>
										Taches en cours : <a
											href="tasksinprogress?id=<c:out value="${ id }"/>"> <c:out
												value="${ unfinish }" />
										</a>
									</p>
									<p>
										Taches réalisés : <a
											href="completedtasks?id=<c:out value="${ id }"/>"> <c:out
												value="${ finish }" />
										</a>
									</p>
									<button class="btn btn-outline-dark"
										onclick="window.location.href='newtask?id=<c:out value="${ project.id }"/>'">Nouvelle
										tache</button>
								</div>
							</div>
						</div>
						<div class="col scroll">
							<div class="card shadow-sm">
								<div class="card-body " style="height: max-content;">
									<h4>Activité recente :</h4>
									<c:forEach var="a" items="${ activity }">
										<div class="notif1"
											onclick="location.href = '<c:out value="${ a.link }"/>'">
											<c:out value="${ a.text }" />
											<br> <span class=nameChat><c:out
													value="${ a.pseudoUser }" /></span>
											<c:out value="${ a.createdAt }" />
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="col">
							<div id="chartContainer" style="height: 250px; width: auto;"></div>
						</div>
						<div class="col scroll">
							<div class="card shadow-sm">
								<div class="card-body" style="height: max-content;">
									<h4>Prochaines tâches à faire :</h4>
									<c:forEach var="task" items="${ upcommingTasks }">
										<div class="alert alert-primary" role="alert"
											onclick="window.location.href='task?id=<c:out value="${ task.id }"/>'">
											<p class="alert-heading" style="font-weight: bold;">
												<c:out value="${ task.name }" />
											</p>
											<p>
												<c:out value="${ task.description }"></c:out>
											</p>
											<hr>
											<p class="mb-0">
												<c:out value="${ task.end }" />
												- tâche réalisée à
												<c:out value="${ task.progress }" />
												&#37;
											</p>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<c:if test="${ empty tasksLate }">
							<div class="col">
								<div class="card shadow-sm">
									<div class="card-body">
										<h4 style="text-align: center; margin-top: 25%;">Vous
											n'avez aucune tâche en retard</h4>
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${ not empty tasksLate }">
							<div class="col scroll">
								<div class="card shadow-sm">
									<div class="card-body" style="height: max-content;">
										<h4>Taches en retard :</h4>
										<c:forEach var="task" items="${ tasksLate }">
											<div class="alert alert-danger" role="alert"
												onclick="window.location.href='task?id=<c:out value="${ task.id }"/>'">
												<p class="alert-heading" style="font-weight: bold;">
													<c:out value="${ task.name }" />
												</p>
												<p>
													<c:out value="${ task.description }"></c:out>
												</p>
												<hr>
												<p class="mb-0">
													<c:out value="${ task.end }" />
													- tâche réalisée à
													<c:out value="${ task.progress }" />
													&#37;
												</p>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</c:if>
						<div class="col">
							<div id="chartActivity" style="height: 250px; width: auto;">
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<div class="card-body">
									<h4>Membres</h4>
									<br>
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
									<button class="btn btn-outline-dark"
										onclick="window.location.href='memberproject?id=<c:out value="${ project.id }" />'">Voir
										plus</button>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow-sm">
								<div class="card-body">
									<h4>Avancement du projet :</h4>
									<div class="progress">
										<div class="progress-bar" role="progressbar"
											style="width: <c:out value="${ progress }"/>%; background-color:rgb(<c:out value="${ r }"/>,<c:out value="${ g }"/>,0);"
											aria-valuenow="<c:out value="${ progress }"/>"
											aria-valuemin="0" aria-valuemax="100">
											<c:out value="${ progress }" />
											%
										</div>
									</div>
									<br>
									<button class="btn btn-outline-dark"
										onclick="window.location.href='ganttchart?id=<c:out value="${ project.id }"/>'">Voir
										le diagramme de Gantt</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>