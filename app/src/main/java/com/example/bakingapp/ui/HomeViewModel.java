package com.example.bakingapp.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.repository.BakingRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = HomeViewModel.class.getSimpleName();

    private BakingRepository repository;
    @NonNull private List<BakingRecipeItem> recipeItems = new ArrayList<>();
    @NonNull private final RecipeAdapter adapter = new RecipeAdapter();
    @NonNull private final CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    public final MutableLiveData<Boolean> loadingIndicator = new MutableLiveData<>(false);
    public final MutableLiveData<Boolean> showErrorText = new MutableLiveData<>(false);

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    @NonNull
    public final RecipeAdapter getAdapter() {
        return adapter;
    }

    public void setRepository(@NonNull final BakingRepository repository) {
        this.repository = repository;
        loadRecipes();
    }

    public void loadRecipes() {
        if (!repository.isRecipeLoading() && recipeItems.isEmpty()) {
            repository.setRecipeLoading(true);
            loadingIndicator.setValue(true);
            showErrorText.setValue(false);
            load();
        }
    }

    private void load() {
        disposables.add(repository.getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<BakingRecipeItem>>() {
                    @Override
                    public void onSuccess(List<BakingRecipeItem> bakingRecipeItems) {
                        Log.d(TAG, "onSuccess: " + bakingRecipeItems);
                        recipeItems = bakingRecipeItems;
                        setAdapter();

                        loadingIndicator.setValue(false);
                        showErrorText.setValue(false);
                        repository.setRecipeLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error retrieving recipes: ", e);
                        loadingIndicator.setValue(false);
                        showErrorText.setValue(true);
                        repository.setRecipeLoading(false);
                    }
                }));
    }

    private void setAdapter() {
        adapter.setRecipes(recipeItems);
        adapter.notifyDataSetChanged();
    }
}