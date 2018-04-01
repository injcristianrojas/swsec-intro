<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />
<c:if test="${not empty username}" >
<p>Usuario: ${username}</p>
</c:if>
<form action="Account" method="post" name="mainForm" id="mainForm">
<p>
Cambiar Password: <input type="password" name="password" id="password"  /><br />
<input type="submit" name="submitButton" id="submitButton" value="Ingresar" />
</p>
</form>
<jsp:include page="footer.inc" />
