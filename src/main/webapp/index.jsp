<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<c:if test="${not empty username}" >
<p>Usuario: ${username}</p>
</c:if>
<p>
<a href="login.jsp">Login</a><br />
<a href="saludos.jsp">Saludos</a>
</p>
<jsp:include page="footer.inc" />
