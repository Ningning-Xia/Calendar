<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/addFriends.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<div id = "friends">

</div>


<div id = "search">
<% ArrayList<ArrayList<String>> userinfo = new ArrayList<ArrayList<String>>(); %>
<form action = "searchFriends" method = "post">
<input type = "text" name = "username">
<input type = "submit" name = "search" value = "search">
</form>
<% userinfo = (ArrayList<ArrayList<String>>)request.getAttribute("userinfo");%>


<% if(userinfo != null){ %>
Name:<%= userinfo.get(0).get(0) %>
<br>
Email:<%= userinfo.get(0).get(1) %>

<form action = "addFriend" method = "post">
<input type = "checkbox" name = "friend" value = <%=userinfo.get(0).get(0) %>>

<input type = "submit" value = "Add Friend">
</form>
<% } %>
</div>

<div id = "requests">

</div>

</body>
</html>