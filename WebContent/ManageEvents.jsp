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

<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>
</head>
<body>
<a class="fancybox" rel="group" href="images/1_b.jpg"><img src="images/1_s.jpg" alt="" /></a>
<table id="events">
			<tr>
				<th>Event Name</th>
				<th>Created By</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Location</th>
				<th>Description</th>
				<th>Video</th>
				<th>Picture</th>
				<th>
					
					
					</th>
			</tr>
			<%
				String ename, startTime, endTime, location, pic, video, description;		
					ArrayList<Event> eventList = (ArrayList<Event>)request.getAttribute("eventList");
					int size = eventList.size();
					for (int i=0; i<size; i++) { 
						ename = eventList.get(i).getEvent_name();
						startTime = eventList.get(i).getStart_time();
						endTime = eventList.get(i).getEnd_time();
			%>

			<tr>
				<form action="ListServlet" method="post">
					<input type="hidden" name="key" value=<%=ename%> />
					<td><label><input type="checkbox" name="event" id = "event" value="<%=ename%>" /><%=ename%></label></td>
					<td><%=startTime%></td>
					<td><%=endTime%></td>
					<td><input type="submit" value="Delete"
						onclick="form.action='DeleteServlet';"></td>
					<td><input type="submit" value="Details"
						onclick="form.action='ViewServlet';"></td>
					
				</form>
			</tr>

			<%
					
				}
			%>
			
		</table>
</body>
</html>