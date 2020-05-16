package com.example.bakingapp.repository.network;

import androidx.annotation.NonNull;

import com.example.bakingapp.data.BakingRecipeApiUseCase;
import com.example.bakingapp.domain.BakingRecipeItem;

import java.util.List;

import io.reactivex.Single;

public class BakingRemoteDataSource {

    @NonNull private final BakingRecipeApiUseCase bakingRecipeApiUseCase = new BakingRecipeApiUseCase();

    @NonNull
    public final Single<List<BakingRecipeItem>> getRecipes() {
        return bakingRecipeApiUseCase.getRecipes();
    }
}