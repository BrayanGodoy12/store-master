package com.example.myapplication.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.models.Offer;
import com.squareup.picasso.Picasso;


import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder>  {
    private List<Offer> mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imageView;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvDescription;
        public MyViewHolder(View v) {
            super(v);

            cardView = (CardView) v.findViewById(R.id.product_card);
            imageView =(ImageView) v.findViewById(R.id.offer_img);
            tvName = (TextView) v.findViewById(R.id.offer_name);
            tvPrice =(TextView) v.findViewById(R.id.offer_price);
            tvDescription = v.findViewById(R.id.offer_description);
        }
    }
    public OffersAdapter(List<Offer> offersDataSet){
        mDataset = offersDataSet;
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card_view,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Picasso.get().load(mDataset.get(position).imgUrl).into(holder.imageView);
        holder.tvName.setText(mDataset.get(position).name);
        holder.tvPrice.setText("$ "+String.valueOf(mDataset.get(position).price));
        holder.tvDescription.setText(mDataset.get(position).description);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
