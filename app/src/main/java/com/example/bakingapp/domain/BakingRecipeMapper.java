package com.example.bakingapp.domain;

import androidx.annotation.NonNull;

import com.example.bakingapp.data.BakingRecipesApiResponse;
import com.example.bakingapp.data.IngredientsApiResponse;
import com.example.bakingapp.data.StepsApiResponse;
import com.example.bakingapp.domain.model.BakingRecipe;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.domain.model.BakingRecipeSteps;

import java.util.ArrayList;
import java.util.List;

public class BakingRecipeMapper {

    @NonNull
    public final BakingRecipe mapBakingRecipe(
            @NonNull final BakingRecipesApiResponse bakingRecipesApiResponse
    ) {
        return new BakingRecipe(
                bakingRecipesApiResponse.getId(),
                bakingRecipesApiResponse.getName(),
                mapBakingRecipeIngredients(bakingRecipesApiResponse.getIngredients()),
                mapBakingRecipeSteps(bakingRecipesApiResponse.getSteps()),
                bakingRecipesApiResponse.getServings(),
                bakingRecipesApiResponse.getImage()
        );
    }

    @NonNull
    public final BakingRecipeItem mapBakingRecipeItem(@NonNull final BakingRecipe bakingRecipe) {
        return new BakingRecipeItem(bakingRecipe);
    }

    @NonNull
    private List<BakingRecipeIngredients> mapBakingRecipeIngredients(
            @NonNull final List<IngredientsApiResponse> ingredientsApiResponseList
    ) {
        final List<BakingRecipeIngredients> ingredientsList = new ArrayList<>();

        for (IngredientsApiResponse ingredientsApiResponse : ingredientsApiResponseList) {
            ingredientsList.add(new BakingRecipeIngredients(
                    ingredientsApiResponse.getQuantity(),
                    ingredientsApiResponse.getMeasure(),
                    ingredientsApiResponse.getIngredient()
            ));
        }

        return ingredientsList;
    }

    @NonNull
    private List<BakingRecipeSteps> mapBakingRecipeSteps(
            @NonNull final List<StepsApiResponse> stepsApiResponseList
    ) {
        final List<BakingRecipeSteps> stepsList = new ArrayList<>();
        int stepNumber = 1;

        for (StepsApiResponse stepsApiResponse : stepsApiResponseList) {
            stepsList.add(new BakingRecipeSteps(
                    stepsApiResponse.getId(),
                    stepsApiResponse.getShortDescription(),
                    stepsApiResponse.getDescription(),
                    stepsApiResponse.getVideoUrl(),
                    stepsApiResponse.getThumbnailUrl(),
                    stepNumber++
            ));
        }

        return stepsList;
    }
}