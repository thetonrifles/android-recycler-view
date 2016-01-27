package com.thetonrifles.recyclerviewsample.sort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thetonrifles.recyclerviewsample.R;

import java.util.Collections;
import java.util.List;

class SortPlayersAdapter extends RecyclerView.Adapter<SortPlayersAdapter.PlayerViewHolder> {

    protected static class PlayerViewHolder extends RecyclerView.ViewHolder {

        View layout;
        TextView txt_rank;
        TextView txt_full_name;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txt_rank = (TextView) itemView.findViewById(R.id.txt_rank);
            txt_full_name = (TextView) itemView.findViewById(R.id.txt_full_name);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<Player> mPlayers;

    public SortPlayersAdapter(Context context, List<Player> players) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPlayers = players;
        Collections.sort(mPlayers);
    }

    public void updatePlayersList() {
        Collections.sort(mPlayers);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    @Override
    public SortPlayersAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_player_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PlayerViewHolder viewHolder, final int position) {
        Player player = mPlayers.get(position);
        viewHolder.txt_rank.setText(player.getRank() + "\nRANK");
        viewHolder.txt_full_name.setText(player.getName());
    }

}
