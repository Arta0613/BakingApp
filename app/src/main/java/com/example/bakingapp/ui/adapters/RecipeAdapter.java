package com.example.bakingapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingapp.BR;
import com.example.bakingapp.R;
import com.example.bakingapp.domain.BakingRecipeItem;
import com.example.bakingapp.ui.HomeItemClickListener;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<BakingRecipeItem> recipes;
    private HomeItemClickListener homeItemClickListener;

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_recipe, parent, false
        );

        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecipeViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return recipes == null ? 0 : recipes.size();
    }

    public void setRecipes(@NonNull final List<BakingRecipeItem> recipes) {
        this.recipes = recipes;
    }

    public void setHomeItemClickListener(@NonNull final HomeItemClickListener homeItemClickListener) {
        this.homeItemClickListener = homeItemClickListener;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        public final ViewDataBinding binding;

        public RecipeViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(final int position) {
            binding.getRoot().setOnClickListener(l -> homeItemClickListener.onItemClick(recipes.get(position)));
            binding.setVariable(BR.recipeItem, recipes.get(position));
            binding.executePendingBindings();
        }
    }
}