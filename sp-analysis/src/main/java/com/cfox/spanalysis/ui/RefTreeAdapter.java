package com.cfox.spanalysis.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cfox.spanalysis.database.RefTree;

import java.util.List;

public class RefTreeAdapter extends RecyclerView.Adapter<RefTreeHolder> {

    private List<RefTree> mData;
    public RefTreeAdapter(List<RefTree> data) {
        this.mData = data;
    }

    public void refresh(List<RefTree> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void clean() {
        this.mData.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RefTreeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(RefTreeHolder.getLayoutId(), parent,false);
        return new RefTreeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RefTreeHolder holder, int position) {
        holder.onBindView(this, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }
}
