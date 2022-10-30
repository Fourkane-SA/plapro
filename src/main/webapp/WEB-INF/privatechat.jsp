<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Discussion privée</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
		<div class="main">
			<h4>
				<c:out value="${ userPrivateConversation }" />
			</h4>
			<div class="container">
				<c:forEach var="message" items="${ conversation }">
					<div class="row">
						<div class="col-1 center">
							<c:if test="${ message.idUserSender != -1 }">
								<br>
								<svg xmlns="http://www.w3.org/2000/svg" fill="currentColor"
									class="bi bi-person-circle" viewBox="0 0 16 16">
								  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
								  <path fill-rule="evenodd"
										d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
								</svg>
							</c:if>
						</div>
						<div class="col">
							<c:if test="${ message.idUserSender != -1 }">
								<br>
								<span class=nameChat> <c:out
										value="${ message.pseudoUserSender }" />
								</span>
								<span class=chatDate> <c:out value="${ message.time }" /></span>
								<br>
							</c:if>
							<c:out value="${ message.text }" />
							<br>
						</div>
					</div>
				</c:forEach>
			</div>
			<br>
			<form action="" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="text" name="text"
						placeholder="..." required> <input type="hidden" id="id"
						name="id" value="<c:out value="${ idUserChat }"/>">
					<button type="submit" class="btn btn-outline-dark">Envoyer
						un message</button>
				</div>
			</form>
		</div>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>