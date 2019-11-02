package com.example.myapplication.adapters;



import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


import com.example.myapplication.R;
import com.example.myapplication.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private List<Category> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public ImageView imageView;
        public MyViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cv);
            imageView = (ImageView) v.findViewById(R.id.iv);
        }
    }

    public CategoriesAdapter(List<Category> myDataset) {
        mDataset = myDataset;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view,parent,false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Picasso.get().load(mDataset.get(position).img).into(holder.imageView);
        holder.cardView.setTag(mDataset.get(position).id);
        holder.imageView.setContentDescription(mDataset.get(position).name);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
