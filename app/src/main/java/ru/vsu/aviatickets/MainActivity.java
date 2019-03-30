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
import ru.vsu.aviatickets.api.SkyScannerApiService;
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
        editText.setText("Из лондона в берлин");

        SkyScannerApiService.createApi()
                .listPlaces("5218bbef51msh474a4c05a0b5196p1fd9c4jsn18c6c2615b8d", "RU",
                        "RUB", "ru-RU", "Воронеж")
                .enqueue(new Callback<SkyScannerPlaces>() {
                    @Override
                    public void onResponse(Call<SkyScannerPlaces> call, Response<SkyScannerPlaces> response) {
                        Headers headers = response.headers();
                        SkyScannerPlaces places = response.body();
                    }

                    @Override
                    public void onFailure(Call<SkyScannerPlaces> call, Throwable t) {
                        int a = 2;
                        a++;
                    }
                });
    }
}
