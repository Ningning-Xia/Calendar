<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*, model.Event "%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Your Events</title>

<script type="text/javascript" src="fancybox/lib/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<link rel="stylesheet" href="fancybox/source/jquery.fancybox.css?v=2.1.4" type="text/css" media="screen" />
<script type="text/javascript" src="fancybox/source/jquery.fancybox.pack.js?v=2.1.4"></script>
  <!--  <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" /> -->
  <link rel="StyleSheet" href="styles/calendar.css" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>
</head>
<body>
<jsp:include page="header.jsp"/>
<br>
<br>
<div id="main_div">

<form id="form1" action="UploadVideo" method="post"
		enctype="multipart/form-data">
		<% 
		if (request.getAttribute("eid")!=null){
				
				%>
				<input type="text" name="eid" value="<%=request.getAttribute("eid") %>" />
				<%} %>
		<fieldset>
			<legend>Video Upload:</legend>
			Select a file to upload: <input class = "button" type="file" name="file" size="50" />
			<br /> <input type="submit" class="button" value="Upload File" /> <br>
			<% 
			if (request.getAttribute("wrongType")!=null){
				String wrongType =(String)request.getAttribute("wrongType");
				//out.println(wrongType); 
				%>
			<font color="red"><%= wrongType %> </font>
			<% 
			}
	    	
			if (request.getAttribute("uploadSucceed")!=null){
				String uploadSucceed =(String)request.getAttribute("uploadSucceed");
				//out.println(wrongType); 
				%>
			<font color="blue"><%= uploadSucceed %> </font>
			<% 
			}
		%>
		</fieldset>
	</form>

<H2><font color ="white"> Your Video List: </font> </H2>
<%if (request.getAttribute("video") != null) {
	
	String videoName= (String)request.getAttribute("video");
	if(!videoName.equals("null")){
	%>
	<form  action="ViewVideoServlet" method="post">
	<table>
	<input type="hidden" name="key" value=<%= videoName %> />
	<tr> <td> <h4><%=videoName%></h4>  </td> <td> <input type="submit" class="button" name="view" value="view" /></td>
	</table>
	</form>
<%} 
}%>


	<%
		String videoname, svideo, dvideo, s, d, src, fsrc = null;
		if ((videoname = (String) request.getAttribute("view")) != null) {
			s = (String) request.getAttribute("stream");
			d = (String) request.getAttribute("download");
			svideo = s + videoname+"\"";		
			dvideo = d + videoname+"\"";
			s = s + "\"";			
	%>
	<fieldset>
		<legend> Watch Video <%=videoname%> </legend>
	Stream: <%=svideo%> <br />
	Download: <%=dvideo%> <br />
	<div id="container"></div>
	<script type='text/javascript' src= "jwplayer/jwplayer.js"></script>
	<script type='text/javascript'>
			jwplayer("container").setup({
								file : <%= svideo%>,
								provider : "rtmp",
								streamer : <%= s %>,
								width : "480",
								height : "360",
								modes : [{
											type : "flash",
											src : "jwplayer/jwplayer.flash.swf"
										},{
											type : "html5",
											config : {
												file : <%= dvideo%>,
												provider : "video"
										}}]
			});

	</script>
	</fieldset>
	<%
		}
	%>
	
</body>
</html>