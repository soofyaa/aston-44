<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Information</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</head>
<body>
<h2>Weather Information: ${weatherData.city}</h2>

<p>Description: ${weatherData.description}</p>
<p>Temperature: ${weatherData.temp} °C</p>
<p>Feels Like: ${weatherData.feelsLike} °C</p>

</body>
</html>
