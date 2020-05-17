package com.example.bakingapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.BR;
import com.example.bakingapp.R;
import com.example.bakingapp.domain.model.BakingRecipeIngredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private List<BakingRecipeIngredients> recipeIngredients;

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_recipe_ingredient, parent, false
        );

        return new IngredientsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final IngredientsViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public final int getItemCount() {
        return recipeIngredients.size();
    }

    public void setRecipeIngredients(@NonNull final List<BakingRecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        public final ViewDataBinding binding;

        public IngredientsViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int position) {
            binding.setVariable(BR.ingredient, recipeIngredients.get(position));
            binding.executePendingBindings();
        }
    }
}