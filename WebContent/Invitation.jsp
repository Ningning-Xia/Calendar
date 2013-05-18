<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="java.util.*,model.Invitation"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Invitation</title>
<link href="../assets/css/bootstrap.css" rel="stylesheet">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="../assets/ico/favicon.png">
</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<br>
	<div id="main_div">
		<H2>
			<font color="white"> Your Invitation List: </font>
		</H2>
		<table class="table table-hover" id="events">
			<tr>
				<th>#</th>
				<th>Event Name</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Status</th>
				<th>Action</th>
			</tr>


			<% if (request.getAttribute("inviteList") != null) {
	ArrayList<Invitation> inviteList = (ArrayList<Invitation>)request.getAttribute("inviteList");
	if (inviteList != null && inviteList.size() > 0) {
		for (int i = 0; i < inviteList.size(); i++) {
			Invitation invite = inviteList.get(i);
			int eid = invite.getEid();
			String ename = invite.getEname();
			String startTime = invite.getStartTime();
			String endTime = invite.getEndTime();
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
			<form name="invitationForm" onsubmit="return check()" method="post" >
			<tr>
				<td name= "eid"><%=eid%></td>
				<td name = "ename"><%=ename%></td>
				<input type=hidden name="eid" value="<%=eid%>" />
				<input type=hidden name="uid" value="<%=uid%>" />
				<input type=hidden name="uname" value="<%=uname%>" />
				<td><%=startTime%></td>
				<td><%=endTime%></td>

				<td><select name="status">
						<option value=0 <% if(action==0) out.write("selected"); %>>Default</option>
						<option value=1 <% if(action==1) out.write("selected"); %>>Accept</option>
						<option value=2 <% if(action==2) out.write("selected"); %>>Maybe</option>
						<option value=3 <% if(action==3) out.write("selected"); %>>Decline</option>
				</select></td>
				<td><input class="button" type="submit" name="editButton"
					value="update"
					onclick="alert('Are you sure to update this status?'); form.action='UpdateInvitationServlet';" />
				</td>
			</tr>
			</form>
			<%
		}
	}
}
%>

		</table>
	</div>
</body>
</html>