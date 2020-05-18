package com.example.bakingapp.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.ui.adapters.IngredientsAdapter;
import com.example.bakingapp.ui.adapters.StepsAdapter;

public class DetailViewModel extends ViewModel {

    @NonNull private final IngredientsAdapter ingredientsAdapter = new IngredientsAdapter();
    @NonNull private final StepsAdapter stepsAdapter = new StepsAdapter();
    private BakingRecipeItem selectedRecipe;

    public void init(
            @NonNull final BakingRecipeItem selectedRecipe,
            @NonNull final RecipeStepItemClickListener recipeStepItemClickListener
    ) {
        if (this.selectedRecipe == null) {
            this.selectedRecipe = selectedRecipe;
            setAdapters();
        }
        // As the fragment is implementing the interface, we want to re-set this as the fragment
        // can be destroyed at any point causing the app to crash when the interface is called
        setStepClickListener(recipeStepItemClickListener);
    }

    public void setStepClickListener(@NonNull final RecipeStepItemClickListener recipeStepItemClickListener) {
        stepsAdapter.setStepClickListener(recipeStepItemClickListener);
    }

    @NonNull
    public IngredientsAdapter getIngredientsAdapter() {
        return ingredientsAdapter;
    }

    @NonNull
    public StepsAdapter getStepsAdapter() {
        return stepsAdapter;
    }

    private void setAdapters() {
        ingredientsAdapter.setRecipeIngredients(selectedRecipe.getIngredients());
        ingredientsAdapter.notifyDataSetChanged();
        stepsAdapter.setRecipeSteps(selectedRecipe.getSteps());
        stepsAdapter.notifyDataSetChanged();
    }
}