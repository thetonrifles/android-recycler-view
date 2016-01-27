package com.thetonrifles.recyclerviewsample.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.ArrayList;
import java.util.List;

class ListContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private static class ContactViewHolder extends RecyclerView.ViewHolder {

        View layout;
        TextView txt_full_name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            layout.setOnClickListener(listener);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Contact> mContacts;
    private List<ListItem> mItems;

    private OnItemTapListener mOnItemTapListener;

    public ListContactsAdapter(Context context, List<Contact> contacts) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContacts = contacts;
        mItems = buildItemsList();
    }

    public void setOnItemTapListener(OnItemTapListener listener) {
        mOnItemTapListener = listener;
    }

    private List<ListItem> buildItemsList() {
        List<ListItem> items = new ArrayList<>();
        if (mContacts != null && mContacts.size() > 0) {
            for (Contact contact : mContacts) {
                items.add(new ContactItem(contact));
            }
        } else {
            for (int i=0; i<5; i++) {
                items.add(new EmptyItem());
            }
        }
        return items;
    }

    public void updateContactsList() {
        mItems.clear();
        mItems.addAll(buildItemsList());
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
            final Contact contact = item.getContact();
            ContactViewHolder holder = (ContactViewHolder) viewHolder;
            holder.txt_full_name.setText(contact.getName() + " " + contact.getSurname());
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemTapListener != null) {
                        mOnItemTapListener.onItemTap(contact);
                    }
                }
            });
        }
    }

    public interface OnItemTapListener {

        void onItemTap(Contact contact);

    }

}
