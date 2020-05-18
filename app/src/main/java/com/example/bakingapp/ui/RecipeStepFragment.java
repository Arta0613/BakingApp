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

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        final FragmentRecipeStepBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step, container, false);

        final RecipeStepViewModel recipeStepViewModel = new ViewModelProvider(requireActivity()).get(RecipeStepViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(recipeStepViewModel);

        return binding.getRoot();
    }
}