<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>Discussions</h4>
			<form action="privatechat" method="get">
				<select class="form-select" name="id" id="id" required>
					<option value="" selected disabled hidden=true>Rechercher
						un utilisateur</option>
					<c:forEach var="user" items="${ users }">
						<option value="<c:out value="${ user.id }"/>"><c:out
								value="${ user.pseudo }" /></option>
					</c:forEach>
				</select> <br>
				<button type="submit" class="btn btn-outline-dark">Discuter</button>
			</form>
			<br>
			<c:forEach var="message" items="${ messages }">
				<div class="row recentsMessages">
					<div class="col-1 center">
						<svg xmlns="http://www.w3.org/2000/svg" fill="currentColor"
							class="bi bi-person-circle" viewBox="0 0 16 16">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
						  <path fill-rule="evenodd"
								d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
						</svg>
					</div>
					<div class="col">
						<span class=nameChat> <c:out
								value="${ message.otherUserPrivateDiscussion }" />
						</span><br>
						<c:out value="${ message.pseudoUserSender }" />
						:
						<c:out value="${ message.text }" />
					</div>
				</div>
			</c:forEach>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>