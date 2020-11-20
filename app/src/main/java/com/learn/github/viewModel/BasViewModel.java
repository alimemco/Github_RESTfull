package com.learn.github.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;

import com.learn.github.model.User;
import com.learn.github.repository.UserRepository;

public class BasViewModel extends BaseObservable {

    private LiveData<User> user;
    private UserRepository userRepo;
    private Context context;

    public BasViewModel(Context context) {
        this.context = context;
    }

    public void init(String username){

        if (user == null){
            userRepo = new UserRepository(context);
            user =  userRepo.getUser(username);
        }

    }

    public LiveData<User> getUser() {
        return user;
    }
}
