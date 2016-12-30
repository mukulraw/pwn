package com.example.mukul.pwn1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RatingBar rat1 , rat2 , rat3 , rat4;
    TextView text1 , text2 , text3 , text4;

    TextView complaint , submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rat1 = (RatingBar)findViewById(R.id.rating1);
        rat2 = (RatingBar)findViewById(R.id.rating2);
        rat3 = (RatingBar)findViewById(R.id.rating3);
        rat4 = (RatingBar)findViewById(R.id.rating4);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);


        complaint = (TextView)findViewById(R.id.complaint);
        submit = (TextView)findViewById(R.id.submit);

        rat1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                text1.setText(String.valueOf(ratingBar.getRating()));

            }
        });

        rat2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                text2.setText(String.valueOf(ratingBar.getRating()));

            }
        });

        rat3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                text3.setText(String.valueOf(ratingBar.getRating()));

            }
        });


        rat4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                text4.setText(String.valueOf(ratingBar.getRating()));

            }
        });



    }
}
