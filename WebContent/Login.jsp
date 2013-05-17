<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.* "%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <SCRIPT LANGUAGE = "JavaScript">
function check()
{
var account=document.loginForm.account.value;
var password=document.loginForm.password.value;

  if(account.length<1)
  {alert("The account should not be empty");
  return false;
  }
  
  if(password.length<1)
  {alert("The password should not be empty");
  return false;
  }
}
</SCRIPT>
    <meta charset="utf-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
     <link rel="StyleSheet" href="./styles/calendar.css" type="text/css" />
        <!--  <link rel="StyleSheet" href="styles/bootstrap.css" type="text/css" /> -->
       
    <style type="text/css">
     body {
        padding-top: 40px;
        padding-bottom: 40px;
        //background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 320px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <link href="../assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="../assets/ico/favicon.png">
  </head>
  
  
<body>
<jsp:include page="header.jsp"/>
<br>
<br>


    <div class="container">

      <form class="form-signin" name = "loginForm" onsubmit = "return check()" method="post" action="LoginServlet">
        <table>
        <tr> <td align = "center" colspan="2"> 
        <h2 class="form-signin-heading">Please sign in</h2>
        </td></tr>
      
        <tr> <td> Account </td><td> 
        <input name = "account" type="text" placeholder="Account">
        </td></tr>
        <tr><td>Password </td><td>
        <input name = "password" type="password" placeholder="Password">
        </td></tr>
        <tr> <td align = "center" colspan = "2">
        <button class="button" type="submit">Sign in</button>
         <button class="button" type="reset">Reset</button>
        </td></tr>
        <br>
        <% if (request.getAttribute("error") !=null )
        	{ String error = (String)request.getAttribute("error");
        	%>
        	<tr> <td colspan="2"> <%=error %> </td></tr>
        <%} %>
        
        </table>
      </form>
    </div> 

  </body>
</html>