package com.learn.github.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.learn.github.apiService.RetrofitClient;
import com.learn.github.model.Repo;
import com.learn.github.model.User;

import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepository {

    private static final String TAG = "LOG_ME";
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Repo>> reposListMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Repo>> getReposListMutableLiveData() {
        return reposListMutableLiveData;
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void getUser(String username) {
        RetrofitClient.getApiClient().getApi().getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        Log.i(TAG, "onResponse: code ="+response.code());
                        if (response.isSuccessful())
                            userMutableLiveData.postValue(response.body());
                        else
                            userMutableLiveData.postValue(null);

                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: "+t.getMessage());
                        userMutableLiveData.postValue(null);
                    }
                });

    }

    public void getRepository(String username){
        RetrofitClient.getApiClient().getApi().getRepositories(username)
                .enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Repo>> call,@NonNull  Response<List<Repo>> response) {
                        reposListMutableLiveData.setValue(response.body());
                        Log.i(TAG, "onResponse: "+response.code());
                        Log.i(TAG, "onResponse size: "+response.body().size());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Repo>> call,@NonNull  Throwable t) {
                        reposListMutableLiveData.setValue(null);
                        Log.i(TAG, "onResponse: "+t.getMessage());
                    }
                });
    }
}
