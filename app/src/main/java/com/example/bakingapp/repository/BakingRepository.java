package com.example.bakingapp.repository;

import androidx.annotation.NonNull;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.repository.network.BakingRemoteDataSource;

import java.util.List;

import io.reactivex.Single;

public class BakingRepository {

    @NonNull private final BakingRemoteDataSource bakingRemoteDataSource;

    public BakingRepository(@NonNull final BakingRemoteDataSource bakingRemoteDataSource) {
        this.bakingRemoteDataSource = bakingRemoteDataSource;
    }

    @NonNull
    public final Single<List<BakingRecipeItem>> getRecipes() {
        return bakingRemoteDataSource.getRecipes();
    }
}