package com.thetonrifles.recyclerviewsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int THRESHOLD = 5;

    private static final int TYPE_CONTACT = 0;
    private static final int TYPE_BUTTON = 1;

    private class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ButtonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItems.add(new Contact(Utils.buildRandomName(5), Utils.buildRandomName(5)));
            notifyDataSetChanged();
        }

    }

    private class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView txt_full_name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
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
        return mItems.size() < THRESHOLD ? mItems.size() + 1 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (mItems.size() < THRESHOLD && position == mItems.size()) ? TYPE_BUTTON : TYPE_CONTACT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BUTTON) {
            View itemView = mLayoutInflater.inflate(R.layout.view_button_item, parent, false);
            return new ButtonViewHolder(itemView);
        } else {
            View itemView = mLayoutInflater.inflate(R.layout.view_contact_item, parent, false);
            return new ContactViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = getItemViewType(position);
        if (type == TYPE_CONTACT) {
            Contact contact = mItems.get(position);
            ContactViewHolder holder = (ContactViewHolder) viewHolder;
            holder.txt_full_name.setText(contact.getName() + " " + contact.getSurname());
        }
    }

}
