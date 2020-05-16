package com.example.bakingapp.domain.model;

import androidx.annotation.NonNull;

public class BakingRecipeSteps {

    private final int id;
    @NonNull private final String shortDescription;
    @NonNull private final String description;
    @NonNull private final String videoUrl;
    @NonNull private final String thumbnailUrl;

    public BakingRecipeSteps(
            final int id,
            @NonNull final String shortDescription,
            @NonNull final String description,
            @NonNull final String videoUrl,
            @NonNull final String thumbnailUrl
    ) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public final int getId() {
        return id;
    }

    @NonNull
    public final String getShortDescription() {
        return shortDescription;
    }

    @NonNull
    public final String getDescription() {
        return description;
    }

    @NonNull
    public final String getVideoUrl() {
        return videoUrl;
    }

    @NonNull
    public final String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "BakingRecipeSteps{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}