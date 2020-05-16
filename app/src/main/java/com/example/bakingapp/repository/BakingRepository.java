package com.example.bakingapp.repository;

import androidx.annotation.NonNull;

import com.example.bakingapp.repository.network.BakingRemoteDataSource;

public class BakingRepository {

    @NonNull
    private final BakingRemoteDataSource bakingRemoteDataSource;

    public BakingRepository(@NonNull final BakingRemoteDataSource bakingRemoteDataSource) {
        this.bakingRemoteDataSource = bakingRemoteDataSource;
    }
}