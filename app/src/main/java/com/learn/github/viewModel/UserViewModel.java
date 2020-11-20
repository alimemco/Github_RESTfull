package com.learn.github.viewModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learn.github.BR;
import com.learn.github.model.User;
import com.learn.github.repository.GithubRepository;
import com.learn.github.view.FollowersList;
import com.learn.github.view.ReposListActivity;
import com.learn.github.view.adapter.FollowerAdapter;

import java.util.List;

public class UserViewModel extends BaseObservable {

    private static final String TAG = "LOG_ME";
    private boolean isLoading;

    private final MutableLiveData<List<User>> followersLiveData=new MutableLiveData<>();
    private User user;
    private String value;
    private Context mContext;
    private GithubRepository githubRepository;
    private String username = "imanneoFighT";

    public UserViewModel(Context mContext, String username) {
        this(mContext);
    }


    public UserViewModel(Context mContext) {
        this.mContext = mContext;
        githubRepository = new GithubRepository();

        githubRepository.getUserLiveData().observe((LifecycleOwner) mContext, user -> {
            setLoading(false);
            setUser(user);
            if (user != null) {
                Toast.makeText(mContext, "Repository Count : "+user.getPublicRepos(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        githubRepository.getFollowersLiveData().observe((LifecycleOwner) mContext, users -> {

            followersLiveData.setValue(users);
            if (users != null) {
                Toast.makeText(mContext, "followers Count : "+users.size(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Not Found", Toast.LENGTH_SHORT).show();
            }
        });
        githubRepository.getUsers(username);
    }

    @Bindable
    public MutableLiveData<List<User>> getFollowersLiveData() {
        return followersLiveData;
    }

    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }


    public void onButtonSearchClicked() {
        if (value != null) {
            setLoading(true);
            githubRepository.getUser(value);
        } else {
            Toast.makeText(mContext, "empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemClick(){
        if (user != null){
            Intent intent = new Intent(mContext , ReposListActivity.class);
            intent.putExtra("username",user.getLogin());
            mContext.startActivity(intent);

        }else{
        Toast.makeText(mContext, "User Error", Toast.LENGTH_SHORT).show();
    }}

    public void onItemLongClick(){
        if (user != null){
            Intent intent = new Intent(mContext , FollowersList.class);
            intent.putExtra("username",user.getLogin());
            mContext.startActivity(intent);


        }else{
            Toast.makeText(mContext, "User Error", Toast.LENGTH_SHORT).show();
        }}

    @BindingAdapter("setRepositories")
    public static void setRepository(TextView textView , int count){
        textView.setText(String.format("%s repositories",count));
        Log.i(TAG, "setRepository: "+count);
    }

    @BindingAdapter("setFollowers")
    public static void setFollowers(RecyclerView recyclerView , MutableLiveData<List<User>> followersLiveData){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        followersLiveData.observe((LifecycleOwner) recyclerView.getContext(), users -> {
            recyclerView.setAdapter(new FollowerAdapter(users));
        });
    }




    @BindingAdapter(value = {"imageUrl","holder"})
    public static void loadImage(ImageView view,String imageUrl, Drawable holder) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .placeholder(holder).apply(new RequestOptions().circleCrop())
                .into(view);
    }


}
