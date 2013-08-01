<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>Loop Status</title>
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
<script src="./scripts/ajax-utils.js"
        type="text/javascript"></script>
</head>
<body>
<div align="center">
<table border="5">
  <tr><th class="title">Loop Status</th></tr>
</table>
<p/>
<fieldset>
  <legend>Test</legend>
  <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
  <% String[] names = {"Joe", "Jane", "Juan", "Juana"};
     request.setAttribute("names", names); %>
  <h2>Names: 
  <c:forEach var="name" items="${names}" varStatus="status">
    ${name}<c:if test="${!status.last}">,</c:if>
  </c:forEach>
  </h2>
</fieldset>

</div></body></html>