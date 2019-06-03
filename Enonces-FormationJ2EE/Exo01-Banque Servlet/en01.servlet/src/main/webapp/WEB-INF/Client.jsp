<%@ include file="/WEB-INF/TagLib.jsp" %>

<!DOCTYPE html>

<html>

	<head>

		<meta charset="UTF-8">
		<title>Liste client</title>
		<%@ include file="/WEB-INF/linkCss.jsp" %>
	
	</head>
	
	
	<body>
	
	<c:forEach items="${userList}" var="utilisateur" >
	
		<p> <c:out value="${utilisateur.nom }" /> 
			<c:out value="${utilisateur.prenom }"/></p>
			 
			<form method="post" action="ServletCompte" >
			
				<input type="hidden" name="userId" value="${utilisateur.numero }" />
			 
			 	<input type="submit" value="Consulter ses comptes" />
			 
			</form>
	
	</c:forEach>
	
	
	</body>

</html>