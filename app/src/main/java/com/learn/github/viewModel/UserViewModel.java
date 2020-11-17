package com.learn.github.viewModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learn.github.BR;
import com.learn.github.model.User;
import com.learn.github.repository.GithubRepository;
import com.learn.github.view.ReposListActivity;

public class UserViewModel extends BaseObservable {

    private static final String TAG = "LOG_ME";
    private boolean isLoading;

    private User user;
    private Context mContext;
    private String value;
    private GithubRepository githubRepository;

    private UserViewModel() {
    }

    public UserViewModel(Context mContext) {
        this.mContext = mContext;
        githubRepository = new GithubRepository();
        githubRepository.getUserMutableLiveData().observe((LifecycleOwner) mContext, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                setLoading(false);
                setUser(user);
                if (user != null) {
                    Toast.makeText(mContext, "Repos count : "+user.getPublicRepos(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    @BindingAdapter("setRepositories")
    public static void setRepository(TextView textView , int count){
        textView.setText(String.format("%s repositories",count));
        Log.i(TAG, "setRepository: "+count);
    }




    @BindingAdapter(value = {"imageUrl","holder"})
    public static void loadImage(ImageView view,String imageUrl, Drawable holder) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().circleCrop())
                .placeholder(holder).apply(new RequestOptions().circleCrop())
                .into(view);
    }


}
