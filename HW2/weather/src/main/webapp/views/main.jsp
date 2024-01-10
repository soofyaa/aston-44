<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Hello</title>
</head>
<body>
<form action="/weather/info" method="get">
  <label for="city">City name:</label>
  <input type="text" id="city" name="city" required>
  <button type="submit">Get weather</button>
</form>
</body>
</html>

