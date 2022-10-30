<c:forEach var="project" items="${ projects }">
	<div class="follow center">
		<div class="row">
			<div class="col">
				<c:out value="${ project.name }" />
			</div>
			<div class="col">
				Visibilit� :
				<c:out value="${ project.visibility }" />
			</div>
			<div class="col">
				Cr�� le : <br>
				<c:out value="${ project.creationDate }" />
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col">
				<c:out value="${ project.shortDescription }" />
			</div>
			<div class="col">
				Statut :
				<c:out value="${ project.status }" />
			</div>
			<div class="col">
				Mis � jour : <br>
				<c:out value="${ project.updateTime }" />
			</div>
		</div>
		<br>
		<button class="btn btn-outline-dark"
			onclick="window.location.href='projectdashboard?id=<c:out value='${ project.id }'/>'">Voir
			le projet</button>
	</div>
</c:forEach>