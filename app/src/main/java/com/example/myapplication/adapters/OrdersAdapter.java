package com.example.myapplication.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.models.OrderData;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter <OrdersAdapter.MyViewHolder> {
    List<OrderData> mDataset;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView tvCode;
        public TextView tvDate;
        public TextView tvPrice;

        public MyViewHolder(View v){
            super(v);
            cardView = v.findViewById(R.id.order_card);
            tvCode = v.findViewById(R.id.order_code);
            tvDate = v.findViewById(R.id.order_date);
            tvPrice = v.findViewById(R.id.order_price);
        }
    }
    public OrdersAdapter(List<OrderData> ordersDataSet){
        mDataset = ordersDataSet;
    }

    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_view,parent,false);
        OrdersAdapter.MyViewHolder vh = new OrdersAdapter.MyViewHolder(v);

        return  vh;
    }
    @Override
    public void onBindViewHolder(OrdersAdapter.MyViewHolder holder, int position){
        holder.tvCode.setText(mDataset.get(position).orderId);
        holder.tvDate.setText(mDataset.get(position).date);
        holder.tvPrice.setText("$ "+String.valueOf(mDataset.get(position).price));
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
