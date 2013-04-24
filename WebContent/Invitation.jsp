<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="java.util.*,model.Invitation" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>

<table name = "invitationTable" >
<% if (request.getAttribute("inviteList") != null) {
	ArrayList<Invitation> inviteList = (ArrayList<Invitation>)request.getAttribute("inviteList");
	if (inviteList != null && inviteList.size() > 0) {
		for (int i = 0; i < inviteList.size(); i++) {
			Invitation invite = inviteList.get(i);
			int eid = invite.getEid();
			String ename = invite.getEname();
			int uid = invite.getUid();
			String uname = invite.getUname();
			int action = invite.getAction();
			String actionStr;
			
			if (action == 0) {
				actionStr = "Default";
			} else if (action == 1) {
				actionStr = "Accept";
			} else if (action == 2) {
				actionStr = "Maybe";
			} else if (action == 3) {
				actionStr = "Decline";
			} else {
				actionStr = "error";
			}
			
			%>
			<tr>
			<td>eid <input type = text name = "eid" value = <%= eid %> /> <td>
			<td>ename <input type = text name = "eid" value = <%= ename %> /> <td>
			<td>uid <input type = text name = "eid" value = <%= uid %> /> <td>
			<td>uname <input type = text name = "eid" value = <%= uname %> /> <td>
			<td>action <input type = text name = "eid" value = <%= action %> /> <td>
			<td>action type <input type = text name = "eid" value = <%= actionStr %> /> <td>
			<td> <input type = button name = "editButton" value = "edit" /> <td> 
			</td>
			<%
		}
	}
}
%>

</table>
</body>
</html>