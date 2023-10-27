<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<%
String error = request.getParameter("notFoundError");
if (error != null) {
	out.println("<p>ERROR: Usuario " + request.getParameter("username") + " no encontrado</p>");
}
%>
<form action="Login?landingPage=saludos.jsp" method="post" name="mainForm" id="mainForm">
<p>
Nombre: <input type="text" name="username" id="username" /><br />
Password: <input type="password" name="password" id="password"  /><br />
<input type="submit" name="submitButton" id="submitButton" value="Ingresar" />
</p>
</form>
<jsp:include page="footer.inc" />
