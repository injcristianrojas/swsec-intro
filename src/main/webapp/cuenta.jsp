<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<% if (session.getAttribute("username") != null) { %>
<p>Usuario: <%=(String) session.getAttribute("username")%></p>
<% } %>
<form action="Account" method="post" name="mainForm" id="mainForm">
<p>
Cambiar Password: <input type="password" name="password" id="password"  /><br />
<input type="submit" name="submitButton" id="submitButton" value="Ingresar" />
</p>
</form>
<jsp:include page="footer.inc" />
