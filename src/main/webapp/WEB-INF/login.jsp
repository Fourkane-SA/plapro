<!doctype html>
<html lang="fr">
<head>
<title>Connexion</title>
</head>
<body>
	<c:if test="${ not empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=home">
	</c:if>
	<c:if test="${ empty sessionScope.user }">
		<div class="registerLoginBloc">
			<h3>Connexion</h3>
			<c:if test="${ error == 'yes' }">
				<div class="alert alert-danger" role="alert">Le pseudo ou le
					mot de passe est incorrect</div>
			</c:if>
			<form action="" method="post">
				<div class="form-group">
					<label for="pseudo">Pseudo</label> <input type="text"
						class="form-control" id="pseudo" name="pseudo"
						placeholder="Entrez votre pseudo">
				</div>
				<div class="form-group">
					<label for="password">Mot de passe</label> <input type="password"
						class="form-control" id="password" name="password"
						placeholder="Entrez votre mot de passe">
				</div>
				<br>
				<button type="submit" class="btn btn-outline-dark">Se
					connecter</button>
				<p>
					Pas encore de compte ? <a href="register">Inscrivez-vous</a>
				</p>
			</form>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>