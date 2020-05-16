package com.example.bakingapp.network;

import com.example.bakingapp.data.BakingRecipesApiResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BakingRecipesApi {

    @GET("baking.json")
    Observable<List<BakingRecipesApiResponse>> getRecipes();
}