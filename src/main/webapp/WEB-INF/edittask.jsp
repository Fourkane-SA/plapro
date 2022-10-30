<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier tache</title>
<script type="text/javascript" src="Assets/js/canvasjs.min.js"></script>
<script type="text/javascript" src="Assets/js/functionsjs"></script>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<c:if test="${ not empty editTask }">
			<meta http-equiv="refresh"
				content="0;URL=task?id=<c:out value="${ editTask.id }"/>">
		</c:if>
		<c:if test="${ empty editTask }">
			<%@ include file="menu.jsp"%>
			<div class="main">
				<h4>Modifier tache</h4>
				<form action="" method="post">
					<div class="form-group">
						<label for="name">Nom de la tache</label> <input type="text"
							class="form-control" name="name" id="name" required
							placeholder="Entrez le nom de la tache" value="<c:out value="${ task.name }"/>">
					</div>
					
					<div class="form-group">
						<label for="start">Date de début</label> <input type="date"
							class="form-control" name="start" id="start" required value="<c:out value="${ task.start }"/>">
					</div>
					<div class="form-group">
						<label for="end">Date butoir</label> <input type="date"
							class="form-control" name="end" id="end" required value="<c:out value="${ task.end }"/>">
					</div>
					<div class="form-group">
						<label for="state" class="form-label">Avancement</label>
						<div class="range">
							<input type="range" class="form-range" min="0" max="100"
								value="<c:out value="${ task.progress }"/>" step="5" id="state" name="state" /> <span id="output"></span>/100
						</div>
						<script type="text/javascript">
							newProjectPrintState();
						</script>
					</div>
					<div id="finish"></div>
					<script type="text/javascript">
						printDateEndTask();
					</script>
					<div class="form-group">
						<label for="description">Description</label>
						<textarea class="form-control" id="description" name="description"
							rows="5"><c:out value="${ task.description }"/></textarea>
					</div>
					<div class="form-group">
						<label for="group">Groupe</label> <select class="form-select"
							id="group" name="group" required>
							<option value="" selected disabled hidden=true>Attribuez
								cette tache à un groupe</option>
							<option value="1">Fondateur</option>
						</select>
					</div>
					<input id="id" name="id" type="hidden"
						value='<c:out value="${ id }"/>'>
					<button type="submit" class="btn btn-primary">Modifier la
						tache</button>
					<button class="btn btn-danger"
						onclick="window.location.href='home'">Annuler</button>
				</form>
			</div>
			<%@ include file="footer.jsp"%>
		</c:if>
	</c:if>
</body>
</html>