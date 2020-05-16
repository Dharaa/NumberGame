package com.zeronesgame;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeronesgame.databinding.ItemLayoutBinding;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {

    private int[] mStringList;
    private Activity mActivity;

    public ButtonAdapter(Activity activity, int[] stringList){
        this.mActivity = activity;
        this.mStringList = stringList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(itemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mItemLayoutBinding.mBtnNumber.setText(mStringList[position]+"");
        if(mStringList[position] == 0){
            holder.mItemLayoutBinding.mIvCross.setVisibility(View.VISIBLE);
            holder.mItemLayoutBinding.mBtnNumber.setVisibility(View.GONE);
        }
        holder.mItemLayoutBinding.mBtnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(position);
            }
        });
        holder.mItemLayoutBinding.executePendingBindings();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mStringList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLayoutBinding mItemLayoutBinding;

        private ViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.mItemLayoutBinding = itemLayoutBinding;
        }
    }
    public void onItemClick(int pos){

    }
}
