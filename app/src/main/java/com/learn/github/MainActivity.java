package com.learn.github;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.learn.github.databinding.ActivityMainBinding;
import com.learn.github.viewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_main );
        binding.setUserViewModel(new UserViewModel(this));
        binding.executePendingBindings();
    }
}