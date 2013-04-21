<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="calendarCommon.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" src="PopupCalendar.js" ></script>
 <link rel="StyleSheet" href="./vcal.css" type="text/css" />
 
 <script language="javascript">
 function validate() {
	 var sdate = document.getElementById("start-date").value.split("-");
	 var edate = document.getElementById("end-date").value.split("-");
	 var stime = document.getElementById("start-time").value;
	 var etime = document.getElementById("end-time").value;

	 var date1 = new Date(sdate[2],sdate[0],sdate[1],stime);
	 var date2 = new Date(edate[2],edate[0],edate[1],etime);
	 
	 if (date2 < date1) {
		 alert("Sorry, you can't create an event that ends before it starts.");
		 document.getElementById("end-date").style.backgroundColor="#fff0f0";
		 document.getElementById("end-time").style.backgroundColor="#fff0f0";
		 return false;
	 }
	 
	 return true;
	 
 }
 
 
 </script>
<title>Create Event</title>
</head>
<body>
<h1> Create a new event</h1>
<script >

var oCalendarEn=new PopupCalendar("oCalendarEn");	
oCalendarEn.Init();

</script>
	<form method="post" action="CreateEventServlet" onsubmit = "return validate();">
		<table name = "create-event" >
		<tr>
			<td>Event Name:</td>
			<td><input type="text" name="ename" id="ename" value="Untitled event" 
			onfocus="if(this.value == 'Untitled event') {this.value=''}" 
			onblur="if(this.value == ''){this.value ='Untitled event'}"/></td>
		</tr>
		<tr>
			<td>Start Time:  </td>
			<td><input readonly type="text" name="start-date" id = "start-date" value="<%=currentMonthInt+1%>-<%=currentDayInt%>-<%=currentYearInt%>"
			onClick="getDateString(this,oCalendarEn)"/>
			<select name="start-time" id = "start-time">
			<% 
			for (int i = 0; i < 24; i++) {
				if (i == currentHourInt) {
					%>
					<option selected value="<%=i%>"><%=i%>:00</option>
				<%;}
				else {
				%>
					<option value="<%=i%>"> <%=i%>:00 </option>
			<%;}
			}	
			%>
			</select> </td>
		</tr>
		<tr>
			<td>End Time: </td>
			<td><input readonly type="text" name="end-date"  id = "end-date" value="<%=currentMonthInt+1%>-<%=currentDayInt%>-<%=currentYearInt%>" 
			onClick="getDateString(this,oCalendarEn)"/> 
			<select name="end-time" id= "end-time">
			<%			
			for (int i = 0; i < 24; i++) {
				if (i == currentHourInt) {
					%>
					<option selected value="<%=i%>"><%=i%>:00</option>
				<%;}
				else {
				%>
					<option value="<%=i%>"> <%=i%>:00 </option>
			<%;}
			}	
			%>
			</select>
			</td>
		</tr>
		
		<tr><td>Location:</td> 
		<td><input type="text" name = "location" value="" /></td></tr>
		
		<tr><td>Upload Picture:</td>
		<td><input type="file" name="picture" size="50" /></td></tr>
		
		<tr><td>Upload Video:</td>
		<td><input type="file" name="video" size="50" /></td></tr>
		
		<tr><td>Invite Friend:</td>
		<td><input type = "text" name = "invitelist" value="" /></td></tr>
		
		<tr><td>Description:</td>
		<td><textarea name = "description"> </textarea></td></tr>
		
		<tr><td>Privacy:</td>
		<td>
		<input type="radio" name = "privacy" value="private"> Private 
		<input type="radio" name = "privacy" value="public" checked> Public 
		<input type="radio" name = "privacy" value="invited"> Invited Only 
		</td></tr>
			
		
		</table>
		<p />
		<input type = "submit" value = "Submit" /> 
		<input type = "reset" value = "Reset" />
		<input type = "button" value = "Discard" />
			
	</form>

</body>
</html>