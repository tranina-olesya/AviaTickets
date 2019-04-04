package ru.vsu.aviatickets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.aviatickets.api.model.Route;
import ru.vsu.aviatickets.api.model.SkyScannerPlaces;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        App.getIATACodeAPI().getRoute("Из москвы в нью-йорк")
                .enqueue(new Callback<Route>() {
                    @Override
                    public void onResponse(Call<Route> call, Response<Route> response) {
                        Route route = response.body();
                        Headers headers = response.headers();
                    }

                    @Override
                    public void onFailure(Call<Route> call, Throwable t) {

                    }
                });

        App.getSkyScannerAPI().listPlaces("Воронеж")
                .enqueue(new Callback<SkyScannerPlaces>() {
                    @Override
                    public void onResponse(Call<SkyScannerPlaces> call, Response<SkyScannerPlaces> response) {
                        Headers headers = response.headers();
                        SkyScannerPlaces places = response.body();
                    }

                    @Override
                    public void onFailure(Call<SkyScannerPlaces> call, Throwable t) {
                    }
                });
    }
}
