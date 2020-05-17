package com.example.bakingapp.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.repository.network.BakingRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class BakingRepository {

    @NonNull private final BakingRemoteDataSource bakingRemoteDataSource;
    @NonNull private List<BakingRecipeItem> recipeItems = new ArrayList<>();
    @Nullable private BakingRecipeItem selectedRecipe;

    public BakingRepository(@NonNull final BakingRemoteDataSource bakingRemoteDataSource) {
        this.bakingRemoteDataSource = bakingRemoteDataSource;
    }

    @NonNull
    public final Single<List<BakingRecipeItem>> getRecipes() {
        if (recipeItems.isEmpty()) {
            return bakingRemoteDataSource.getRecipes();
        } else {
            return Single.just(recipeItems);
        }
    }

    @NonNull
    public final List<BakingRecipeItem> getLoadedRecipes() {
        return recipeItems;
    }

    public void setRecipeItems(@NonNull final List<BakingRecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }

    @Nullable
    public final BakingRecipeItem getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(@NonNull final BakingRecipeItem selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }
}