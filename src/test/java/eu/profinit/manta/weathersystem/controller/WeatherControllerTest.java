package eu.profinit.manta.weathersystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHistory() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "Prague")
                .param("dateTime", "2007-12-03T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value("Prague"))
                .andExpect(jsonPath("$.dateTime").value("2007-12-03T10:15:00"));
    }

    @Test
    public void getHistoryDateTime() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "Prague")
                .param("dateTime", "2007-12-03T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value("Prague"))
                .andExpect(jsonPath("$.dateTime").value("2007-12-03T10:15:00"));
    }

    @Test
    public void getHistoryMissingParameters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getHistoryCityNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "")
                .param("dateTime", "2007-12-03T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getHistoryMissingRequiredParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getHistoryInvalidDateFormat() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "Prague")
                .param("dateTime", "2007-99-99T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "Prague")
                .param("dateTime", "2007-12-03")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getHistoryFutureDate() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/history")
                .param("city", "Prague")
                .param("dateTime", LocalDateTime.MAX.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void getForecast() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "Prague")
                .param("dateTime", "2030-12-03T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.city").value("Prague"))
                .andExpect(jsonPath("$.dateTime").value("2030-12-03T10:15:00"));
    }

    @Test
    public void getForecastMissingParameters() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getForecastCityNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "")
                .param("dateTime", "2030-12-03T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getForecastMissingRequiredParameter() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getForecastInvalidDateFormat() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "Prague")
                .param("dateTime", "2030-99-99T10:15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "Prague")
                .param("dateTime", "2030-12-03")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getForecastPastDate() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/weather/v1/forecast")
                .param("city", "Prague")
                .param("dateTime", LocalDateTime.MIN.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
}