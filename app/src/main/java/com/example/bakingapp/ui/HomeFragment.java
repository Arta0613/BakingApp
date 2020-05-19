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
import androidx.test.espresso.idling.CountingIdlingResource;

import com.example.bakingapp.R;
import com.example.bakingapp.databinding.FragmentHomeBinding;
import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.util.Utils;

import java.util.Objects;

public class HomeFragment extends Fragment implements HomeItemClickListener {

    private static final String TAG = HomeFragment.class.getSimpleName();

    private final Utils utils = new Utils();
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        final FragmentHomeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(homeViewModel);

        homeViewModel.init(
                utils.getBakingRepository(Objects.requireNonNull(getActivity()).getApplication()),
                this,
                getIdlingResource()
        );

        return binding.getRoot();
    }

    @Override
    public void onItemClick(@NonNull final BakingRecipeItem bakingRecipeItem) {
        utils.getBakingRepository(
                Objects.requireNonNull(getActivity()).getApplication()
        ).setSelectedRecipe(bakingRecipeItem);

        Intent intent = new Intent(requireActivity(), DetailActivity.class);
        startActivity(intent);
    }

    @NonNull
    private CountingIdlingResource getIdlingResource() {
        return utils.getAppContainer(Objects.requireNonNull(getActivity()).getApplication()).getCountingIdlingResource();
    }
}