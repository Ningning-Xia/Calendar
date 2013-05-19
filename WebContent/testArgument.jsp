<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form id="form1" action="TestServlet" method="post"
			>
<input type="text" name= "eid" value = "3" />
				Select a file to upload: 
				<input class="button" type="file"
					name="file" size="50" />
					 <br /> 
					 <input type="submit"
					class="button" value="Upload File" /> <br>
					

</form>
</body>
</html>