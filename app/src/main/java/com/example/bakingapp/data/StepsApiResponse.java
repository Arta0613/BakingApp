package com.example.bakingapp.data;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class StepsApiResponse {

    private final int id;
    @NonNull private final String shortDescription;
    @NonNull private final String description;
    @NonNull private final String videoUrl;
    @NonNull private final String thumbnailUrl;

    public StepsApiResponse(
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
        return "StepsApiResponse{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}