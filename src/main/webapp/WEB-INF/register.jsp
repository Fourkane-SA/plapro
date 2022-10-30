<!doctype html>
<html lang="fr">
<head>
<title>Inscription</title>
</head>
<body>
	<c:if test="${ not empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=home">
	</c:if>
	<c:if test="${ empty sessionScope.user }">
		<c:if test="${ error == 'no' }">
			<meta http-equiv="refresh" content="0; URL=login">
		</c:if>
		<c:if test="${ error != 'no' }">
			<div class="registerLoginBloc">
				<h3>Inscription</h3>
				<c:if test="${ error == 'yes' }">
					<div class="alert alert-danger" role="alert">Le pseudo ou
						l'adresse mail est déjà pris</div>
				</c:if>
				<form action="" method="post">
					<div class="form-group">
						<label for="pseudo">Pseudo</label> <input type="text"
							class="form-control" id="pseudo" name="pseudo" required
							placeholder="Entrez votre pseudo">
					</div>
					<div class="form-group">
						<label for="password">Mot de passe</label> <input type="password"
							class="form-control" id="password" name="password" required
							placeholder="Entrez votre mot de passe">
					</div>
					<div class="form-group">
						<label for="email">Adresse mail</label> <input type="email"
							class="form-control" id="email" name="email" required
							placeholder="Entrez votre adresse mail">
					</div>
					<div class="form-group">
						<label for="firstname">Prénom</label> <input type="text"
							class="form-control" id="firstname" name="firstname" required
							placeholder="Entrez votre prénom">
					</div>
					<div class="form-group">
						<label for="lastname">Nom</label> <input type="text"
							class="form-control" id="lastname" name="lastname" required
							placeholder="Entrez votre nom">
					</div>
					<div class="form-group">
						<label for="gender">Sexe</label> <select class="form-select"
							id="gender" name="gender" required>
							<option value="" selected disabled hidden=true>Choisir
								un sexe</option>
							<option value="man">Homme</option>
							<option value="woman">Femme</option>
							<option value="none">Ne souhaite pas répondre</option>
						</select>
					</div>
					<div class="form-group">
						<label for="birth">Date de naissance</label> <input type="date"
							class="form-control" id="birth" name="birth" required>
					</div>
					<br>
					<button type="submit" class="btn btn-outline-dark">S'inscrire</button>
					<p>
						Déjà inscrit ? <a href="login">Se connecter</a>
					</p>
				</form>
			</div>
		</c:if>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>