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
<% ArrayList<Integer> friendListId = new ArrayList<Integer>();
   ArrayList<String> friendListName = new ArrayList<String>();
   boolean isDeletedFriend;
   %>
<form action = "ListFriends" method = "post">
<input type = "submit" name = "friends" value = "Friends">
</form>
<% friendListId = (ArrayList<Integer>)request.getAttribute("friendsid");
   friendListName = (ArrayList<String>)request.getAttribute("friendsName");
  /* if(friendListName != null)
	   out.println(friendListName.get(0));*/
   %>
<form action = "DeleteFriends" method = "post">
<% if(friendListName != null ){
for(int i = 0; i < friendListName.size(); i++){%>
Name: <%= friendListName.get(i) %>
<input type = "checkbox" name = "friend" value = <%=friendListName.get(i) %>>
<br>
<%}%>
<input type = "submit" value = "Delete"><%}%>
</form>
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
<% ArrayList<Integer> requestsId = new ArrayList<Integer>();
   ArrayList<String> requestsName = new ArrayList<String>();
   %>
<form action = "friendRequests" method = "post">
<input type = "submit" value = "Friend Request">
</form>
<% requestsId = (ArrayList<Integer>)request.getAttribute("requestsId");
   requestsName = (ArrayList<String>)request.getAttribute("reuqestsName");
   %>
<form action = "AcceptRequest" method = "post">
<% if(requestsName != null && requestsName.size() != 0){
for(int i = 0; i < requestsName.size(); i++){%>
Name: <%= requestsName.get(i) %>
<input type = "checkbox" name = "friend" value = <%=requestsName.get(i) %>>
<br>
<%}%>
<input type = "submit" value = "Accept"><%}%>
</form>
</div>

</body>
</html>