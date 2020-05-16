package com.example.bakingapp.domain;

import androidx.annotation.NonNull;

import com.example.bakingapp.domain.model.BakingRecipe;

public class BakingRecipeItem {

    @NonNull private final BakingRecipe bakingRecipe;

    public BakingRecipeItem(@NonNull final BakingRecipe bakingRecipe) {
        this.bakingRecipe = bakingRecipe;
    }

    @NonNull
    public final BakingRecipe getBakingRecipe() {
        return bakingRecipe;
    }
}