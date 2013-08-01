<?xml version="1.0" encoding="UTF-8"?>
<cities>
  <headings>
    <heading>City</heading>
    <heading>Time</heading>
    <heading>Population</heading>
  </headings>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="city" items="${cities}">
  <city>
    <name>${city.name}</name>
    <time>${city.shortTime}</time>
    <population>${city.population}</population>
  </city>
</c:forEach>
</cities>