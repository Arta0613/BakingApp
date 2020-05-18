package com.example.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bakingapp.HomeApplication;
import com.example.bakingapp.R;
import com.example.bakingapp.databinding.FragmentDetailBinding;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;
import com.example.bakingapp.repository.BakingRepository;

import java.util.Objects;

public class DetailFragment extends Fragment implements RecipeStepItemClickListener {

    private final static String TAG = DetailFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        final FragmentDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        DetailViewModel detailViewModel = new ViewModelProvider(requireActivity()).get(DetailViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(detailViewModel);

        detailViewModel.init(Objects.requireNonNull(getBakingRepository().getSelectedRecipe()), this);

        return binding.getRoot();
    }

    @NonNull
    private BakingRepository getBakingRepository() {
        final HomeApplication homeApplication =
                ((HomeApplication) Objects.requireNonNull(getActivity()).getApplication());

        return homeApplication.getAppContainer().getBakingRepository();
    }

    @Override
    public void stepClicked(final int position) {
        Intent intent = new Intent(requireActivity(), RecipeStepActivity.class);
        intent.putExtra(BakingRecipeIngredients.STEP_POSITION, position);
        startActivity(intent);
    }
}