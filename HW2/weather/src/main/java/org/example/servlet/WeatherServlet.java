package org.example.servlet;

import org.example.model.WeatherData;
import org.example.model.Root;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/info")
public class WeatherServlet extends HttpServlet {
    private static final String API_KEY = "5f5a9a788cc70316af3c41981ba2f54e";
    private static final int CONNECTION_TIMEOUT = 5000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city = req.getParameter("city");

        if (city == null) {
            resp.sendRedirect(req.getContextPath() + "/main");
            return;
        }

        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                Root data = objectMapper.readValue(connection.getInputStream(), Root.class);
                WeatherData weatherData = WeatherData.builder()
                        .city(city)
                        .description(data.getWeather().get(0).getDescription())
                        .temp((int) Math.round(data.getMain().getTemp() - 273.15))
                        .feelsLike((int) Math.round(data.getMain().getFeels_like() - 273.15))
                        .build();
                req.setAttribute("weatherData", weatherData);
                req.getRequestDispatcher("/views/weather.jsp").forward(req, resp);

            } else {
                resp.setStatus(responseCode);
                StringBuilder errorMessage = new StringBuilder("An error occurred while receiving the data. Response code: ")
                        .append(responseCode)
                        .append("\n");
                if (responseCode == 404) {
                    errorMessage.append("City not found");
                }
                resp.setContentType("text/plain");
                resp.getWriter().write(errorMessage.toString());
            }
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
            resp.setContentType("text/plain");
            resp.getWriter().write("The response timeout has expired :(");
        }
    }
}
