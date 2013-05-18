<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "model.User"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<% if(session.getAttribute("user") != null){
	User user = (User)session.getAttribute("user");
	String userName = user.getUserName();
	int uid = user.getUid();%>

<div id = "friends">
<% ArrayList<Integer> friendListId = new ArrayList<Integer>();
   ArrayList<String> friendListName = new ArrayList<String>();
   boolean isDeletedFriend;
   %>
<form action = "ListFriends" method = "post">
<input type = "hidden" name = "listfriendname" value = <%=userName %>>
<input type = "submit" name = "friends" value = "Friends" class = "button">
</form>
<% friendListId = (ArrayList<Integer>)request.getAttribute("friendsid");
   friendListName = (ArrayList<String>)request.getAttribute("friendsName");
   %>
<form action = "DeleteFriends" method = "post">
<% if(friendListName != null ){%>
<table class = "table table-hover" id = "events">
<%for(int i = 0; i < friendListName.size(); i++){%>
<tr>
<td>Name: </td>
<td><%= friendListName.get(i) %></td>
<td><input type = "checkbox" name = "friend" value = <%=friendListName.get(i) %>></td>
</tr>
<%}%>
</table>
<input type = "hidden" name = "whodeletefriend" value = <%=userName %>>
<input type = "submit" class = "button" value = "Delete"><%}%>
</form>
</div>





<div id = "search">
<H2><font color ="white"> Search Your Friends: </font> </H2>
<% ArrayList<ArrayList<String>> userinfo = new ArrayList<ArrayList<String>>(); %>
<form action = "searchFriends" method = "post">
<input type = "text" name = "username" size = "35">
<input type = "submit" name = "search" value = "search" class = "button">
</form>


<% userinfo = (ArrayList<ArrayList<String>>)request.getAttribute("userinfo");%>
<% if(userinfo != null && userinfo.size() != 0){ %>
<form action = "addFriend" method = "post">
<table>
<tr>
<td>Name:</td>
<td><%= userinfo.get(0).get(0) %></td>
<td><input type = "checkbox" name = "friend" value = <%=userinfo.get(0).get(0) %>></td>
</tr>
<tr>
<td>Email:</td>
<td><h7><%= userinfo.get(0).get(1) %></td>
</tr>
</table>
<input type = "hidden" name = "whofindfriend" value = <%=userName %>>
<input type = "submit" value = "Add Friend" class = "button">
</form>
<% } %>
</div>








<div id = "request">
<% ArrayList<Integer> requestsId = new ArrayList<Integer>();
   ArrayList<String> requestsName = new ArrayList<String>();
   %>
<form action = "friendRequests" method = "post">
<input type = "hidden" name = "whofriendrequest" value = <%=userName %>>
<input type = "submit" value = "Friend Request" class = "button">
</form>
<% requestsId = (ArrayList<Integer>)request.getAttribute("requestsId");
   requestsName = (ArrayList<String>)request.getAttribute("reuqestsName");
   %>
<form action = "AcceptRequest" method = "post">
<% if(requestsName != null && requestsName.size() != 0){%>
<table class = "table table-hover" id = "events">
<%for(int i = 0; i < requestsName.size(); i++){%>
<tr>
<td>Name: </td>
<td><%= requestsName.get(i) %></td>
<td><input type = "checkbox" name = "friend" value = <%=requestsName.get(i) %>></td>
</tr>
<%}%>
</table>
<input type = "hidden" name = "whoacceptfriend" value = <%=userName %>>
<input type = "submit" value = "Accept" class = "button"><%}%>
</form>
</div>
<%} %>
</body>
</html>