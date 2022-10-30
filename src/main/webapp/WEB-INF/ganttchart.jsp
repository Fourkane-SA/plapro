<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="Assets/css/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="Assets/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="Assets/css/jquery.ganttView.css" />
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>
<title>Diagramme de Gantt</title>
</head>
<body>
	<c:if test="${ empty sessionScope.user }">
		<meta http-equiv="refresh" content="0;URL=/PlaPro">
	</c:if>
	<c:if test="${ not empty sessionScope.user }">
		<%@ include file="menu.jsp"%>
			<div id="ganttChart"></div>
			<br />
			<br />
			<div id="eventMessage"></div>

			<script type="text/javascript" src="Assets/js/jquery-1.4.2.js"></script>
			<script type="text/javascript" src="Assets/js/date.js"></script>
			<script type="text/javascript" src="Assets/js/jquery-ui-1.8.4.js"></script>
			<script type="text/javascript" src="Assets/js/jquery.ganttView.js"></script>
			<script type="text/javascript" src="Assets/js/data.js"></script>
			<script type="text/javascript">
				$(function() {
					$("#ganttChart")
							.ganttView(
									{
										data : ganttData,
										slideWidth : 900,
										behavior : {
											onClick : function(data) {
												var msg = data.name;
												$("#eventMessage").text(msg);
											}
										}
									});

					// $("#ganttChart").ganttView("setSlideWidth", 600);
				});
			</script>
		<%@ include file="footer.jsp"%>
	</c:if>
</body>
</html>