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
<H2><font color ="white"> Your Event List: </font> </H2>
<table class ="table table-hover" id="events" >
			<tr>
				<th>#</th>
				<th>Event Name</th>
				<th>Created By</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th colspan = "5">Action</th>
			</tr>
			<%
				String ename, startTime, endTime;	
				int createBy, eid;
					ArrayList<Event> eventList = (ArrayList<Event>)request.getAttribute("eventList");
					int size = eventList.size();
					for (int i=0; i<size; i++) { 
						ename = eventList.get(i).getEvent_name();
						createBy = eventList.get(i).getUid();
						startTime = eventList.get(i).getStart_time();
						endTime = eventList.get(i).getEnd_time();
						eid = eventList.get(i).getEid();
			%>

			<tr>
				<form action="ShowEventDetails" method="post">
					<input type="hidden" name="key" value=<%=eid%> />
					<input type="text" name="eid" value=<%=eid%> />
					<td><%=i+1 %></td>
					<td><%=ename%></td>
					<td><%=createBy %></td>
					<td><%=startTime%></td>
					<td><%=endTime%></td>
					<td><a value="Delete" href = "DeleteEventServlet?key=<%=eid%>">Delete</a></td> 
					<td><a value="Edit" href = "ShowEventDetails?action=Edit&key=<%=ename%>">Edit</a></td>
					<td><a value="Details" class="fancybox fancybox.iframe" rel="group" href="ShowEventDetails?key=<%=ename%>">Details</a></td>
					<td> <a  value = "Video" href = "ListVideoServlet?eid=<%=eid%>">Video</a></td>
					<td> <a  value = "Photo" href = "ListPhotoServlet?eid=<%=eid%>">Photo</a></td>
				</form>
			</tr>

			<%	
				}
			%>
			
		</table>
		</div>
</body>
</html>