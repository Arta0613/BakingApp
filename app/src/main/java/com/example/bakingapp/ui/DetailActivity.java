package com.example.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bakingapp.R;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.util.Utils;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements RecipeStepItemClickListener {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private final Utils utils = new Utils();
    private boolean twoPane = false;
    private RecipeStepViewModel twoPaneRecipeStepsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        twoPane = findViewById(R.id.step_details_tablet) != null;
        if (twoPane) {
            twoPaneRecipeStepsViewModel = new ViewModelProvider(this).get(RecipeStepViewModel.class);
            twoPaneRecipeStepsViewModel.init(
                    0,
                    Objects.requireNonNull(utils.getBakingRepository(getApplication()).getSelectedRecipe()).getSteps()
            );
            // TODO: maybe just updating the view model is enough, set view with LiveData so no need to replace fragment
            setFragment();
        }

        setTitle(utils.getAppBarTitle(getApplication()));
        utils.setBackNavigationInAppBar(getSupportActionBar(), TAG);
    }

    @Override
    public void stepClicked(final int step) {
        if (twoPane) {
            twoPaneRecipeStepsViewModel.updateSelectedStep(step);
            twoPaneRecipeStepsViewModel.setPlayerPosition(-1); // simple way to reset position in tablet mode; TODO: investigate better way
            setFragment();
        } else {
            Intent intent = new Intent(this, RecipeStepActivity.class);
            intent.putExtra(BakingRecipeIngredients.STEP_POSITION, step);
            startActivity(intent);
        }
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.step_details_tablet, new RecipeStepFragment(twoPaneRecipeStepsViewModel)
        ).commit();
    }
}