package com.example.bakingapp.domain.model;

import androidx.annotation.NonNull;

public class BakingRecipeIngredients {

    @NonNull public static final String STEP_POSITION = "step_position";

    private final double recipeQuantity;
    @NonNull private final String recipeMeasure;
    @NonNull private final String recipeIngredient;

    public BakingRecipeIngredients(
            final double recipeQuantity,
            @NonNull final String recipeMeasure,
            @NonNull final String recipeIngredient
    ) {
        this.recipeQuantity = recipeQuantity;
        this.recipeMeasure = recipeMeasure;
        this.recipeIngredient = recipeIngredient;
    }

    public final double getRecipeQuantity() {
        return recipeQuantity;
    }

    @NonNull
    public final String getRecipeMeasure() {
        return recipeMeasure;
    }

    @NonNull
    public final String getRecipeIngredient() {
        return recipeIngredient;
    }

    @NonNull
    public final String getRecipeAmount() {
        return getRecipeQuantity() + " " + getRecipeMeasure();
    }

    @NonNull
    @Override
    public final String toString() {
        return "BakingRecipeIngredients{" +
                "recipeQuantity=" + recipeQuantity +
                ", recipeMeasure='" + recipeMeasure + '\'' +
                ", recipeIngredient='" + recipeIngredient + '\'' +
                '}';
    }
}