package com.thetonrifles.recyclerviewsample.sort;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.List;

class SortPersonsAdapter extends RecyclerView.Adapter<SortPersonsAdapter.PersonViewHolder> {

    protected static class PersonViewHolder extends RecyclerView.ViewHolder {

        View layout;
        TextView txt_rank;
        TextView txt_full_name;

        public PersonViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txt_rank = (TextView) itemView.findViewById(R.id.txt_rank);
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private SortedList<Person> mPersons;

    public SortPersonsAdapter(Context context, List<Person> persons) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPersons = new SortedList<>(Person.class, new PersonListCallback());
        mPersons.addAll(persons);
    }

    public void addPerson(Person person) {
        mPersons.add(person);
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_person_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder viewHolder, final int position) {
        Person person = mPersons.get(position);
        viewHolder.txt_rank.setText(person.getRank() + "\nRANK");
        viewHolder.txt_full_name.setText(person.getName());
    }

    /**
     * Implementation of callback for getting updates on person list changes.
     */
    private class PersonListCallback extends SortedList.Callback<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            int diff = p1.getRank() - p2.getRank();
            return (diff == 0) ? p1.getName().compareTo(p2.getName()) : diff;
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemInserted(position);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRemoved(position);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
        }

        @Override
        public void onChanged(int position, int count) {
        }

        @Override
        public boolean areContentsTheSame(Person oldItem, Person newItem) {
            return false;
        }

        @Override
        public boolean areItemsTheSame(Person item1, Person item2) {
            return false;
        }

    }

}
