package com.example.bakingapp.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BakingAppWidgetUpdateService extends IntentService {

    private static final String UPDATE_BAKING_WIDGET = "com.example.bakingapp.ui.widget.update_baking_widget";

    public BakingAppWidgetUpdateService() {
        super(BakingAppWidgetUpdateService.class.getSimpleName());
    }

    public static void updateBakingWidgetWithRecipe(@NonNull final Context context) {
        Intent intent = new Intent(context, BakingAppWidgetUpdateService.class);
        intent.setAction(UPDATE_BAKING_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (UPDATE_BAKING_WIDGET.equals(action)) {
                handleBakingWidgetUpdate();
            }
        }
    }

    private void handleBakingWidgetUpdate() {
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        final int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidgetProvider.class));

        BakingAppWidgetProvider.updateAppWidgets(this, appWidgetManager, appWidgetIds);
    }
}