package com.example.bakingapp.di;

import androidx.annotation.NonNull;

import com.example.bakingapp.repository.BakingRepository;
import com.example.bakingapp.repository.network.BakingRemoteDataSource;

// Container of objects shared across the whole app
public class AppContainer {

    @NonNull
    private final BakingRemoteDataSource bakingRemoteDataSource = new BakingRemoteDataSource();

    @NonNull
    private final BakingRepository bakingRepository = new BakingRepository(bakingRemoteDataSource);

    @NonNull
    public BakingRepository getBakingRepository() {
        return bakingRepository;
    }
}