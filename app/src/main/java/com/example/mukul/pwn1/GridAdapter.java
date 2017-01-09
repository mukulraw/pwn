package com.example.mukul.pwn1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    Context context;
    List<Detail> list = new ArrayList<>();

    public GridAdapter(Context context , List<Detail> list)
    {
        this.context = context;
        this.list = list;
    }



    public void setGridData(List<Detail> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_model , parent , false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Detail item = list.get(position);

        holder.head.setText(item.getName());



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
