<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="calendarCommon.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" src="PopupCalendar.js" ></script>
 <link rel="StyleSheet" href="./styles/create_event.css" type="text/css" />
 
 <script language="javascript">
 function validate() {	
	 var flag = checkDate() && checkEmail(); 
	 return flag; 
 }
 
 function checkDate(){
	 var sdate = document.getElementById("start-date").value.split("-");
	 var edate = document.getElementById("end-date").value.split("-");
	 var stime = document.getElementById("start-time").value;
	 var etime = document.getElementById("end-time").value;	 	 
	 var date1 = new Date(sdate[2],sdate[0],sdate[1],stime);
	 var date2 = new Date(edate[2],edate[0],edate[1],etime);
	 
	 if (date2 < date1) {
		
		 document.getElementById("end-date").style.backgroundColor="#fff0f0";
		 document.getElementById("end-time").style.backgroundColor="#fff0f0";
		 alert("Sorry, you can't create an event that ends before it starts.");
		 return false;
	 }
	 return true;
 }
 
 function checkEmail(){
	 	var email = document.getElementById("invitelist").value.split(";");
        var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        var i;
        if (! email.equals("")) {
        	for (i = 0; i < email.length; i++){
        		if(!re.test(email[i])) {       		
        			document.getElementById("invitelist").style.backgroundColor="#fff0f0";
        			alert("Your input " + email[i]+" is not a valid email address.");
        			return false;
        		}        	
        	}	
        }
        return true;      
 }
</script>
<title>Create Event</title>
</head>

<body>

<script >
var oCalendarEn=new PopupCalendar("oCalendarEn");	
oCalendarEn.Init();
</script>

	<form method="post" action="addEventServlet" onsubmit = "return validate();">
		<br>
		<br>
		<table id = "create-event" >
		<tr><td></td><td ><h1> Create a new event</h1></td></tr>
		<tr>
			<th>Event Name:</th>
			<td><input type="text" name="ename" id="ename" value="Untitled event" 
			onfocus="if(this.value == 'Untitled event') {this.value=''}" 
			onblur="if(this.value == ''){this.value ='Untitled event'}"/></td>
		</tr>
		<tr>
			<th>Start Time:  </th>
			<td><input readonly type="text" name="start-date" id = "start-date" 
			value="<%=currentMonthInt+1%>-<%=currentDayInt%>-<%=currentYearInt%>"
			onClick="getDateString(this,oCalendarEn)"/>
			<select name="start-time" id = "start-time">
			<% 
			for (int i = 0; i < 24; i++) {
				if (i == currentHourInt) {
					%>
					<option selected value="<%=i%>"><%=i%>:00</option>
				<%} else {	%>
					<option value="<%=i%>"> <%=i%>:00 </option>
			<%}
			}%>
			</select> </td>
		</tr>
		<tr>
			<th>End Time: </th>
			<td><input readonly type="text" name="end-date"  id = "end-date" 
			value="<%=currentMonthInt+1%>-<%=currentDayInt%>-<%=currentYearInt%>" 
			onClick="getDateString(this,oCalendarEn)"/> 
			<select name="end-time" id= "end-time">
			<%			
			for (int i = 0; i < 24; i++) {
				if (i == currentHourInt) {
					%>
					<option selected value="<%=i%>"><%=i%>:00</option>
				<%} else {	%>
					<option value="<%=i%>"> <%=i%>:00 </option>
			<%}
			}	%>
			</select>
			</td>
		</tr>
		
		<tr><th>Location:</th> 
		<td><input type="text" name = "location" value="" /></td></tr>
		
		<tr><th>Upload Picture:</th>
		<td><input type="file" name="picture" size="50" /></td></tr>
		
		<tr><th>Upload Video:</th>
		<td><input type="file" name="video" size="50" /></td></tr>
		
		<tr><th>Invite Friend:</th>
		<td>(Please input the email addresses, separate by ';') <br>
		<textarea name = "invitelist" id = "invitelist"></textarea></td></tr>
		
		<tr><th>Description:</th>
		<td>
		<textarea name = "description"> </textarea></td></tr>
		
		<tr><th>Privacy:</th>
		<td>
		<input type="radio" name = "privacy" value="private"> Private 
		<input type="radio" name = "privacy" value="public" checked> Public 
		<input type="radio" name = "privacy" value="invited"> Invited Only 
		</td></tr>
					
	
		
		<p />
		<tr><td></td>
		<td> <p/>
		<input type = "submit" value = "Submit" /> 
		<input type = "reset" value = "Reset" />
		<input type = "button" value = "Discard" onclick="history.go(-1)"/></td></tr>
			</table>

	</form>

</body>
</html>