package com.example.mukul.pwn1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    Context context;
    List<Detail> list = new ArrayList<>();
    List<Bean> updatedList = new ArrayList<>();

    public GridAdapter(Context context , List<Detail> list)
    {
        this.context = context;
        this.list = list;
        updatedList = Arrays.asList(new Bean[list.size()]);
    }



    public void setGridData(List<Detail> list)
    {
        this.list = list;
        updatedList = Arrays.asList(new Bean[list.size()]);
        notifyDataSetChanged();
    }


    public List<Bean> getUpdatedList()
    {
        return this.updatedList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_model , parent , false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Detail item = list.get(position);

        holder.head.setText(item.getName());




        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                holder.rating.setText(String.valueOf(ratingBar.getRating()));

                Bean update = new Bean();

                update.setId(item.getId());
                update.setRating(String.valueOf(ratingBar.getRating()));

                updatedList.set(position , update);

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RatingBar ratingBar;
        TextView rating , head;

        public ViewHolder(View itemView) {
            super(itemView);
            head = (TextView)itemView.findViewById(R.id.text);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_bar);
            rating = (TextView)itemView.findViewById(R.id.rating);

        }
    }

}
