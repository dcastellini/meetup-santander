package com.santander.meetup.service;

import com.google.gson.Gson;
import com.santander.meetup.domain.Meetup;
import com.santander.meetup.domain.weather.WeatherResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    public static final String SERVICIO_CLIMA_KEY = "beca23754522431db34a0e4a4077d141";

    public  WeatherResponse getWeatherFor16Days(Double latitud, Double longitud) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.weatherbit.io/v2.0/forecast/daily?&lat="+ latitud + "&lon=" + longitud + "&key=" + SERVICIO_CLIMA_KEY )
                .method("GET", null)
                .build();

        Response response = client.newCall(request).execute();

        String json = response.body().string();
        Gson gson = new Gson();
        WeatherResponse weatherResponse = gson.fromJson(json,WeatherResponse.class);

        return weatherResponse;
    }



    public Double getWeatherForDateReceived(Meetup meetup) throws IOException {

        WeatherResponse weatherResponse = getWeatherFor16Days(meetup.getLatitud(), meetup.getLongitud());
        DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Double temperaturaObtenidaParaLaFecha = null;

        for(int i = 0; i < weatherResponse.getData().size() ; i++){
            String fechaDelaMeetup = meetup.getFecha().format(formateoFecha);
            if((weatherResponse.getData().get(i).getDatetime()).equals(fechaDelaMeetup)){
                temperaturaObtenidaParaLaFecha = weatherResponse.getData().get(i).getTemp();
            }
        }
        return temperaturaObtenidaParaLaFecha;
    }

}
