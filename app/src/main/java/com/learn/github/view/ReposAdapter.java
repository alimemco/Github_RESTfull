package com.learn.github.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.github.R;
import com.learn.github.databinding.ItemBinding;
import com.learn.github.model.Repo;
import com.learn.github.viewModel.ReposViewModel;

import java.util.List;

public class ReposAdapter  extends RecyclerView.Adapter<ReposAdapter.Holder> {

    private  List<ReposViewModel> value;
    public ReposAdapter(List<ReposViewModel> value) {
        this.value = value;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        ItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_repos_list,parent,false);

        return new Holder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(value.get(position));

    }

    @Override
    public int getItemCount() {
        if (value == null || value.size() == 0 ) return 0 ;
        return value.size();
    }

    class Holder extends RecyclerView.ViewHolder{


        private ItemBinding itemBinding;
        public Holder(@NonNull ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(ReposViewModel reposViewModel){

            itemBinding.setReposViewModel(reposViewModel);
            itemBinding.executePendingBindings();
        }
    }
}
