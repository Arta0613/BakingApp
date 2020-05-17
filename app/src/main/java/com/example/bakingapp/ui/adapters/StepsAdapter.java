package com.example.bakingapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.BR;
import com.example.bakingapp.R;
import com.example.bakingapp.domain.model.BakingRecipeSteps;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    private List<BakingRecipeSteps> steps;

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_recipe_step, parent, false
        );

        return new StepsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final StepsViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public final int getItemCount() {
        return steps.size();
    }

    public void setRecipeSteps(@NonNull final List<BakingRecipeSteps> steps) {
        this.steps = steps;
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        public final ViewDataBinding binding;

        public StepsViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int position) {
            binding.setVariable(BR.step, steps.get(position));
            binding.executePendingBindings();
        }
    }
}