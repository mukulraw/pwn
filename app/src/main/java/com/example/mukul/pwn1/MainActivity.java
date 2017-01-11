package com.example.mukul.pwn1;

import android.app.Dialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


    //TextView complaint , submit;

    //RecyclerView grid;
    //EditText comment;
    //GridLayoutManager manager;
    //List<Detail> list;
    ViewPager pager;
    static app a;
    TabLayout tabs;
    //GridAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        a = (app)getApplicationContext();


        tabs = (TabLayout)findViewById(R.id.tabs);


        pager = (ViewPager)findViewById(R.id.pager);


      //  comment = (EditText)findViewById(R.id.comment);

     //   list = new ArrayList<>();

      //  manager = new GridLayoutManager(this , 1);
      //  grid = (RecyclerView)findViewById(R.id.grid);


      //  adapter = new GridAdapter(this , list);


     //   grid.setLayoutManager(manager);
       // grid.setAdapter(adapter);


        tabs.addTab(tabs.newTab().setText("Feedback"));
        tabs.addTab(tabs.newTab().setText("Complaint"));

        FragStatePagerAdapter adapter = new FragStatePagerAdapter(getSupportFragmentManager() , 2);

        pager.setAdapter(adapter);


        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


      //  complaint = (TextView)findViewById(R.id.complaint);
      //  submit = (TextView)findViewById(R.id.submit);

      /*  complaint.setOnClickListener(new View.OnClickListener() {
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
*/
/*        submit.setOnClickListener(new View.OnClickListener() {
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


*/

//        Log.d("asdasd" , String.valueOf(list1.get(0).getRating()));



    }



    public class FragStatePagerAdapter extends FragmentStatePagerAdapter {




        private int count;


        FragStatePagerAdapter(FragmentManager fm , int count) {
            super(fm);
            this.count = count;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0)
            {
                return new Feed();
            }
            if (position == 1)
            {

                return new Comp();
            }


            return null;
        }

        @Override
        public int getCount() {
            return count;
        }

    }



    public static class Feed extends Fragment{


        TextView submit;

        RecyclerView grid;
        RadioGroup group;
        RadioButton btn;
        GridLayoutManager manager;
        List<Detail> list;

        GridAdapter adapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.feed_layout , container , false);


            group = (RadioGroup)view.findViewById(R.id.radioGroup);


              list = new ArrayList<>();

              manager = new GridLayoutManager(getActivity() , 1);
              grid = (RecyclerView)view.findViewById(R.id.grid);


              adapter = new GridAdapter(getActivity() , list);


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







              submit = (TextView)view.findViewById(R.id.submit);



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
                        Toast.makeText(getContext() , "Please fill all ratings" , Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        int selId = group.getCheckedRadioButtonId();

                        RadioButton butt = (RadioButton)view.findViewById(selId);

                        String comm = butt.getText().toString();



                        try {
                            a.finalObj.put("feed" , comm);
                            a.finalObj.put("rating" , ratArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Intent intent = new Intent(getContext() , UserInfo.class);
                        intent.putExtra("type" , "feed");
                        startActivity(intent);





                    }

                }







            }
        });








            return view;
        }
    }



    public static  class Comp extends Fragment{

        EditText query;
        TextView submit;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.comp_layout , container , false);


            query = (EditText)view.findViewById(R.id.query);
            submit = (TextView)view.findViewById(R.id.submit);



            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String q = query.getText().toString();

                    try {
                        a.obj2.put("complaint" , q);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getContext() , UserInfo.class);
                    intent.putExtra("type" , "comp");
                    startActivity(intent);

                }
            });



            return view;
        }
    }


}
