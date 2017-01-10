package com.example.mukul.pwn1;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UserInfo extends AppCompatActivity {

    TextView submit;
    app a;
    EditText name , email , phone;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        a = (app)getApplicationContext();

        type = getIntent().getStringExtra("type");


        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);


        submit = (TextView)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (type == "feed") {

                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = phone.getText().toString();


                    try {
                        a.finalObj.put("name", n);
                        a.finalObj.put("email", e);
                        a.finalObj.put("phone", p);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://mrtechs.in/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final interfaces cr = retrofit.create(interfaces.class);

                    Log.d("asdasdasdasd", a.finalObj.toString());

                    Call<String> call = cr.setRating(a.finalObj.toString());

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {


                            Dialog dialog = new Dialog(UserInfo.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.success_dialog);
                            dialog.show();


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


                }
                else
                {
                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = phone.getText().toString();


                    try {
                        a.obj2.put("name", n);
                        a.obj2.put("email", e);
                        a.obj2.put("phone", p);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://mrtechs.in/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final interfaces cr = retrofit.create(interfaces.class);

                    Log.d("asdasdasdasd", a.obj2.toString());

                    Call<String> call = cr.setComplaint(a.obj2.toString());

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {


                            Dialog dialog = new Dialog(UserInfo.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.success_dialog);
                            dialog.show();


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }




            }
        });







    }
}
