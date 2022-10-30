<!doctype html>
<html lang="fr">
<head>
<meta charset="utf-8">
<title>PlaPro</title>
<link href="Assets/css/StyleWebsite.css" rel="stylesheet">
</head>
<body>
	<c:if test="${ not empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=home">
	</c:if>
	<c:if test="${ empty sessionScope.user }">
		<h1 class="mainTitle">
			<span style="color: red">PLA</span>NIFICATION<br> DE <span
				style="color: red">PRO</span>JETS
		</h1>
		<h4 class="subTitle">
			Réalisez vos projets facilement<br> avec PlaPro, une application
			de<br> gestion de projets
		</h4>
		<br>
		<br>
		<div class="blocCenterLoginRegister">
			<div>
				<button class="btn btn-outline-dark"
					onclick="window.location.href='register'">Inscription</button>
			</div>
			<br>
			<div>
				<button class="btn btn-outline-dark"
					onclick="window.location.href='login'">Connexion</button>
			</div>
		</div>
		<%@ include file="WEB-INF/footer.jsp"%>
	</c:if>
</body>
</html>