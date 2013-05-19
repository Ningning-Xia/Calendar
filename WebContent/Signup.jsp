<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import = "model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>

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
var account=document.SignupForm.account.value;
var email=document.SignupForm.email.value;
var Pass=document.SignupForm.Pass.value;
var checkPass=document.SignupForm.checkPass.value;

	if(account.length<1)
	{alert("The account should not be empty");
	return false;
	}
	
	if(email.length<1)
	{alert("The email should not be empty");
	return false;
	}
  
  if(Pass.length<1)
  {alert("The new Password should not be empty");
  return false;
  }
  
  if(checkPass.length<1)
  {alert("The checked Password should not be empty");
  return false;
  }
  
  if(checkPass!=Pass) 
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
			<form name = "SignupForm" class="form-signin" name="Signup"
				onsubmit="return check()" method="post" action="SignupServlet">
				<table align = "center">
					<tr>
						<td colspan="2" align="center">
							<h2 class="form-signin-heading">Sign Up</h2>
						</td>
					</tr>
					
					<tr> <td> <br></td></tr>
					
					<tr>
						
						<td><input type="hidden" name="uid" value=""/></td>
					</tr>
					<tr>
						<td>Account</td>
						<td><input type="text" name="account" value=""/></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" name="email" value=""/></td>
					<tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="Pass" value="" />
						</td>
					</tr>
					<tr>
						<td>check Password</td>
						<td><input type="password" name="checkPass" value="" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<button class="button" type="submit">Submit</button>
							<button class="button" type="reset">reset</button>
						</td>
					</tr>
					<% if (request.getAttribute("duplicate_account")!=null) {
						String duplicate_account = (String)request.getAttribute("duplicate_account");
						%>
						<tr> <td colspan = "2"> <%= duplicate_account %> </td> </tr>
					<% }%>
					
						<% if (request.getAttribute("duplicate_email")!=null) {
						String duplicate_account = (String)request.getAttribute("duplicate_email");
						%>
						<tr> <td colspan = "2"> <%= duplicate_account %> </td> </tr>
					<% }%>
					
				</table>
			</form>
		</body>
	</div>
</html>