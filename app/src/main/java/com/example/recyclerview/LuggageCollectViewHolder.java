package com.example.recyclerview;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract  class LuggageCollectViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;
    public LuggageCollectViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
