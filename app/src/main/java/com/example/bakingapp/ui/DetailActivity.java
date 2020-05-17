package com.example.bakingapp.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bakingapp.HomeApplication;
import com.example.bakingapp.R;
import com.example.bakingapp.di.AppContainer;
import com.example.bakingapp.domain.BakingRecipeItem;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setAppbarTitle();
        setBackNavigationInAppBar();
    }

    private void setAppbarTitle() {
        final AppContainer appContainer = ((HomeApplication) getApplication()).getAppContainer();
        final BakingRecipeItem recipeItem = appContainer.getBakingRepository().getSelectedRecipe();

        setTitle(Objects.requireNonNull(recipeItem).getRecipeName());
    }

    private void setBackNavigationInAppBar() {
        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Log.e(TAG, "Error while setting UP action", e);
        }
    }
}