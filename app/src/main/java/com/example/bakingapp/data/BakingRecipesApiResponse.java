package com.example.bakingapp.data;

import androidx.annotation.NonNull;

import java.util.List;

@SuppressWarnings("unused")
public class BakingRecipesApiResponse {

    private final int id;
    @NonNull private final String name;
    @NonNull private final List<IngredientsApiResponse> ingredients;
    @NonNull private final List<StepsApiResponse> steps;
    private final int servings;
    @NonNull private final String image;

    public BakingRecipesApiResponse(
            final int id,
            @NonNull final String name,
            @NonNull final List<IngredientsApiResponse> ingredients,
            @NonNull final List<StepsApiResponse> steps,
            final int servings,
            @NonNull final String image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.image = image;
    }

    public final int getId() {
        return id;
    }

    @NonNull
    public final String getName() {
        return name;
    }

    @NonNull
    public final List<IngredientsApiResponse> getIngredients() {
        return ingredients;
    }

    @NonNull
    public final List<StepsApiResponse> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    @NonNull
    public final String getImage() {
        return image;
    }

    @NonNull
    @Override
    public String toString() {
        return "\nBakingRecipesApiResponse{\n" +
                "\tid=" + id +
                ", \n\tname='" + name + '\'' +
                ", \n\tingredients=" + ingredients +
                ", \n\tsteps=" + steps +
                ", \n\tservings=" + servings +
                ", \n\timage='" + image + '\'' +
                "\n}";
    }
}