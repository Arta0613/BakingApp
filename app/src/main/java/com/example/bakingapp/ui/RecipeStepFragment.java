package com.example.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bakingapp.R;
import com.example.bakingapp.databinding.FragmentRecipeStepBinding;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeStepFragment extends Fragment {

    @Nullable private final RecipeStepViewModel tabletModeViewModel;
    private SimpleExoPlayer player;

    // required empty constructor if paramaterized constructor is used
    public RecipeStepFragment() {
        tabletModeViewModel = null;
    }

    public RecipeStepFragment(@Nullable final RecipeStepViewModel tabletModeViewModel) {
        this.tabletModeViewModel = tabletModeViewModel;
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        player = new SimpleExoPlayer.Builder(context).build();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        final FragmentRecipeStepBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step, container, false);

        final RecipeStepViewModel viewModel;
        if (tabletModeViewModel != null) {
            viewModel = tabletModeViewModel;
        } else {
            viewModel = new ViewModelProvider(requireActivity()).get(RecipeStepViewModel.class);
        }

        if (!viewModel.getStepVideoUrl().toString().isEmpty()) {
            setPlayer(binding, viewModel);
        }

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        player.release();
    }

    private void setPlayer(
            @NonNull final FragmentRecipeStepBinding binding,
            @NonNull final RecipeStepViewModel viewModel
    ) {
        final PlayerView playerView = binding.getRoot().findViewById(R.id.player);
        playerView.setVisibility(View.VISIBLE);
        playerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded.
        final DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                requireActivity(),
                Util.getUserAgent(requireActivity(), getString(R.string.app_name))
        );
        // This is the MediaSource representing the media to be played.
        final MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(viewModel.getStepVideoUrl());

        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        player.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);

        player.prepare(videoSource);
    }
}