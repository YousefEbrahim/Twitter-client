package com.codex.twitterclient.base.common.ui.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.codex.twitterclient.R;
import com.codex.twitterclient.base.common.ui.recyclerview.interfaces.OnLastItemListener;
import com.codex.twitterclient.base.common.ui.recyclerview.interfaces.OnLoadMoreListener;

import java.util.List;

/**
 * Created by Yousef on 1/5/2016.
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder>  {

    private int mScrollOffset = 4;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final int VIEW_TYPE_HEADER = 2;
    private OnLoadMoreListener mOnLoadMoreListener;
    private OnLastItemListener mOnLastItemListener;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount , firstVisibleItem;
    private boolean isScrollUp=true;
    private boolean isEnableLoadMore=true;
    private int indexOfLodingView=-1;
    private RecyclerView getRecyclerView;
    public BaseRecyclerAdapter(final Context context, RecyclerView getRecyclerView) {
        this.setGetRecyclerView(getRecyclerView);
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getRecyclerView.getLayoutManager();
        getRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (linearLayoutManager !=null){
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();
                    if (mOnLastItemListener != null) {

                    if (lastVisibleItem >= ( totalItemCount-1)) {
                            if (isScrollUp==false) {
                                mOnLastItemListener.onLastItem();
                            }
                    } else   {
                        mOnLastItemListener.onNotLastItem();
                    }
                    if (Math.abs(dy) > mScrollOffset) {
                        if ( dy > 0){
                            isScrollUp=false;
                        }else{
                            isScrollUp=true;
                            mOnLastItemListener.onNotLastItem();
                        }
                    }

            }
                    if (mOnLoadMoreListener != null && isEnableLoadMore()) {
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                            if (getData() != null) {
                                indexOfLodingView= (getData().size() - 1);
                                    if (indexOfLodingView >=0 &&  getData().get(indexOfLodingView)!= null) {
                                        getData().add(null);
                                        indexOfLodingView= getData().size()-1;
                                        notifyItemInserted(indexOfLodingView);
                                    }
                                    mOnLoadMoreListener.onLoadMore();
                                    isLoading = true;

                            }
                        }
                    }
            }
            }
        });

    }

    public abstract RecyclerView.ViewHolder onCreateMainViewHolder(ViewGroup parent, int viewType);
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holderLoading = onCreateLoadingViewHolder(parent, viewType);
        RecyclerView.ViewHolder holderHeader = onCreateHeaderHolder(parent, viewType);
        if(holderLoading != null){
            return holderLoading;
        }else if (holderHeader != null){
           return  holderHeader;
        }else{
            return  onCreateMainViewHolder(parent, viewType);
        }


    }

    private RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate( onGetMoreLayout(), parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    private RecyclerView.ViewHolder onCreateHeaderHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(onGetHeaderLayout(), parent, false);
            return new HeaderViewHolder(view);
        }
        return null;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
           if (  loadingViewHolder.loading_progressbar !=null) {
               loadingViewHolder.loading_progressbar.setIndeterminate(true);
           }
        }else if (holder instanceof HeaderViewHolder){
           onCreateHeaderView(holder,position);
       }else {
           if (addedHeader()){
               onCreateMainView(holder,position -1);
           }else {
               onCreateMainView(holder, position);
           }
        }


    }

    protected   void onCreateHeaderView(final RecyclerView.ViewHolder holderView, int position){

    }
    public abstract void onCreateMainView(final RecyclerView.ViewHolder holderView, int position) ;

    public boolean isEnableLoadMore() {
        return isEnableLoadMore;
    }

    public RecyclerView getGetRecyclerView() {
        return getRecyclerView;
    }

    private void setGetRecyclerView(RecyclerView getRecyclerView) {
        this.getRecyclerView = getRecyclerView;
    }


    private   class LoadingViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar loading_progressbar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        if (onGetMoreLayout()== R.layout.layout_loading_item) {
            loading_progressbar = (ProgressBar) itemView.findViewById(R.id.loading_progressbar);
        }else{
            onSetViewLoading(itemView);
        }
    }
}

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);

            onSetViewHeader(itemView);

        }
    }

    protected void onSetViewLoading(View itemView){

    }

    protected void onSetViewHeader(View itemView) {

    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    public void setOnLastItemOrNotListener(OnLastItemListener mOnLastItemListener) {
        this.mOnLastItemListener = mOnLastItemListener;
    }
    public abstract <T> List<T> getData();

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && addedHeader()) {
            return VIEW_TYPE_HEADER;
        } else if (position < getCountOfData() && getData().get(position) == null) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    /**
     * get count of recycler view
     **/
    @Override
    public int getItemCount() {
        if (addedHeader()) {
            return getData() == null ? 0 : (getData().size() + 1);
        } else {
            return getData() == null ? 0 : getData().size();
        }
    }


    private boolean addedHeader(){
        if (onGetHeaderLayout() == 0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * get count of data changed about count of recycle when set header
     */

    public int getCountOfData (){
        return getData() == null ? 0 : getData().size();
    }

    /**
     *
     * @param pos
     * @return position of item in recycler view
     */

    public int getPositionInRecycler (int pos){
        if (addedHeader() && (pos+1)< getItemCount()){
            return (pos+1);
        }else{
           return pos;
        }

    }


    public void isLoaded() {
        isLoading = false;
        if (getData() != null) {
           if (indexOfLodingView >= 0 && getData().size() > indexOfLodingView && getData().get(indexOfLodingView) == null) {
                getData().remove(indexOfLodingView);
                notifyItemRemoved(indexOfLodingView+1);
                indexOfLodingView=-1;
            }else{
                if (indexOfLodingView != -1) {
                    int inde=getData().indexOf(null);
                    if (inde!= -1) {
                        getData().remove(inde);
                        notifyItemRemoved(inde);
                        indexOfLodingView = -1;
                    }
                }
            }
        }
    }


    public boolean getLoading() {
       return isLoading ;
    }


    /**
     *
     * @return layout of get more data
     */
    protected int onGetMoreLayout(){
        return R.layout.layout_loading_item;
    }

    /**
     *
     * @return layout of header
     */
    protected int onGetHeaderLayout() {
        return 0;
    }

    /**
     *
     * @param enable  set enable to load more data
     *
     */
    public void setEnableLoadMore(boolean enable){
        this.isEnableLoadMore = enable;
    }

    public int getFirstVisibleItem(){
        return  firstVisibleItem ;
    }
}