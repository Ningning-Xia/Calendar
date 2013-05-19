<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "model.User"%>
<%@ page import="java.io.*, java.util.*, model.Event, management.RDSManagement "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />

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

<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<br><br>
<%String friendUid;
  int frienduid;
  friendUid = request.getParameter("user");%>
  
<div id = "main_div">
<% RDSManagement rds = new RDSManagement();
   frienduid = rds.getUidByName(friendUid);
   ArrayList<Event> eventList = (ArrayList<Event>)rds.getEventsByTime(frienduid);
   //out.println(eventList.size()); 
   %>

<H2><font color ="white"> <%=friendUid %> Event List: </font> </H2>
<table class ="table table-hover" id="events" >
<tr>
<th>#</th>
<th>Event Name</th>
<th>Created By</th>
<th>Start Time</th>
<th>End Time</th>
<th>
</tr>

<% String ename, startTime, endTime;
   int createBy, eid;
   int size = eventList.size();
   for(int i = 0; i < size; i++){
	   ename = eventList.get(i).getEvent_name();
	   createBy = eventList.get(i).getUid();
	   startTime = eventList.get(i).getStart_time();
	   endTime = eventList.get(i).getEnd_time();
	   eid = eventList.get(i).getEid();%>
<tr>
<td><%=i+1 %></td>
<td><%=ename %></td>
<td><%=createBy %></td>
<td><%=startTime %></td>
<td><%=endTime %></td>
<td><a class="button fancybox fancybox.iframe" rel="group" href="ShowEventDetails?key=<%=ename%>">Details</a></td>
</tr>
<%} %>
</table>




</div>

</body>
</html>