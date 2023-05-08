package unal.todosalau.appclima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import unal.todosalau.appclima.clientes.ApiClient;
import unal.todosalau.appclima.modelos.WeatherResponse;
import unal.todosalau.appclima.servicios.WeatherService;

public class MainActivity extends AppCompatActivity {
    private TextView cityTextView;
    private TextView temperatureTextView;
    private Button fetchWeatherButton;
    private EditText cityEditText;

    private final String apiKey = "ESCRIBE_TU_APIKEY"; // Replace with your OpenWeatherMap API key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.cityTextView);
        temperatureTextView = findViewById(R.id.temperatureTextView);
        fetchWeatherButton = findViewById(R.id.fetchWeatherButton);
        cityEditText = findViewById(R.id.cityEditText);

        fetchWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityEditText.getText().toString();
                fetchWeatherData(city);
            }
        });
    }

    private void fetchWeatherData(String city) {
        WeatherService weatherService = ApiClient.getInstance().create(WeatherService.class);
        Call<WeatherResponse> call = weatherService.getCurrentWeather(city, apiKey, "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    cityTextView.setText(weatherResponse.getCityName());
                    temperatureTextView.setText(String.format("%.1f°C", weatherResponse.getMain().getTemperature()));
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                cityTextView.setText(R.string.error_text);
                temperatureTextView.setText("");
            }
        });
    }
}