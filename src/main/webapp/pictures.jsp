<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<jsp:include page="header.jsp" />
<jsp:include page="username.jsp" />
<div class="yoxview">
<%
String localDir = getServletContext().getRealPath("/upload");
String webDir = "upload/";
File dir = new File(localDir);
File[] list = dir.listFiles();
for (int i = 0; i < list.length; i++) {
	if (!list[i].isDirectory() && list[i].getName().toLowerCase().endsWith(".jpg")) {
%>
	<a href="<%=webDir + list[i].getName()%>"><img style="height:auto; width:auto; max-width:150px; max-height:150px;" src="<%=webDir + list[i].getName()%>" /></a>
<%
	}
}
%>
</div>
<script type="text/javascript" src="js/yoxview/yoxview-init.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
			$(".yoxview").yoxview();
	});
</script>

<p style="text-align:center;"><a href="upload.jsp">Subir imagen</a></p>
<jsp:include page="footer.inc" />
