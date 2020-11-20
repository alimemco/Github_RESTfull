package com.learn.github.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.learn.github.R;
import com.learn.github.databinding.ActivityUserBinding;
import com.learn.github.viewModel.BasViewModel;

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "LOG_ME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_user);
        BasViewModel basViewModel = new BasViewModel(this);
        basViewModel.init("alimemco");
        basViewModel.getUser().observe(this , user -> {
            if (user != null) {
                Toast.makeText(this, user.getLogin(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onCreate: "+user.getLogin());
            }
            else {
                Toast.makeText(this, "user is null", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onCreate: user is null");
            }
        });
        binding.setViewModelBas(basViewModel);
        binding.executePendingBindings();
    }
}