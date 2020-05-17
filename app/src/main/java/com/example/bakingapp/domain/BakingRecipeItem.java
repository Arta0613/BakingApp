package com.example.bakingapp.domain;

import androidx.annotation.NonNull;

import com.example.bakingapp.domain.model.BakingRecipe;

public class BakingRecipeItem {

    @NonNull
    private final BakingRecipe bakingRecipe;

    public BakingRecipeItem(@NonNull final BakingRecipe bakingRecipe) {
        this.bakingRecipe = bakingRecipe;
    }

    @NonNull
    public final BakingRecipe getBakingRecipe() {
        return bakingRecipe;
    }

    @NonNull
    public final String getRecipeName() {
        return bakingRecipe.getRecipeName();
    }

    @NonNull
    public final String getRecipeImage() {
        return bakingRecipe.getRecipeImage();
    }

    @NonNull
    public final String getRecipeServings() {
        return "Serves: " + bakingRecipe.getRecipeServings();
    }
}