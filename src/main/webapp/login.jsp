<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.inc" />
<%
String error = request.getParameter("notFoundError");
if (error != null) {
	out.println("<p>ERROR: Usuario no encontrado</p>");
}
%>
<form action="Login" method="post">
<p>
Nombre: <input type="text" name="username" id="username" /><br />
Password: <input type="password" name="password" id="password"  /><br />
<input type="submit" value="Ingresar" />
</p>
</form>
<jsp:include page="footer.inc" />
