package com.example.bakingapp.domain.model;

import androidx.annotation.NonNull;

public class BakingRecipeSteps {

    private final int id;
    @NonNull
    private final String shortDescription;
    @NonNull
    private final String description;
    @NonNull
    private final String videoUrl;
    @NonNull
    private final String thumbnailUrl;
    private final int stepNumber;

    public BakingRecipeSteps(
            final int id,
            @NonNull final String shortDescription,
            @NonNull final String description,
            @NonNull final String videoUrl,
            @NonNull final String thumbnailUrl,
            final int stepNumber
    ) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.stepNumber = stepNumber;
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
        // TODO: investigate better way to determine if url is of type image
        if (thumbnailUrl.endsWith(".jpg") || thumbnailUrl.endsWith(".png")) return thumbnailUrl;
        return "";
    }

    public final int getStepNumber() {
        return stepNumber;
    }

    @NonNull
    public final String getShortDescriptionWithStepNumber() {
        return getStepNumber() + ".\t" + getShortDescription();
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
                ", stepNumber='" + stepNumber + '\'' +
                '}';
    }
}