<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Tool</title>
</head>
<body>
This is Email Tool.
<% ArrayList<String> EventNames = new ArrayList<String>(); %>

<form action = "ListEvent" method = "post">
<input type = "hidden" name = "uid" value = "1"> 
<input type = "submit" value = "List Event" name = "1">
</form>
<% EventNames = (ArrayList<String>)request.getAttribute("enames");
   //if(EventNames != null){
	 //  for(String s: EventNames)
	   //  out.println(s);
   ///}
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

<br><br>
<form action = "SendEmails" method = "post">
Send Emails to: <input type = "text" name = "email">
<br>
subject:  <textarea name = "subject" id = "subject" style = "width: 220px; height: 15px;">
</textarea>
<br>
<textarea name = "body" id = "body" style = "width: 300px; height: 250px;">
</textarea>
<br>
<input type = "submit" value = "Send">
</form>




</body>
</html>