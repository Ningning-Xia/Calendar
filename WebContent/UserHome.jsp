<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import = "model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<script type="text/javascript" src="fancybox/lib/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="fancybox/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<link rel="stylesheet"
	href="fancybox/source/jquery.fancybox.css?v=2.1.4" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="fancybox/source/jquery.fancybox.pack.js?v=2.1.4"></script>
<!--  <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" /> -->
<link rel="StyleSheet" href="styles/calendar.css" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox();
	});
</script>

  <SCRIPT LANGUAGE = "JavaScript">
function check()
{
var oldPass=document.editForm.oldPass.value;
var newPass=document.editForm.newPass.value;
var checkPass=document.editForm.checkPass.value;

  if(oldPass.length<1)
  {alert("The old Password should not be empty");
  return false;
  }
  
  if(newPass.length<1)
  {alert("The new Password should not be empty");
  return false;
  }
  
  if(checkPass.length<1)
  {alert("The checked Password should not be empty");
  return false;
  }
  
  if(checkPass!=newPass) 
  {alert("The checked Password should be the same as the new Password");
  return false;
  } 
}
</SCRIPT>


<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px; //
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 350px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<br>
	<div id="main_div">
		<body>
		     <% if (session.getAttribute("user")!=null) {
		User user = (User)session.getAttribute("user");
		String userName = user.getUserName();
		int uid = user.getUid();
		String email = user.getEmail();
		String password = user.getPassword();
		%>
			<form name = "editForm" class="form-signin" name="editAccount"
				onsubmit="return check()" method="post" action="EditAccountServlet">
				<table>
					<tr>
						<td colspan="2" align="center">
							<h2 class="form-signin-heading">Your Profile</h2>
						</td>
					</tr>
					<tr>
						
						<td><input type="hidden" name="uid" value="<%=uid%>"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>Account</td>
						<td><input type="text" name="Account" value="<%=userName%>"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="Email" value="<%=email%>"
							readonly="readonly" /></td>
					<tr>
					<tr>
						<td>old Password</td>
						<td><input type="password" name="oldPass" value="" />
						</td>
					</tr>
					<tr>
						<td>new Password</td>
						<td><input type="password" name="newPass" value="" />
						</td>
					</tr>
					<tr>
						<td>check Password</td>
						<td><input type="password" name="checkPass" value="" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button class="button" type="submit">Edit</button>
							<button class="button" type="reset">reset</button>
						</td>
					</tr>
					<% if (request.getAttribute("message")!=null) {
						String message = (String)request.getAttribute("message");
						%>
						<tr> <td colspan = "2"> <%= message %> </td> </tr>
					<% }%>
					
				</table>
			</form>
			<%} %>
		</body>
	</div>
</html>