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

public class RecipeStepFragment extends Fragment {

    @Nullable private final RecipeStepViewModel tabletModeViewModel;

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
        final FragmentRecipeStepBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step, container, false);

        final RecipeStepViewModel viewModel;
        if (tabletModeViewModel != null) {
            viewModel = tabletModeViewModel;
        } else {
            viewModel = new ViewModelProvider(requireActivity()).get(RecipeStepViewModel.class);
        }

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}