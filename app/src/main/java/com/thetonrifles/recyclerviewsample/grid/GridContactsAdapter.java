package com.thetonrifles.recyclerviewsample.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.List;

class GridContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView txt_full_name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Contact> mItems;

    public GridContactsAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems = contacts;
    }

    @Override
    public int getItemCount() {
        return mItems.size() > 0 ? mItems.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.size() == 0 ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View itemView = mLayoutInflater.inflate(R.layout.view_empty_item, parent, false);
            return new EmptyViewHolder(itemView);
        } else {
            View itemView = mLayoutInflater.inflate(R.layout.view_contact_item, parent, false);
            return new ContactViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = getItemViewType(position);
        if (type == 1) {
            Contact contact = mItems.get(position);
            ContactViewHolder holder = (ContactViewHolder) viewHolder;
            holder.txt_full_name.setText(contact.getName() + " " + contact.getSurname());
        }
    }

}
