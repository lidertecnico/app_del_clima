package unal.todosalau.appclima.modelos;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private Main main;

    public String getCityName() {
        return cityName;
    }

    public Main getMain() {
        return main;
    }

    public static class Main {
        @SerializedName("temp")
        private float temperature;

        public float getTemperature() {
            return temperature;
        }
    }
}