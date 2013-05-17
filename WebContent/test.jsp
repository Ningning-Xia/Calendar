<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.User"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> Hello!</h1>
<% if (session.getAttribute("user")!=null) {
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		int uid = user.getUid();
		%>
	<h1> <%= userName %> </h1>
		<%
		
	}%>

</body>
</html>