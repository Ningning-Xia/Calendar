<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.util.ArrayList, model.Event " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Event Details</title>
<link rel="StyleSheet" href="styles/calendar.css" type="text/css" />
</head>
<body class = "create_event">
<div id="main_div">
<%
	Event event = (Event) request.getAttribute("event");
	String ename, createdBy, sTime, eTime, location, picture, video, description;

	int privacy;
		
	ename = event.getEvent_name();
	sTime = event.getStart_time();
	eTime = event.getEnd_time();
	location = event.getLocation();
	picture = event.getPic_URL();
	video = event.getVideo_URL();
	description  = event.getDescription();
	privacy = event.getPrivacy();
	ArrayList<ArrayList<String>> userList = event.getUsers();
%>
<br>
<br>
<br>
<table id = "create-event" >
		<tr><td></td><td ><h1> Event Details</h1></td></tr>
		<tr>
			<th>Event Name:</th>
			<td><%= ename %></td>
		</tr>
		<tr>
			<th>Start Time:  </th>
			<td><%=sTime %>
			</td>
		</tr>
		<tr>
			<th>End Time: </th>
			<td><%=eTime %>	</td>
		</tr>
		<tr><th>Location:</th> 
		<td><%= location %></td></tr>
		<!--  
		<tr><th>Picture:</th>
		<td><%=picture %></td></tr>
		
		<tr><th>Video:</th>
		<td><%= video %></td></tr>
		-->
		<tr><th>Participants:</th>
		<td>
		
		<%
		ArrayList<String> users = new ArrayList<String>();

		for (int i = 0; i < 4; i++){
			String action = null;
			users = userList.get(i);
			if (users.size() != 0) {
				switch(i){
				case 0: action = "Default"; break;
				case 1: action = "Accepted";break;
				case 2: action = "Maybe";break;
				case 3: action = "Declined";break;
				}
			%>		
			<%= action %>: <%
			for (String s : users){
				
				if (s != null)%>
				<%= s %>
			<% }
			%>
			<br>
			<%}
		}
		%>
		</td></tr>
		
		<tr><th>Description:</th>
		<td><%=description %>		</td></tr>
		
		<tr><th>Privacy:</th>
		<td><%
		String pri = "";
		switch(privacy){
		case 0: pri = "Private";
		case 1: pri = "Public";
		case 2: pri = "Invited Only";
		}
		%>
		<%= pri %>
		</td></tr>
		<tr><td></td>
		<td> <p/>
		
			</table>
			</div>
</body>
</html>