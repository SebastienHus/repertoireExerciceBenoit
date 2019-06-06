<%@ include file="/WEB-INF/TagLib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NetBanque Menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/banque.css" rel="stylesheet">
</head>

<body class="elbody">

	<!-- test si nous sommes conecté ! -->
	${userId }
<c:choose>
	<c:when test="${ userId  != null}">
		
	<p>${msgHome }</p>
	<p>Que souhaitez vous réaliser comme operation sur votre compte</p>

	<table border="0" width="100%">
		<tr>
			<td align="center" valign="top"><img src="<c:url value="images/image-bienvenue.jpg" />" border="0" height="98" alt="" /></td>
		</tr>
		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td align="center">
				<table>
					<tr>
						<td>
							<table width="100%" border="1">
								<tr class="ellignetableau1">
									<td width="446" class="elcelluletableau3">
										<img src="<c:url value="images/puce.gif"/>" width="13" height="18" alt="" />&nbsp;
										<a href="comptes/liste.html"> Liste de vos comptes</a>
									</td>
								</tr>
								<tr class="ellignetableau2">
									<td width="446" class="elcelluletableau3">
									<img src="<c:url value= "images/puce.gif" />" width="13" height="18" alt="" />&nbsp;
									<a href="comptes/virement.html"> Virement </a></td>
								</tr>
								
								<tr class="ellignetableau3">
									<td width="446" class="elcelluletableau3">
									<img src="<c:url value= "images/puce.gif" />" width="13" height="18" alt="" />&nbsp;
									<a  href="<c:url value="/ServletDeconnection" />" /> Deconnexion </a></td>
								</tr>
								
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>

</c:when>

<c:otherwise>

	<p>Petit coquinou tu essais de rentrer en scred ca vas pas le faire aller hop vas te logger ou t'inscrire</p>

</c:otherwise>
</c:choose>

</html>
