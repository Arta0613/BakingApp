package com.example.bakingapp.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import com.example.bakingapp.R;
import com.example.bakingapp.ui.HomeActivity;

import static com.example.bakingapp.util.Utils.SHARED_PREFS_KEY;
import static com.example.bakingapp.util.Utils.SHARED_PREFS_RECIPE_NAME_KEY;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    // Called when new widget is added or every update interval
    @Override
    public void onUpdate(@NonNull final Context context, @NonNull final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        updateAllWidgets(context, appWidgetManager, appWidgetIds);
    }

    public static void updateAppWidgets(@NonNull final Context context, @NonNull final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        updateAllWidgets(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(@NonNull final Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(@NonNull final Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void updateAllWidgets(@NonNull final Context context, @NonNull final AppWidgetManager appWidgetManager, @NonNull final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @NonNull
    private static PendingIntent getPendingIntent(@NonNull final Context context) {
        // TODO: Potentially pass in BakingRecipe in bundle and deeplink to detail activity of selected recipe
        final Intent intent = new Intent(context, HomeActivity.class);

        return PendingIntent.getActivity(context, 0, intent, 0);
    }

    private static void updateAppWidget(@NonNull final Context context, @NonNull final AppWidgetManager appWidgetManager, final int appWidgetId) {
        // Construct the RemoteViews object
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);

        // set the title
        final CharSequence widgetText =
                context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE).getString(
                        SHARED_PREFS_RECIPE_NAME_KEY, context.getString(R.string.recipe_name)
                );

        remoteView.setTextViewText(R.id.widget_recipe_name, widgetText);
        remoteView.setOnClickPendingIntent(R.id.baking_app_widget, getPendingIntent(context));

        // Set the listview
        final Intent intent = new Intent(context, BakingAppWidgetListViewService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        remoteView.setRemoteAdapter(R.id.widget_recipe_ingredients, intent);
        remoteView.setEmptyView(R.id.widget_recipe_ingredients, R.id.empty_widget_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteView);
    }
}