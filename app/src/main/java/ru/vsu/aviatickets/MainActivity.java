package ru.vsu.aviatickets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.aviatickets.api.ApiCityService;
import ru.vsu.aviatickets.api.ApiService;
import ru.vsu.aviatickets.api.model.Route;

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
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.createApi()
                        .getRoute(editText.getText().toString())
                        .enqueue(new Callback<Route>() {
                            @Override
                            public void onResponse(Call<Route> call, Response<Route> response) {
                                Route route = response.body();
                                textView.setText(route.toString());
                            }

                            @Override
                            public void onFailure(Call<Route> call, Throwable t) {
                                textView.setText("error");
                            }
                        });
            }
        });
    }

}
