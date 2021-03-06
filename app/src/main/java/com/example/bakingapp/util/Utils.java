package com.example.bakingapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.example.bakingapp.HomeApplication;
import com.example.bakingapp.di.AppContainer;
import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.repository.BakingRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Utils {

    @NonNull public static final String SHARED_PREFS_KEY = "BakingApp";
    @NonNull public static final String SHARED_PREFS_RECIPE_NAME_KEY = "RecipeName";
    @NonNull public static final String SHARED_PREFS_RECIPE_INGREDIENTS_KEY = "RecipeIngredients";

    @NonNull
    public final String getAppBarTitle(@NonNull final Context context) {
        final BakingRecipeItem recipeItem = getBakingRepository(context).getSelectedRecipe();

        return Objects.requireNonNull(recipeItem).getRecipeName();
    }

    @NonNull
    public final BakingRepository getBakingRepository(@NonNull final Context context) {
        return getAppContainer(context).getBakingRepository();
    }

    @NonNull
    public final AppContainer getAppContainer(@NonNull final Context context) {
        return ((HomeApplication) context).getAppContainer();
    }

    public void setBackNavigationInAppBar(
            @Nullable final ActionBar actionBar, @NonNull final String tag
    ) {
        try {
            Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(tag, "Error while setting UP action", e);
        }
    }

    public void saveSelectedRecipe(@NonNull final Context context) {
        final SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        final BakingRecipeItem bakingRecipeItem = getBakingRepository(context).getSelectedRecipe();
        final Set<String> ingredients = new HashSet<>();

        for (BakingRecipeIngredients ingredient : Objects.requireNonNull(bakingRecipeItem).getIngredients()) {
            ingredients.add(ingredient.getRecipeIngredient());
        }

        editor.putString(SHARED_PREFS_RECIPE_NAME_KEY, Objects.requireNonNull(bakingRecipeItem).getRecipeName());
        editor.putStringSet(SHARED_PREFS_RECIPE_INGREDIENTS_KEY, ingredients);

        editor.apply();
    }
}