<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*, model.Event,model.Photo "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Your Events</title>

<script type="text/javascript" src="fancybox/lib/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<link rel="stylesheet"
	href="fancybox/source/jquery.fancybox.css?v=2.1.4" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="fancybox/source/jquery.fancybox.pack.js?v=2.1.4"></script>
<!--  <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" /> -->
<link rel="StyleSheet" href="styles/calendar.css" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<br>
	<div id="main_div">
			<form id="form1" method="post"
			enctype="multipart/form-data">
			<%
			String eidS = (String)request.getAttribute("eid");
				if (request.getAttribute("eid") != null) {
			%>
			<input type="text" name="eid" 
				value="<%=request.getAttribute("eid")%>" />
			<%
				}
			%>
			<fieldset>
				<legend>Photo Upload:</legend>
				Select a file to upload: <input class="button" type="file"
					name="file" size="50" /> <br /> <input type="submit"
					class="button" value="Upload File" onclick="form.action='UploadPhotoServlet?eid=<%=eidS%>';"/> <br>
				<%
					if (request.getAttribute("wrongType") != null) {
						String wrongType = (String) request.getAttribute("wrongType");
						//out.println(wrongType);
				%>
				<font color="red"><%=wrongType%> </font>
				<%
					}

					if (request.getAttribute("uploadSucceed") != null) {
						String uploadSucceed = (String) request
								.getAttribute("uploadSucceed");
						//out.println(wrongType);
				%>
				<font color="blue"><%=uploadSucceed%> </font>
				<%
					}
				%>
			</fieldset>
		</form>
		
		
			<fieldset>
		<legend> Photo List </legend>

		<table id="videos">
			<tr>
				<th>File Name</th>
				<th colspan="3">Action</th>
			</tr>
			<%
					int eid;
					int pid;
					String pname;
					if (request.getAttribute("photoList") != null) {
						ArrayList<Photo> photoList = (ArrayList<Photo>)request.getAttribute("photoList");
						int size = photoList.size();
						if (size > 0){
						for (int i=0; i<size; i++) { 
							eid = photoList.get(i).getEid();
							pid = photoList.get(i).getPid();
							pname = photoList.get(i).getPname();										
				%>

			<tr>
				<form method="post">
					<td> <input type="text" name="eid" value=<%= eid %> />
				     <input type="text" name="pid" value=<%= pid %> />
					<input type="text" name="pname" value=<%= pname %> /> </td>

					<td><input type="submit" value="delete" class = "button"
						onclick="alert('Are you sure to delete this photo?'); form.action='DeletePhotoServlet';"/></td>
					<td><input type="submit" value="view" class = "button fancybox fancybox.iframe" 
					href="ViewPhotoServlet?pname=<%=pname%>"/>
					</td>
				</form>
			</tr>

			<%
						}
				}
			}
			%>
		</table>
	</fieldset>
		
		
	</div>

</body>
</html>