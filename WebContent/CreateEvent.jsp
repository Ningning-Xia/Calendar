<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.Event, model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="calendarCommon.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all"
	href="./styles/jsDatePick_ltr.min.css" />
<!--  <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" /> -->
<script type="text/javascript" src="jsDatePick.min.1.3.js"></script>
<script language="javascript">
	window.onload = function() {
		calendar1 = new JsDatePick({
			useMode : 2,
			target : "start-date",
			cellColorScheme : "aqua",
		});
		calendar2 = new JsDatePick({
			useMode : 2,
			target : "end-date",
			cellColorScheme : "aqua",
		});
	};

	function validate() {
		if (!checkUid()) {
			return false;
		}
		if (!checkEmail()) {
			return false;
		}
		if (!checkDate()) {
			return false;
		}
	}

	function checkDate() {
		var sdate = document.getElementById("start-date").value.split("-");
		var edate = document.getElementById("end-date").value.split("-");
		var stime = document.getElementById("start-time").value;
		var etime = document.getElementById("end-time").value;
		var date1 = new Date(sdate[2], sdate[0], sdate[1], stime);
		var date2 = new Date(edate[2], edate[0], edate[1], etime);

		if (date2 < date1) {
			document.getElementById("end-date").style.backgroundColor = "lightblue";
			document.getElementById("end-time").style.backgroundColor = "lightblue";
			alert("Sorry, you can't create an event that ends before it starts.");
			return false;
		}
		return true;
	}

	function checkEmail() {
		var email = document.getElementById("invitelist").value.split(";");
		var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var i;
		if (!email.equals("")) {
			for (i = 0; i < email.length; i++) {
				if (!re.test(email[i])) {
					document.getElementById("invitelist").style.backgroundColor = "lightblue";
					alert("Your input " + email[i]
							+ " is not a valid email address.");
					return false;
				}
			}
		}
		return true;
	}

	function checkUid() {
		var uid = document.event_form.uid.value;
		if (uid == -1) {
			alert("Please login first");
			return false;
		}

		return true;
	}
</script>
<title>Create Event</title>
</head>

<body class="create_event">
	<jsp:include page="header.jsp" />
	<script>
		var oCalendarEn = new PopupCalendar("oCalendarEn");
		oCalendarEn.Init();
	</script>

	<form method="post" name="event_form" action="addEventServlet"
		onsubmit="return validate()">
		<br> <br> <br>
		<%
			int uid = -1;
			if (session.getAttribute("user") != null) {
				User user = (User) session.getAttribute("user");
				String userName = user.getUserName();
				uid = user.getUid();
				String email = user.getEmail();
				String password = user.getPassword();
			}

			String action = request.getParameter("action");
			String option = "Create";
			String ename = "Untitled event";
			String startTime = "" + (currentMonthInt + 1) + "-" + currentDayInt
					+ "-" + currentYearInt;
			String endTime = "" + (currentMonthInt + 1) + "-" + currentDayInt
					+ "-" + currentYearInt;
			int sHour = currentHourInt;
			int eHour = currentHourInt;
			String[] strs = new String[2];
			String invite = "";
			String location = "";
			String pic_URL = "";
			String video_URL = "";
			String description = "";
			int privacy;
			if (action != null) {
				option = "Edit";
				Event event = (Event) request.getAttribute("event");
				ename = event.getEvent_name();

				strs = event.getStart_time().split(" ");
				startTime = strs[0];
				sHour = Integer.parseInt(strs[1].split(":")[0]);

				strs = event.getEnd_time().split(" ");
				endTime = strs[0];
				eHour = Integer.parseInt(strs[1].split(":")[0]);

				location = event.getLocation();
				pic_URL = event.getPic_URL();
				video_URL = event.getVideo_URL();
				description = event.getDescription();
				privacy = event.getPrivacy();
				invite = event.getEmailList();
			}
		%>
		<table id="create-event">
			<tr>
				<td></td>
				<td><h1>
						<%=option%>
						event
					</h1></td>
			</tr>
			<tr>
			<tr>
				<td><input type="hidden" name="uid" value="<%=uid%>" /></td>
			</tr>
			<th>Event Name:</th>
			<td><input type="text" name="ename" id="ename"
				value="<%=ename%>"
				onfocus="if(this.value == 'Untitled event') {this.value=''}"
				onblur="if(this.value == ''){this.value ='<%=ename%>'}" /></td>
			</tr>
			<tr>
				<th>Start Time:</th>
				<td><input readonly type="text" name="start-date"
					id="start-date" value="<%=startTime%>" /> <select
					name="start-time" id="start-time">
						<%
							for (int i = 0; i < 24; i++) {
								if (i == sHour) {
						%>
						<option selected value="<%=i%>"><%=i%>:00
						</option>
						<%
							} else {
						%>
						<option value="<%=i%>">
							<%=i%>:00
						</option>
						<%
							}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<th>End Time:</th>
				<td><input readonly type="text" name="end-date" id="end-date"
					value="<%=endTime%>" /> <select name="end-time" id="end-time">
						<%
							for (int i = 0; i < 24; i++) {
								if (i == eHour) {
						%>
						<option selected value="<%=i%>"><%=i%>:00
						</option>
						<%
							} else {
						%>
						<option value="<%=i%>">
							<%=i%>:00
						</option>
						<%
							}
							}
						%>
				</select></td>
			</tr>

			<tr>
				<th>Location:</th>
				<td><input type="text" name="location" value="<%=location%>" /></td>
			</tr>


			<tr>
				<th>Picture:</th>
				<td><%=pic_URL%> <%
 	if (action != null)
 %><br> <%
 	;
 %> <input
					type="file" name="picture" size="50" /></td>
			</tr>

			<tr>
				<th>Video:</th>
				<td><%=video_URL%>
					<%
						if (action != null)
					%><br> <%
 	;
 %> <input type="file"
					name="video" size="50" /></td>
			</tr>

			<tr>
				<th>Invite Friend:</th>
				<td>(Input email addresses, separated by ;)<br> <textarea
						name="invitelist" class="textarea"><%=invite%>
		</textarea></td>
			</tr>

			<tr>
				<th>Description:</th>
				<td><textarea class="textarea"> </textarea></td>
			</tr>

			<tr>
				<th>Privacy:</th>
				<td><input type="radio" name="privacy" value="1">
					Private <input type="radio" name="privacy" value="2" checked>
					Public <input type="radio" name="privacy" value="3">
					Invited Only</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<p /> <input type="submit" class="button" value="Submit" /> <input
					type="reset" class="button" value="Reset" /> <input type="button"
					class="button" value="Discard" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>

	</form>

</body>
</html>