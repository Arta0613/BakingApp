package com.example.bakingapp.ui.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bakingapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.bakingapp.util.Utils.SHARED_PREFS_KEY;
import static com.example.bakingapp.util.Utils.SHARED_PREFS_RECIPE_INGREDIENTS_KEY;

public class BakingAppWidgetListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<String> ingredientsList = new ArrayList<>();
    private final Context context;
    private final int appWidgetId;


    public BakingAppWidgetListRemoteViewsFactory(@NonNull final Context context, @NonNull final Intent intent) {
        this.context = context;
        updateIngredientsList();
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {}

    @Override
    public void onDataSetChanged() {
        updateIngredientsList();
    }

    @Override
    public void onDestroy() {}

    @Override
    public final int getCount() {
        return ingredientsList.size();
    }

    @NonNull
    @Override
    public final RemoteViews getViewAt(final int position) {
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.item_widget_ingredient);
        remoteView.setTextViewText(R.id.item_widget_ingredient_text, ingredientsList.get(position));
        return remoteView;
    }

    @Nullable
    @Override
    public final RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public final int getViewTypeCount() {
        return 1;
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final boolean hasStableIds() {
        return true;
    }

    private void updateIngredientsList() {
        final Set<String> setIngredients =
                context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
                        .getStringSet(SHARED_PREFS_RECIPE_INGREDIENTS_KEY, new HashSet<>());

        ingredientsList.clear();
        ingredientsList.addAll(setIngredients);
    }
}