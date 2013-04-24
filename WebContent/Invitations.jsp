<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>
<link rel="StyleSheet" href="Event.css" type="text/css" media="screen" />
</head>
<body id = "Event_Page">

<div id = "EventT">
Event From: <% //should have a parameter to receive who sends the event... %>
</div>

<% //need to get the current user id. %>
<form action = "Event" method = "Post">
<input type = "hidden" name = "uid" value = "1">
<input type = "submit" value = "Event Details" name = "Event">
<br>
</form>

<%
 ArrayList<ArrayList<String>> einfo = new ArrayList<ArrayList<String>>();
 if(request.getAttribute("einfo") != null)
	 einfo = (ArrayList<ArrayList<String>>)request.getAttribute("einfo");
%>
<% if(einfo.size() != 0){
	for(ArrayList<String> event_order:einfo){%>
<textarea name = "einfo" id = "einfo" style = "width: 300px; height: 150px;">
<% for(String event_desc: event_order){ %>
<%= event_desc %>
<%}}} %>
</textarea>

<form action = "EventAction" method = "post">
<input type = "hidden" name = "eventid" value = "1"> <% //should be the same as uid %>
<input type = "hidden" name = "friendid" value = "2"> <% //should be the current login ind %>
<input type = "submit" value = "Join" name = "action">
<input type = "submit" value = "Refuse" name = "action">
<input type = "submit" value = "Maybe" name = "action">
</form>
</body>
</html>