<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


hi guys !! slowly i am moving towards my goal.....
<% String s =new String("This is string from scriptlets");%>
<%= s + "velmurugan" %>
<%! static int  count =0;%>
The value of count is calculated by this call: <%=++count %>
</body>
</html>