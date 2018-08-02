package us.sayederfanarefin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import us.sayederfanarefin.R;
import us.sayederfanarefin.model.friends;
import us.sayederfanarefin.model.friends_recycler_view;
import us.sayederfanarefin.model.users;

/**
 * Created by erfan on 9/14/17.
 */

public class FriendsFirebaseRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<friends> friendsList;
    private Context context;

    public FriendsFirebaseRecycler(List<friends> friendsList, Context context) {
        this.friendsList = friendsList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        //Log.v("---xx-",  " inside getItemViewType, postiion->" + String.valueOf(position) +" length->" +  String.valueOf(friendsList.size()) );
        if(friendsList.size()-1 == position){
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.friend_item_without_buttons, parent, false);
                return new friends_recycler_view(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.friends_list_header, parent, false);
                return new friends_recycler_view(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:
                friends_recycler_view viewHolder_friends = (friends_recycler_view) holder;

                friends friends = friendsList.get(position);
                viewHolder_friends.mood(friends.getPhone());
                viewHolder_friends.phone(friends.getPhone());
                viewHolder_friends.name("");
                break;

            case 1:
                friends_recycler_view viewHolder_timeline_header = (friends_recycler_view)holder;

                friends friendss = friendsList.get(position);
                viewHolder_timeline_header.mood(friendss.getPhone());
                viewHolder_timeline_header.phone(friendss.getPhone());
                viewHolder_timeline_header.name("");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

}
