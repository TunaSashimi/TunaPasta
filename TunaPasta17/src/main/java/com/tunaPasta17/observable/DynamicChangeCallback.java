package com.tunaPasta17.observable;


import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

public class DynamicChangeCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

    private RecyclerView.Adapter adapter;

    public DynamicChangeCallback(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(ObservableList<T> sender) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void onItemRangeInserted(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList<T> sender, int fromPosition, int toPosition, int itemCount) {
        adapter.notifyItemRangeRemoved(fromPosition, itemCount);
        adapter.notifyItemRangeInserted(toPosition, itemCount);
    }

    @Override
    public void onItemRangeRemoved(ObservableList<T> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

}