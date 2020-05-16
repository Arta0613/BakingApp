package com.example.bakingapp.data;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class IngredientsApiResponse {

    private final double quantity;
    @NonNull private final String measure;
    @NonNull private final String ingredient;

    public IngredientsApiResponse(
            final int quantity,
            @NonNull final String measure,
            @NonNull final String ingredient
    ) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public final double getQuantity() {
        return quantity;
    }

    @NonNull
    public final String getMeasure() {
        return measure;
    }

    @NonNull
    public final String getIngredient() {
        return ingredient;
    }

    @NonNull
    @Override
    public String toString() {
        return "IngredientsApiResponse{" +
                "quantity=" + quantity +
                ", measure='" + measure + '\'' +
                ", ingredient='" + ingredient + '\'' +
                '}';
    }
}