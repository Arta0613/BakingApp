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

    public void init(@NonNull final BakingRecipeItem selectedRecipe) {
        if (this.selectedRecipe == null) {
            this.selectedRecipe = selectedRecipe;
            setAdapters();
        }
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