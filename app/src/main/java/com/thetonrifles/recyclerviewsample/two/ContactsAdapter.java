package com.thetonrifles.recyclerviewsample.two;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.List;

class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static class EmptyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public EmptyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "empty", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_full_name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "item", Toast.LENGTH_SHORT).show();
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Contact> mItems;

    public ContactsAdapter(Context context, List<Contact> contacts) {
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
