package com.learn.github.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.github.R;
import com.learn.github.databinding.ItemFollowerBinding;
import com.learn.github.model.User;

import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.FollowerHolder> {

    private List<User> items;
    private LayoutInflater layoutInflater;

    public FollowerAdapter(List<User> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FollowerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        ItemFollowerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_follower,parent,false);
        return new FollowerHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerHolder holder, int position) {

        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class FollowerHolder extends RecyclerView.ViewHolder {

        private final ItemFollowerBinding itemFollowerBinding;

        public FollowerHolder(@NonNull ItemFollowerBinding itemFollowerBinding) {
            super(itemFollowerBinding.getRoot());
            this.itemFollowerBinding = itemFollowerBinding;
        }

        public void bind(User user) {
            itemFollowerBinding.setUser(user);
            itemFollowerBinding.executePendingBindings();
        }

    }
}
