<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un membre</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<c:if test="${ not empty addUser }">
			<meta http-equiv="refresh"
				content="0;URL=memberproject?id=<c:out value="${ id }"/>">
		</c:if>
		<c:if test="${ empty addUser }">
			<%@ include file="menu.jsp"%>
			<div class="main">
				<h4>Ajouter un membre :</h4>
				<br>
				<form action="" method="post">
					<div class="form-group">
						<label for="pseudo">Utilisateur</label> <select
							class="form-select" id="pseudo" name="pseudo" required>
							<option value="" selected disabled hidden=true>Choisir
								un utilisateur</option>
							<c:forEach var="user" items="${ users }">
								<option value="<c:out value="${ user.id }"/>">
									<c:out value="${ user.pseudo }" />
								</option>
							</c:forEach>
						</select>
					</div>
					<br> <input type="hidden" name="id" id="id"
						value='<c:out value="${ id }"/>'>
					<div class="form-group">
						<label for="group">Niveau d'autorisation</label> <select
							class="form-select" id="group" name="group" required>
							<option value="" selected disabled hidden=true>Choisir
								un niveau d'autorisation</option>
							<c:forEach var="group" items="${ groups }">
								<option value="<c:out value="${ group.id }"/>">
									<c:out value="${ group.name }" />
								</option>
							</c:forEach>
						</select>
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Ajouter le
						membre</button>
					<button type="submit" class="btn btn-danger">Annuler</button>
				</form>
			</div>
			<%@ include file="footer.jsp"%>
		</c:if>
	</c:if>
</body>
</html>