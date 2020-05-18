package com.example.bakingapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.bakingapp.R;
import com.example.bakingapp.databinding.ActivityRecipeStepBinding;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.repository.BakingRepository;
import com.example.bakingapp.util.Utils;

import java.util.Objects;

public class RecipeStepActivity extends AppCompatActivity {

    @NonNull private static final String TAG = RecipeStepActivity.class.getSimpleName();

    private final Utils utils = new Utils();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int stepPosition = getSelectedRecipeStepPosition();

        setTitle(utils.getAppBarTitle(getApplication()));
        utils.setBackNavigationInAppBar(getSupportActionBar(), TAG);

        final ActivityRecipeStepBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_recipe_step);

        final RecipeStepViewModel recipeStepViewModel = new ViewModelProvider(this).get(RecipeStepViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(recipeStepViewModel);

        recipeStepViewModel.init(
                stepPosition,
                Objects.requireNonNull(getBakingRepository().getSelectedRecipe()).getSteps()
        );

        setFragment();

        recipeStepViewModel.replaceLiveData.observe(this, replace -> setFragment());
    }

    private void setFragment() {
        // TODO: Maybe change to single instance of fragment and update currentStep before replacing
        getSupportFragmentManager().beginTransaction().replace(
                R.id.step_details, new RecipeStepFragment(null)
        ).commit();
    }

    @NonNull
    private BakingRepository getBakingRepository() {
        return utils.getBakingRepository(getApplication());
    }

    private int getSelectedRecipeStepPosition() {
        return getIntent().getIntExtra(BakingRecipeIngredients.STEP_POSITION, 0);
    }
}