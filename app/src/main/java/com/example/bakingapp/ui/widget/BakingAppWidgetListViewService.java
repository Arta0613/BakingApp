package com.example.bakingapp.ui.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;

public class BakingAppWidgetListViewService extends RemoteViewsService {

    @NonNull
    @Override
    public final RemoteViewsFactory onGetViewFactory(final Intent intent) {
        return new BakingAppWidgetListRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}