<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "model.User"%>
<%@ page import="java.io.*, java.util.*, model.Event, management.RDSManagement "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<%String friendUid;
  int frienduid;
  friendUid = request.getParameter("user");%>
  
<div id = "main_div">

<% RDSManagement rds = new RDSManagement();
   frienduid = rds.getUidByName(friendUid);
   ArrayList<Event> eventList = (ArrayList<Event>)rds.getEventsByTime(frienduid); 
   if(eventList.size() != 0)
	   out.println(eventList.get(0).getDescription());
   %>

<H2><font color ="white"> <%=friendUid %> Event List: </font> </H2>
<table class ="table table-hover" id="events" >
</div>

</body>
</html>