<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "model.User"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
<title>Email Tool</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<% if(session.getAttribute("user") != null){
	User user = (User)session.getAttribute("user");
	String userName = user.getUserName();
	String userEmailAddress = user.getEmail();%>

<% ArrayList<String> EventNames = new ArrayList<String>(); %>
<form action = "ListEvent" method = "post">
<input type = "hidden" name = "uid" value = "1"> 
<input type = "submit" value = "List Event" name = "1">
</form>
<% EventNames = (ArrayList<String>)request.getAttribute("enames");
%>
<form action = "EventDetails" method = "post">
<% if(EventNames != null){ 
int i = 0;
int size = EventNames.size();%>

<select id = "list" name = "List" onchange = changeType()>
<% while(i < size){%>
<option><%=EventNames.get(i) %>
<%i++;}%> 
<input type = "submit" value = "Details">
<%}%>
</select>
</form>

<div id = "email">
<H2><font color ="white"> Send Emails: </font> </H2>
<%out.println(userEmailAddress); %>
<form action = "SendEmails" method = "post">
<input type = "hidden" name = "fromUserEmail" value = <%=userEmailAddress %>>
<table>
<tr>
<td><input type = "button" value = "Send Email to" class = "button"></td>
<td><input type = "text" name = "email"></td>
</tr>

<tr>
<td><input type = "button" value = "Subject" class = "button"></td>
<td><textarea name = "subject" id = "subject" style = "width: 220px; height: 15px;">
</textarea></td>
</tr>
</table>

<textarea name = "body" id = "body" style = "width: 600px; height: 250px;">
</textarea>

<input type = "submit" value = "Send" class = "button">
</form>
</div>


<%} %>
</body>
</html>