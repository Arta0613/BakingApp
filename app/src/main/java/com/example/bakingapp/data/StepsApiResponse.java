package com.example.bakingapp.data;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class StepsApiResponse {

    private final int id;
    @NonNull private final String shortDescription;
    @NonNull private final String description;
    @NonNull private final String videoURL;
    @NonNull private final String thumbnailURL;

    public StepsApiResponse(
            final int id,
            @NonNull final String shortDescription,
            @NonNull final String description,
            @NonNull final String videoURL,
            @NonNull final String thumbnailURL
    ) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
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
        return videoURL;
    }

    @NonNull
    public final String getThumbnailUrl() {
        return thumbnailURL;
    }

    @NonNull
    @Override
    public String toString() {
        return "StepsApiResponse{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoURL + '\'' +
                ", thumbnailUrl='" + thumbnailURL + '\'' +
                '}';
    }
}