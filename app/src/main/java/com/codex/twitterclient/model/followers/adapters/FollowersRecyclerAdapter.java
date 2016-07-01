package com.codex.twitterclient.model.followers.adapters;




import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.codex.twitterclient.R;
import com.codex.twitterclient.base.common.ui.recyclerview.adapter.BaseRecyclerAdapter;
import com.codex.twitterclient.model.followers.dtos.FollowerItem;
import java.util.List;


/**
 * Created by YousefEbrahim on 16/01/03.
 */
public class FollowersRecyclerAdapter extends BaseRecyclerAdapter {
    private  List<FollowerItem> followers;
    private  Context context;

    public List<FollowerItem> getFollowers(){
        return followers;
    }
    public void setFollowers(List<FollowerItem> data) {
        this.followers = data;

        notifyDataSetChanged();
        
    }
    public void setFollowersLoadMore(List<FollowerItem> data) {
       this.followers.addAll(data);
        notifyItemRangeInserted((getCountOfData() - data.size()), data.size());


    }
    public class ViewHolderFollower extends RecyclerView.ViewHolder {
       
        public ViewHolderFollower(View cellView) {
            super(cellView);
           

        }
    }

    public void add(final int position, FollowerItem item) {
       followers.add(position, item);
        notifyItemInserted(position);


    }


    public void remove(FollowerItem item) {
        int position = followers.indexOf(item);
        followers.remove(position);
        notifyItemRemoved(position);
    }
    public void Clear( ) {
        followers.clear();
        notifyDataSetChanged();
    }

    public FollowersRecyclerAdapter(final Context context, List<FollowerItem> followers,RecyclerView mRecyclerView) {
        super(context,mRecyclerView);

        this.context = context;
        this.followers = followers;


    }



    @Override
    public RecyclerView.ViewHolder onCreateMainViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_item, parent, false);
        return  new ViewHolderFollower(v);
    }

    @Override
    public void onCreateMainView(RecyclerView.ViewHolder holderView, int position) {
        onCreatView(holderView,position);
    }

    @Override
    public <T> List<T> getData() {
        return (List<T>) followers;
    }


    private void onCreatView(final RecyclerView.ViewHolder holderView, int position) {
        final ViewHolderFollower holder = (ViewHolderFollower) holderView;
        final FollowerItem FollowerItem=getItem(position);



    }


    public FollowerItem getItem(int position) {
        if (followers != null) {
            return followers.get(position);
        }
        return null;
    }
}