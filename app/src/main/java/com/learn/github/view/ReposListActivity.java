package com.learn.github.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.learn.github.R;
import com.learn.github.databinding.ActivityReposListBinding;
import com.learn.github.viewModel.ReposViewModel;

public class ReposListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_repos_list);

        String username = getIntent().getStringExtra("username");
        ActivityReposListBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_repos_list);
        binding.setReposViewModel(new ReposViewModel(this , username));
        binding.executePendingBindings();
    }
}