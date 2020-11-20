package com.learn.github.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.learn.github.R;
import com.learn.github.databinding.ActivityFollowersListBinding;
import com.learn.github.viewModel.UserViewModel;

public class FollowersList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String username = "imanneoFighT";
        ActivityFollowersListBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_followers_list);
        binding.setViewModelUser(new UserViewModel(this,username));
        binding.executePendingBindings();
    }
}