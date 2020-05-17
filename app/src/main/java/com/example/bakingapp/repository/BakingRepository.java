package com.example.bakingapp.repository;

import androidx.annotation.NonNull;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.repository.network.BakingRemoteDataSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Single;

public class BakingRepository {

    @NonNull private final BakingRemoteDataSource bakingRemoteDataSource;
    @NonNull private final AtomicBoolean recipeLoading = new AtomicBoolean(false);

    public BakingRepository(@NonNull final BakingRemoteDataSource bakingRemoteDataSource) {
        this.bakingRemoteDataSource = bakingRemoteDataSource;
    }

    @NonNull
    public final Single<List<BakingRecipeItem>> getRecipes() {
        return bakingRemoteDataSource.getRecipes();
    }

    public final boolean isRecipeLoading() {
        return recipeLoading.get();
    }

    public void setRecipeLoading(final boolean recipeLoading) {
        this.recipeLoading.set(recipeLoading);
    }
}