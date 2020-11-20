package com.learn.github.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.learn.github.apiService.ApiService;
import com.learn.github.apiService.RetrofitClient;
import com.learn.github.database.MyDatabase;
import com.learn.github.database.dao.UserDao;
import com.learn.github.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private UserDao userDao;
    private Executor executor;
    private ApiService apiService;

    private Context context;

    public UserRepository(Context context) {
        this.userDao = MyDatabase.getInstance(context).userDao();
        this.executor = Executors.newSingleThreadExecutor();
        this.apiService = RetrofitClient.getApiClient().getApi();
        this.context = context;
    }

   public LiveData<User> getUser(String username){
        refreshUser(username);
        return userDao.load(username);

   }

    private void refreshUser(String username) {
        executor.execute(() -> {

            //do work
            apiService.getUser(username).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    Toast.makeText(context, ""+response.code(), Toast.LENGTH_SHORT).show();
                    executor.execute(() -> {
                        User user = response.body();
                        userDao.save(user);
                    });
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        });
    }
}
