package com.example.myapplication.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.R;
import com.example.myapplication.models.CarProduct;
import com.squareup.picasso.Picasso;


import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {
    private List<CarProduct> mDataset;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imageView;
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvDescription;
        public TextView tvAmount;
        public TextView tvTotalPrice;
        public MyViewHolder( View v) {

            super(v);
            cardView = (CardView) v.findViewById(R.id.car_card);
            imageView =(ImageView) v.findViewById(R.id.product_img);
            tvName = (TextView) v.findViewById(R.id.product_name);
            tvPrice =(TextView) v.findViewById(R.id.product_price);
            tvDescription = v.findViewById(R.id.product_description);
            tvAmount = v.findViewById(R.id.amount);
            tvTotalPrice = v.findViewById(R.id.totalPrice);
        }
    }

    public CarAdapter(List<CarProduct> dataSet){
        this.mDataset = dataSet;
    }

    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_card_view,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(CarAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(mDataset.get(position).imgUrl).into(holder.imageView);
        holder.tvName.setText(mDataset.get(position).name);
        holder.tvPrice.setText("$ "+String.valueOf(mDataset.get(position).price));
        holder.tvDescription.setText(mDataset.get(position).description);
        holder.tvAmount.setText(String.valueOf(mDataset.get(position).amount));
        holder.tvTotalPrice.setText(String.valueOf(mDataset.get(position).totalPrice));

    }

    @Override
    public int getItemCount() {
            return mDataset.size();
    }

}
