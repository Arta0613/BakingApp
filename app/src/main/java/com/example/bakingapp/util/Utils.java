package com.example.bakingapp.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.example.bakingapp.HomeApplication;
import com.example.bakingapp.di.AppContainer;
import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.repository.BakingRepository;

import java.util.Objects;

public class Utils {

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
}