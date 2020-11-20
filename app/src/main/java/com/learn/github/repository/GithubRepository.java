package com.learn.github.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.learn.github.apiService.RetrofitClient;
import com.learn.github.model.Repo;
import com.learn.github.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepository {

    private static final String TAG = "LOG_ME";
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Repo>> repositoriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<User>> followersLiveData=new MutableLiveData<>();

    public MutableLiveData<List<User>> getFollowersLiveData() {
        return followersLiveData;
    }

    public MutableLiveData<List<Repo>> getRepositoriesLiveData() {
        return repositoriesLiveData;
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public void getUser(String username) {
        RetrofitClient.getApiClient().getApi().getUser(username)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        Log.i(TAG, "onResponse: code ="+response.code());
                        if (response.isSuccessful())
                            userLiveData.postValue(response.body());
                        else
                            userLiveData.postValue(null);

                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                        Log.i(TAG, "onFailure: "+t.getMessage());
                        userLiveData.postValue(null);
                    }
                });

    }

    public void getRepositories(String username){
        RetrofitClient.getApiClient().getApi().getRepositories(username)
                .enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Repo>> call,@NonNull  Response<List<Repo>> response) {
                        repositoriesLiveData.setValue(response.body());
                        Log.i(TAG, "onResponse: "+response.code());
                        Log.i(TAG, "onResponse size: "+response.body().size());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Repo>> call,@NonNull  Throwable t) {
                        repositoriesLiveData.setValue(null);
                        Log.i(TAG, "onResponse: "+t.getMessage());
                    }
                });
    }

    public void getUsers(String username){
        RetrofitClient.getApiClient().getApi().getFollowers(username)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call,@NonNull Response<List<User>> response) {
                        followersLiveData.setValue(response.body());
                        Log.i(TAG, "onResponse: "+response.code());
                        Log.i(TAG, "onResponse size: "+response.body().size());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        followersLiveData.setValue(null);
                        Log.i(TAG, "onResponse: "+t.getMessage());
                    }
                });
    }
}
