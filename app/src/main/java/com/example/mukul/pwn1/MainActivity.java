package com.example.mukul.pwn1;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView complaint , submit;

    RecyclerView grid;
    GridLayoutManager manager;
    List<Detail> list;
    GridAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        manager = new GridLayoutManager(this , 1);
        grid = (RecyclerView)findViewById(R.id.grid);


        adapter = new GridAdapter(this , list);


        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mrtechs.in/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final interfaces cr = retrofit.create(interfaces.class);


        Call<rateBean> call = cr.getRating();

        call.enqueue(new Callback<rateBean>() {
            @Override
            public void onResponse(Call<rateBean> call, Response<rateBean> response) {


                list = response.body().getDetail();

                adapter.setGridData(list);



            }

            @Override
            public void onFailure(Call<rateBean> call, Throwable throwable) {

            }
        });






        complaint = (TextView)findViewById(R.id.complaint);
        submit = (TextView)findViewById(R.id.submit);

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.complaint_dialog);
                dialog.setCancelable(true);

                dialog.show();

                TextView sub = (TextView)dialog.findViewById(R.id.submit);
                TextView can = (TextView)dialog.findViewById(R.id.cancel);

                can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getBaseContext() , UserInfo.class);
                        startActivity(intent);

                    }
                });


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext() , UserInfo.class);
                startActivity(intent);

            }
        });





    }
}
