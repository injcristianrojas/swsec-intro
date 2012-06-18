<jsp:include page="header.inc" />
<% if (session.getAttribute("username") != null) { %>
<p>Usuario: <%=(String) session.getAttribute("username")%></p>
<% } %>
<p>
<a href="login.jsp">Login</a><br />
<a href="Wall">Muro de visitas</a><br />
<a href="saludos.jsp">Saludos</a>
</p>
<jsp:include page="footer.inc" />