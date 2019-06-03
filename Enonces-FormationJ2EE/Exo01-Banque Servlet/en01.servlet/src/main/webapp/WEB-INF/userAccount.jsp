<%@ include file="/WEB-INF/TagLib.jsp" %>

<!DOCTYPE html>

<html>

	<head>

		<meta charset="UTF-8">
		<title>Comptes</title>
		<%@ include file="/WEB-INF/linkCss.jsp" %>
	
	</head>
	
	
	<body>
	
		<c:forEach items="${userListAccount }"  var="account" >
		
			<p> ${account.libelle }</p>
			<p> ${account.solde }</p>
		
		</c:forEach>
		
		<a href=<c:url value="/ServletClient"/> >Retour liste client</a>

	</body>

</html>