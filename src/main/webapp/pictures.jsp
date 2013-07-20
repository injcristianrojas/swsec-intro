<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<jsp:include page="header.jsp" />
	<% if (session.getAttribute("username") != null) { %>
	<p>Usuario: <%=(String) session.getAttribute("username")%></p>
	<% } %>
	<%
	String localDir = getServletContext().getRealPath("/upload");
	String webDir = "upload/";
	File dir = new File(localDir);
	File[] list = dir.listFiles();
	for (int i = 0; i < list.length; i++) {
		if (!list[i].isDirectory() && list[i].getName().toLowerCase().endsWith(".jpg")) {
	%>
	<img style="height:auto; width:auto; max-width:150px; max-height:150px;" src="<%=webDir + list[i].getName()%>" />
	<%
		}
	}
	%>
	<p style="text-align:center;"><a href="upload.jsp">Subir imagen</a></p>
<jsp:include page="footer.inc" />
