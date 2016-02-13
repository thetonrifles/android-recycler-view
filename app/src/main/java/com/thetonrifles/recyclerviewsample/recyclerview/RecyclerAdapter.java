package com.thetonrifles.recyclerviewsample.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    protected class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txt_label;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txt_label = (TextView) itemView.findViewById(android.R.id.text1);
        }

    }

    private LayoutInflater mLayoutInflater;
    private List<String> mItems;

    public RecyclerAdapter(Context context, List<String> items) {
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        String label = (String) mItems.get(position);
        holder.txt_label.setText(label);
    }

}
