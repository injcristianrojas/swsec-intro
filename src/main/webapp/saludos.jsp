<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<jsp:include page="username.jsp" />
	<form action="Saludos" method="get">
		<p>Escriba su nombre en el cuadro y oprima el bot&oacute;n "Aceptar"</p>
		<input type="text" name="nombre">
		<input type="submit" value="Aceptar"><input type="reset" value="Limpiar">
	</form>
<jsp:include page="footer.inc" />
