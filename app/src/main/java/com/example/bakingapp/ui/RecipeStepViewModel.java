package com.example.bakingapp.ui;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bakingapp.domain.model.BakingRecipeSteps;

import java.util.List;

public class RecipeStepViewModel extends ViewModel {

    private List<BakingRecipeSteps> bakingRecipeSteps;
    private int currentStep = -1;

    @NonNull public final MutableLiveData<Boolean> previousButtonEnabled = new MutableLiveData<>(true);
    @NonNull public final MutableLiveData<Boolean> nextButtonEnabled = new MutableLiveData<>(true);

    @NonNull public final MutableLiveData<Boolean> replaceLiveData = new MutableLiveData<>(false);

    public void init(
            final int currentStep,
            @NonNull final List<BakingRecipeSteps> bakingRecipeSteps
    ) {
        if (this.bakingRecipeSteps == null) {
            this.currentStep = currentStep;
            this.bakingRecipeSteps = bakingRecipeSteps;

            if (currentStep == 0) previousButtonEnabled.setValue(false);
            if (currentStep == bakingRecipeSteps.size() - 1) nextButtonEnabled.setValue(false);
        }
    }

    public void nextStep() {
        if (currentStep == 0) previousButtonEnabled.setValue(true);
        if (currentStep < bakingRecipeSteps.size() - 1) {
            currentStep++;
            replaceLiveData.setValue(true);
            if (currentStep == bakingRecipeSteps.size() - 1) nextButtonEnabled.setValue(false);
        }
    }

    public void previousStep() {
        if (currentStep == bakingRecipeSteps.size() - 1) nextButtonEnabled.setValue(true);
        if (currentStep > 0) {
            currentStep--;
            replaceLiveData.setValue(true);
            if (currentStep == 0) previousButtonEnabled.setValue(false);
        }
    }

    @NonNull
    public final String getCurrentRecipeStepDescription() {
        return bakingRecipeSteps.get(currentStep).getDescription();
    }

    @NonNull
    public final String getStepImageUrl() {
        return bakingRecipeSteps.get(currentStep).getThumbnailUrl();
    }

    @NonNull
    public final String getStepShortDescription() {
        return bakingRecipeSteps.get(currentStep).getShortDescription();
    }

    public void updateSelectedStep(final int selectedStep) {
        currentStep = selectedStep;
    }

    @NonNull
    public final Uri getStepVideoUrl() {
        return Uri.parse(bakingRecipeSteps.get(currentStep).getVideoUrl());
    }
}