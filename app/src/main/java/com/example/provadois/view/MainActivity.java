package com.example.provadois.view;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.provadois.R;
import com.example.provadois.config.RetrofitService;
import com.example.provadois.entities.DadosClima;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textLatitude;
    TextView textLongitude;
    ImageView imageViewClima;
    List<DadosClima> dadosClimaList;
    double latitudeC;
    double longitudeC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dadosClimaList = new ArrayList<>();

        imageViewClima = findViewById(R.id.imageViewClima);
        textLatitude = findViewById(R.id.inputLatitude);
        textLongitude = findViewById(R.id.inputLongitude);

        double latitudeC = Double.parseDouble(textLatitude.getText().toString());
        double longitudeC = Double.parseDouble(textLongitude.getText().toString());

        searchData();
    }

    private void searchData() {
        RetrofitService.getServico().consulta(latitudeC,longitudeC).enqueue(new Callback<DadosClima>() {
            @Override
            public void onResponse(Call<DadosClima> call, Response<DadosClima> response) {
                dadosClimaList = (List<DadosClima>) response.body();
                for (DadosClima icon : dadosClimaList) {
                    if (icon.getIcon().equals("clearday")) {
                        imageViewClima.setImageResource(R.drawable.clearday);
                    }else if (icon.getIcon().equals("clearnight")) {
                        imageViewClima.setImageResource(R.drawable.clearnight);
                    }else if (icon.getIcon().equals("cloudy")) {
                        imageViewClima.setImageResource(R.drawable.cloudy);
                    }else if (icon.getIcon().equals("fog")) {
                        imageViewClima.setImageResource(R.drawable.fog);
                    }else if (icon.getIcon().equals("partlycloudday")) {
                        imageViewClima.setImageResource(R.drawable.partlycloudday);
                    }else if (icon.getIcon().equals("partlycloudynight")) {
                        imageViewClima.setImageResource(R.drawable.partlycloudynight);
                    }else if (icon.getIcon().equals("rain")) {
                        imageViewClima.setImageResource(R.drawable.rain);
                    }else if (icon.getIcon().equals("sleet")) {
                        imageViewClima.setImageResource(R.drawable.sleet);
                    }else if (icon.getIcon().equals("snow")) {
                        imageViewClima.setImageResource(R.drawable.snow);
                    }else if (icon.getIcon().equals("wind")) {
                        imageViewClima.setImageResource(R.drawable.wind);
                    }
                }
            }

            @Override
            public void onFailure(Call<DadosClima> call, Throwable t) {

            }

        });
    }

}