<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier un projet</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<c:if test="${ error == 'no' }">
			<meta http-equiv="refresh" content="0;URL=projectdashboard?id=<c:out value="${ project.id }" />">
		</c:if>
		<c:if test="${ empty error }">
			<%@ include file="menu.jsp"%>
			<div class="main">
				<h4>
					Modifier le projet
					<c:out value="${ project.name }" />
				</h4>
				<br>
				<form action="" method="post">
					<div class="form-group">
						<label for="name">Nom du projet</label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Entrez le nom du projet" 
							value='<c:out value="${ project.name }"></c:out>' required>
					</div>
					<br>
					<div class="form-group">
						<label for="shortdescription">Description brève du projet</label>
						<input type="text" class="form-control" id="shortdescription"
							name="shortdescription"
							value='<c:out value="${ project.shortDescription }"></c:out>'
							placeholder="Entrez une brève description du projet">
					</div>
					<br>
					<div class="form-group">
						<label for="description">Description du projet</label>
						<textarea class="form-control" rows="5" name="description" id="description"><c:out value="${ project.description }"/></textarea>
					</div>
					<br>
					<p>Visibilité du projet</p>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="visibility"
							id="public" value="public" checked> <label
							class="form-check-label" for="public"> public </label>
						<p class="form_subtitle">
							Tout le monde a accès aux informations du projet. <br>Certaines
							taches peuvent être explicitement privées
						</p>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="visibility"
							id="private" value="private"> <label
							class="form-check-label" for="private"> privé </label>
						<p class="form_subtitle">Seul les membres du projet ont accès
							au projet</p>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="visibility"
							id="open" value="open"> <label class="form-check-label"
							for="open"> ouvert </label>
						<p class="form_subtitle">
							Tout le monde peut proposer des modifications. <br>Ils
							doivent être validées par un membre du projet
						</p>
					</div>
					<div class="form-group">
						<label for="status">Statut du projet</label> <select class="form-select"
							id="status" name="status" required>
							<option value="en cours" selected>En cours</option>
							<option value="terminé">Terminé</option>
						</select>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Modifier le
						projet</button>
				</form>
			</div>
			<%@ include file="footer.jsp"%>
		</c:if>
	</c:if>
</body>
</html>