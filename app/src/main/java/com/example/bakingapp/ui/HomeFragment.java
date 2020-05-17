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

import com.example.bakingapp.HomeApplication;
import com.example.bakingapp.R;
import com.example.bakingapp.databinding.FragmentHomeBinding;
import com.example.bakingapp.repository.BakingRepository;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private static final String TAG = HomeFragment.class.getSimpleName();

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

        homeViewModel.setRepository(getBakingRepository());

        return binding.getRoot();
    }

    @NonNull
    private BakingRepository getBakingRepository() {
        final HomeApplication homeApplication =
                ((HomeApplication) Objects.requireNonNull(getActivity()).getApplicationContext());

        return homeApplication.getAppContainer().getBakingRepository();
    }
}