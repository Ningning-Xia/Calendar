 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.User"%>
 
 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="calendar.jsp">V-Cal</a>
          <div class="nav-collapse collapse">  
         
            <ul class="nav">
            <% 
            String path =request.getServletPath().toString();
           
            if(path.equals("/calendar.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="calendar.jsp">Home</a>
              </li>

              
              
              <% if (session.getAttribute("user")!=null) {
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		int uid = user.getUid();
		%>             
			 <% if(path.equals("/CreateEvent.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="CreateEvent.jsp">Create Event</a>
              </li>
              <% if(path.equals("/ManageEvents.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="listEventServlet">Manage Event</a>
              </li>
               <% if(path.equals("/Invitation.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="listInvitationServlet">Invitations</a>
              </li>
              <% if(path.equals("/EmailTool.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="EmailTool.jsp">Email Tool</a>
              </li>
	     <% if(path.equals("/Userhome.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="UserHome.jsp">Welcome! <%= user.getUserName() %></a>
              </li>
              
          <% if(path.equals("/logout.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="LogoutServlet">Logout</a>
              </li>
		<%
		
		 } else {%>
	          
              <% if(path.equals("/Login.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="Login.jsp">Login</a>
              </li>
              
              
              <%} %>            
            </ul>
          </div>
        </div>
      </div>
    </div>

