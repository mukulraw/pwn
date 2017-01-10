package com.example.mukul.pwn1;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    EditText comment;
    GridLayoutManager manager;
    List<Detail> list;
    app a;
    GridAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        a = (app)getApplicationContext();

        comment = (EditText)findViewById(R.id.comment);

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
                final EditText query = (EditText)dialog.findViewById(R.id.query);

                can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();

                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String q = query.getText().toString();

                        try {
                            a.obj2.put("complaint" , q);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(getBaseContext() , UserInfo.class);
                        intent.putExtra("type" , "comp");
                        startActivity(intent);

                    }
                });


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                               //Intent intent = new Intent(getBaseContext() , UserInfo.class);
                //startActivity(intent);




                List<Bean> list1 = adapter.getUpdatedList();


                if (list1.size() == list.size())
                {
                    int flag = 0;

                    JSONArray ratArray = new JSONArray();



                    for (int i = 0 ; i < list1.size() ; i++)
                    {
                        if (list1.get(i) != null)
                        {
                            JSONObject obj = new JSONObject();
                            try {
                                obj.put("ratingId" , String.valueOf(list1.get(i).getId()));
                                obj.put("rating" , String.valueOf(list1.get(i).getRating()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("asdasd" , String.valueOf(list1.get(i).getRating() + " , " + list1.get(i).getId()));

                            ratArray.put(obj);

                        }
                        else
                        {
                            flag++;

                        }

                    }

                    if (flag>0)
                    {
                        Toast.makeText(getApplicationContext() , "Please fill all ratings" , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {


                        String comm = comment.getText().toString();



                        try {
                            a.finalObj.put("feed" , comm);
                            a.finalObj.put("rating" , ratArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Intent intent = new Intent(getBaseContext() , UserInfo.class);
                        intent.putExtra("type" , "feed");
                        startActivity(intent);





                    }

                }







            }
        });




//        Log.d("asdasd" , String.valueOf(list1.get(0).getRating()));



    }
}
