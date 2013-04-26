<%@ page import="com.devdaily.calendar.Month, java.util.*,java.io.*,java.sql.*" %>
<%-- TODO: CLEAN UP THE PAGE TAG ABOVE --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="calendarCommon.jsp" %>

<html>
<head>
  <title>V-Cal</title>
  <link rel="stylesheet" href="fancybox/source/jquery.fancybox.css?v=2.1.4" type="text/css" media="screen" />
  <link rel="StyleSheet" href="styles/calendar.css" type="text/css" />
 <!-- <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" />
 
 <script type="text/javascript" src="fancybox/lib/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript" src="fancybox/source/jquery.fancybox.pack.js?v=2.1.4"></script>
  -->
  
<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>
</head>
<br>
<br>
<body class = "general">
<jsp:include page="header.jsp"/>
   
<!--  
<table id="bar"  >
			<tr >

			<th><a class="fancybox fancybox.iframe" rel="group" href="CreateEvent.jsp">Create Event</a>
				<a href = "CreateEvent.jsp">Create Event</a></th>
				<th><a href = "listEventServlet">Manage Events</a></th>
				<th><a href = "Invitations.jsp">Invitations</a></th>

				<th><a href = "EmailTool.jsp">Email Tool</a></th>
			</tr>			
</table>
-->
<p />

<div id="main_div">
<table id = "calendar_head" cellspacing = "4px">
  <tr>
  <td id="prev_link">
      <form method="post">
        <input type="submit" class = "submit" name="PREV" value=" << ">
        <input type="hidden" name="month" value="<%=prevMonth%>">
        <input type="hidden" name="year" value="<%=prevYear%>">
      </form>
    </td>
    <td width="100%" colspan="5" class="month_year_header">
      <%=monthName%>, <%=intYear%>
    </td>
    <td id="next_link">
      <form method="post">
        <input type="submit" class = "submit" name="NEXT" value=" >> ">
        <input type="hidden" name="month" value="<%=nextMonth%>">
        <input type="hidden" name="year" value="<%=nextYear%>">
      </form>
    </td>
  </tr>
  <tr class="week_header_row">
    <th width="14%" class="th_day_cell day">Sun</th>
    <th width="14%" class="th_day_cell day">Mon</th>
    <th width="14%" class="th_day_cell day">Tue</th>
    <th width="14%" class="th_day_cell day">Wed</th>
    <th width="14%" class="th_day_cell day">Thu</th>
    <th width="15%" class="th_day_cell day">Fri</th>
    <th width="15%" class="th_day_cell day">Sat</th>
  </tr>
  </table>
  
  <div id = "celendar_body_div">
  <table id="calendar" cellspacing = "4px">
<%
{
  Month aMonth = Month.getMonth( Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString) );
  int [][] days = aMonth.getDays();
  for( int i=0; i<aMonth.getNumberOfWeeks(); i++ )
  {
    %><tr class="week_data_row"><%
    for( int j=0; j<7; j++ )
    {
      if( days[i][j] == 0 )
      {
        %><td class="empty_day_cell">&nbsp;</td><%
      }
      else
      {
        // this is "today"
        if( currentDayInt == days[i][j] && currentMonthInt == aMonth.getMonth() && currentYearInt == aMonth.getYear() )
        {
          %><td class="today_cell"> <font color = "red"><%=days[i][j]%></font></td><%
        }
        else
        {
          %><td class="day_cell"><%=days[i][j]%></td><%
        }
      } // end outer if
    } // end for
    %>
    </tr>
  <%}
}
%>
</table>
</div>
<%-- end of "calendar_div" --%>
</div>


</body>
</html>


