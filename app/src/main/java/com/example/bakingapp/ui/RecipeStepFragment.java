package com.example.bakingapp.ui;

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

import java.util.Objects;

public class RecipeStepFragment extends Fragment {

    private static final String PLAYER_POSITION = "playerPosition";

    @Nullable private final RecipeStepViewModel tabletModeViewModel;
    private FragmentRecipeStepBinding binding;
    private SimpleExoPlayer player;
    private RecipeStepViewModel viewModel;

    // required empty constructor if paramaterized constructor is used
    public RecipeStepFragment() {
        tabletModeViewModel = null;
    }

    public RecipeStepFragment(@Nullable final RecipeStepViewModel tabletModeViewModel) {
        this.tabletModeViewModel = tabletModeViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step, container, false);

        if (tabletModeViewModel != null) {
            viewModel = tabletModeViewModel;
        } else {
            viewModel = new ViewModelProvider(requireActivity()).get(RecipeStepViewModel.class);
        }

        if (savedInstanceState != null) {
            viewModel.setPlayerPosition(savedInstanceState.getLong(PLAYER_POSITION, 0));
        }

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        player = new SimpleExoPlayer.Builder(Objects.requireNonNull(getContext())).build();
        if (!viewModel.getStepVideoUrl().toString().isEmpty()) {
            setPlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            if (viewModel.getPlayerPosition() != -1) { // TODO: better way to reset player position on tablets
                viewModel.setPlayerPosition(player.getCurrentPosition());
            }
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(PLAYER_POSITION, viewModel.getPlayerPosition());
    }

    private void setPlayer() {
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

        final long playerPosition = viewModel.getPlayerPosition();
        player.seekTo(playerPosition >=0 ? playerPosition : 1);
        viewModel.setPlayerPosition(0);
    }
}