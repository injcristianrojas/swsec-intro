<jsp:include page="header.inc" />
	<form action="WeakServlet" method="get">
		<p>Escriba su nombre en el cuadro y oprima el botón "Aceptar"</p>
		<input type="text" name="nombre">
		<input type="submit" value="Aceptar"><input type="reset" value="Limpiar">
	</form>
<jsp:include page="footer.inc" />