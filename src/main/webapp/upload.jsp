<%@ page pageEncoding="UTF-8" %>
<jsp:include page="header.inc" />
<% if (session.getAttribute("username") != null) { %>
<p>Usuario: <%=(String) session.getAttribute("username")%></p>
<% } %>
<form action="UploadServlet" method="post" enctype="multipart/form-data" onSubmit="if(document.getElementById('file_to_upload').value == '') return false;">
<input type="file" name="file_to_upload" id="file_to_upload" size="50" />
<br />
<input type="submit" value="Subir archivo" />
</form>
<jsp:include page="footer.inc" />