<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Informations personnelles</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h3>Modifiez vos informations personnelles :</h3>
			<c:if test="${ error == 'no' }">
				<div class="alert alert-success" role="alert">Les
					modifications ont été effectuées avec succès</div>
			</c:if>
			<c:if test="${ error == 'password' }">
				<div class="alert alert-danger" role="alert">Le mot de passe
					est incorrect</div>
			</c:if>
			<c:if test="${ error == 'pseudo' }">
				<div class="alert alert-danger" role="alert">Le pseudo est
					déjà pris</div>
			</c:if>
			<c:if test="${ error == 'email' }">
				<div class="alert alert-danger" role="alert">L'adresse mail a
					déjà été pris</div>
			</c:if>
			<form action="" method="post">
				<div class="form-group">
					<label for="pseudo">Pseudo (*)</label> <input type="text"
						class="form-control" id="pseudo" name="pseudo" required
						value="<c:out value='${ sessionScope.user.pseudo }'/>">
				</div>
				<div class="form-group">
					<label for="password">Mot de passe (*)</label> <input
						type="password" class="form-control" id="password" name="password"
						required placeholder="Entrez votre mot de passe">
				</div>
				<div class="form-group">
					<label for="newpassword">Nouveau mot de passe</label> <input
						type="password" class="form-control" id="newpassword"
						name="newpassword"
						placeholder="Changez votre mot de passe (optionnel)">
				</div>
				<div class="form-group">
					<label for="email">Adresse mail (*)</label> <input type="email"
						class="form-control" id="email" name="email" required
						value="<c:out value='${ sessionScope.user.email }'/>">
				</div>
				<div class="form-group">
					<label for="firstname">Prénom (*)</label> <input type="text"
						class="form-control" id="firstname" name="firstname" required
						value="<c:out value='${ sessionScope.user.firstName }'/>">
				</div>
				<div class="form-group">
					<label for="lastname">Nom (*)</label> <input type="text"
						class="form-control" id="lastname" name="lastname" required
						value="<c:out value='${ sessionScope.user.lastName }'/>">
				</div>
				<div class="form-group">
					<label for="gender">Sexe (*)</label> <select class="form-select"
						id="gender" name="gender" required>
						<option value="" selected disabled hidden=true>Choisir un
							sexe</option>
						<option value="man">Homme</option>
						<option value="woman">Femme</option>
						<option value="none">Ne souhaite pas répondre</option>
					</select>
				</div>
				<div class="form-group">
					<label for="birth">Date de naissance (*)</label> <input type="date"
						class="form-control" id="birth" name="birth" required>
				</div>
				<div class="form-group">
					<label for="description">Description</label>
					<textarea class="form-control" id="description" name="description"
						rows="5"><c:out
							value="${ sessionScope.user.description }" /></textarea>
				</div>
				<br>
				<button type="submit" class="btn btn-primary">Modifier</button>
				<button class="btn btn-danger"
					onclick="window.location.href='profile'">Annuler</button>
			</form>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>