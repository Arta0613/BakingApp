package com.example.bakingapp.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.bakingapp.R;
import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.ui.widget.BakingAppWidgetProvider;
import com.example.bakingapp.ui.widget.BakingAppWidgetUpdateService;
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
                    getSelectedRecipe().getSteps()
            );
            // TODO: maybe just updating the view model is enough, set view with LiveData so no need to replace fragment
            setFragment();
        }

        setTitle(utils.getAppBarTitle(getApplication()));
        utils.setBackNavigationInAppBar(getSupportActionBar(), TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == R.id.save_recipe) {
            utils.saveSelectedRecipe(getApplicationContext());
            updateBakingWidget();
        }

        return super.onOptionsItemSelected(item);
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

    @NonNull
    private BakingRecipeItem getSelectedRecipe() {
        return Objects.requireNonNull(utils.getBakingRepository(getApplication()).getSelectedRecipe());
    }

    private void updateBakingWidget() {
        BakingAppWidgetUpdateService.updateBakingWidgetWithRecipe(getApplicationContext());

        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        final int[] appwidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), BakingAppWidgetProvider.class));

        appWidgetManager.notifyAppWidgetViewDataChanged(appwidgetIds, R.id.widget_recipe_ingredients);
    }
}