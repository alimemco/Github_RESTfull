package com.learn.github.apiService;

import com.learn.github.model.Repo;
import com.learn.github.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<Repo>> getRepositories(@Path("username") String username);


}
