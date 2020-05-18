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
import com.example.bakingapp.databinding.FragmentDetailBinding;
import com.example.bakingapp.repository.BakingRepository;
import com.example.bakingapp.util.Utils;

import java.util.Objects;

public class DetailFragment extends Fragment {

    private final static String TAG = DetailFragment.class.getSimpleName();

    private final Utils utils = new Utils();

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

        detailViewModel.init(
                Objects.requireNonNull(getBakingRepository().getSelectedRecipe()),
                (RecipeStepItemClickListener) Objects.requireNonNull(getActivity())
        );

        return binding.getRoot();
    }

    @NonNull
    private BakingRepository getBakingRepository() {
        return utils.getBakingRepository(Objects.requireNonNull(getActivity()).getApplication());
    }
}