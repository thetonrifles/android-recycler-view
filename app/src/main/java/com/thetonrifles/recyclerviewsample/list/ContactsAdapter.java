package com.thetonrifles.recyclerviewsample.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;
import com.thetonrifles.recyclerviewsample.model.Contact;

import java.util.ArrayList;
import java.util.List;

class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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

    private List<ListItem> mItems;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItems = buildItemsList(contacts);
    }

    private List<ListItem> buildItemsList(List<Contact> contacts) {
        List<ListItem> items = new ArrayList<>();
        if (contacts != null && contacts.size() > 0) {
            for (Contact contact : contacts) {
                items.add(new ContactItem(contact));
            }
        } else {
            items.add(new EmptyItem());
        }
        return items;
    }

    public void updateContactsList(List<Contact> contacts) {
        mItems.clear();
        mItems.addAll(buildItemsList(contacts));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ListItem.EMPTY_TYPE) {
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
        if (type == ListItem.CONTACT_TYPE) {
            ContactItem item = (ContactItem) mItems.get(position);
            Contact contact = item.getContact();
            ContactViewHolder holder = (ContactViewHolder) viewHolder;
            holder.txt_full_name.setText(contact.getName() + " " + contact.getSurname());
        }
    }

}
