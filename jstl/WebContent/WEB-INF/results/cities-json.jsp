<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
{ headings: ["City", "Time", "Population"],
  cities: [
  <c:forEach var="city" items="${cities}" varStatus="status">
    ["${city.name}","${city.shortTime}","${city.population}"]
    <c:if test="${!status.last}">,</c:if>
  </c:forEach>
  ]
}