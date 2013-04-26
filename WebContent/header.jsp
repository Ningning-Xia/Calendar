 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
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
               <% if(path.equals("/Invitations.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="Invitations.jsp">Invitations</a>
              </li>
              <% if(path.equals("/EmailTool.jsp")) {%>
              <li class="active">
              <% } else {%>
               <li class=""><% }%>
                <a href="EmailTool.jsp">Email Tool</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

