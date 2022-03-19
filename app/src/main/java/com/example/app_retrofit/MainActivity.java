package com.example.app_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_retrofit.Interfaz.RamdonD;
import com.example.app_retrofit.modelo.ramdonD;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView ramdonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ramdonText = findViewById(R.id.ramdonText);
        getRamdon();
    }

    private void getRamdon() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RamdonD ram = retrofit.create(RamdonD.class);
        Call<List<ramdonD>> call = ram.getRamdons();
        call.enqueue(new Callback<List<ramdonD>>() {
            @Override
            public void onResponse(Call<List<ramdonD>> call, Response<List<ramdonD>> response) {
                if(!response.isSuccessful()){
                    ramdonText.setText("Codigo: "+response.code());
                    return;
                }
                List<ramdonD> ramdonDList = response.body();
                for (ramdonD ran: ramdonDList ) {
                    String content = "";
                    content += "userId:"+ ran.getUserId() + "\n";
                    content += "id:"+ ran.getId() + "\n";
                    content += "title:"+ ran.getTitle() + "\n";
                    content += "body:"+ ran.getBody() + "\n\n";
                   ramdonText.append (content);
                }
            }

            @Override
            public void onFailure(Call<List<ramdonD>> call, Throwable t) {
                ramdonText.setText(t.getMessage());

            }
        });
    }
}