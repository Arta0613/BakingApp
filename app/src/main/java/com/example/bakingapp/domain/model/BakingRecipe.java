package com.example.bakingapp.domain.model;

import androidx.annotation.NonNull;

import java.util.List;

public class BakingRecipe {

    private final int recipeId;
    @NonNull private final String recipeName;
    @NonNull private final List<BakingRecipeIngredients> recipeIngredients;
    @NonNull private final List<BakingRecipeSteps> recipeSteps;
    private final int recipeServings;
    @NonNull private final String recipeImage;

    public BakingRecipe(
            final int recipeId,
            @NonNull final String recipeName,
            @NonNull final List<BakingRecipeIngredients> recipeIngredients,
            @NonNull final List<BakingRecipeSteps> recipeSteps,
            final int recipeServings,
            @NonNull final String recipeImage
    ) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;
        this.recipeServings = recipeServings;
        this.recipeImage = recipeImage;
    }

    public int getRecipeId() {
        return recipeId;
    }

    @NonNull
    public String getRecipeName() {
        return recipeName;
    }

    @NonNull
    public List<BakingRecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    @NonNull
    public List<BakingRecipeSteps> getRecipeSteps() {
        return recipeSteps;
    }

    public int getRecipeServings() {
        return recipeServings;
    }

    @NonNull
    public String getRecipeImage() {
        return recipeImage;
    }

    @NonNull
    @Override
    public final String toString() {
        return "\nBakingRecipe{\n" +
                "\trecipeId=" + recipeId +
                ", \n\trecipeName='" + recipeName + '\'' +
                ", \n\trecipeIngredients=" + recipeIngredients +
                ", \n\trecipeSteps=" + recipeSteps +
                ", \n\trecipeServings=" + recipeServings +
                ", \n\trecipeImage='" + recipeImage + '\'' +
                "\n}";
    }
}