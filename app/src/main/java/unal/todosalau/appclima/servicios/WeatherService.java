package unal.todosalau.appclima.servicios;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import unal.todosalau.appclima.modelos.WeatherResponse;

public interface WeatherService {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getCurrentWeather(@Query("q") String city, @Query("appid") String apiKey, @Query("units") String units);
}
