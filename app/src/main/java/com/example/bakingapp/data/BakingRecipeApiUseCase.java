package com.example.bakingapp.data;

import androidx.annotation.NonNull;

import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.domain.BakingRecipeMapper;
import com.example.bakingapp.network.BakingRecipeServiceGenerator;
import com.example.bakingapp.network.BakingRecipesApi;

import java.util.List;

import io.reactivex.Single;

public class BakingRecipeApiUseCase {

    @NonNull private final BakingRecipeMapper bakingRecipeMapper = new BakingRecipeMapper();

    @NonNull
    private final BakingRecipesApi bakingRecipesApi =
            new BakingRecipeServiceGenerator().createBakingRecipeApiService(BakingRecipesApi.class);

    @NonNull
    public final Single<List<BakingRecipeItem>> getRecipes() {
        return bakingRecipesApi.getRecipes()
                .flatMapIterable(bakingRecipesApiResponses -> bakingRecipesApiResponses)
                .map(bakingRecipeMapper::mapBakingRecipe)
                .map(bakingRecipeMapper::mapBakingRecipeItem)
                .toList();
    }
}