package com.learn.github.viewModel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.github.model.Repo;
import com.learn.github.repository.GithubRepository;
import com.learn.github.view.ReposAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReposViewModel extends BaseObservable {

    private GithubRepository repository;
    private Context mContext;
    private MutableLiveData<List<ReposViewModel>> viewModelListMutableLiveData = new MutableLiveData<>();
    private Repo repo;

    @Bindable
    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public MutableLiveData<List<ReposViewModel>> getViewModelListMutableLiveData() {
        return viewModelListMutableLiveData;
    }

    private ReposViewModel(Repo repo) {
        this.repo = repo;
    }

    public ReposViewModel(Context context , String username) {
        this.mContext = context;
        repository = new GithubRepository();
        repository.getReposListMutableLiveData().observe((LifecycleOwner) mContext, repos -> {
            List<ReposViewModel> list = new ArrayList<>();
            for (int i = 0; i < repos.size(); i++) {
                list.add(new ReposViewModel(repos.get(i)));
            }
            viewModelListMutableLiveData.setValue(list);

        });
        repository.getRepository(username);

    }

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView ,MutableLiveData<List<ReposViewModel>> reposListMutableLiveData ){

        reposListMutableLiveData.observe((LifecycleOwner) recyclerView.getContext(), new Observer<List<ReposViewModel>>() {
            @Override
            public void onChanged(List<ReposViewModel> reposViewModels) {
                recyclerView.setAdapter(new ReposAdapter(reposViewModels));
            }
        });

    }
}
