<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>City#Time#Population
<c:forEach var="city" items="${cities}">
${city.name}#${city.shortTime}#${city.population}</c:forEach>