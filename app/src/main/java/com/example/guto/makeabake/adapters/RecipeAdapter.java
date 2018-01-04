package com.example.guto.makeabake.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guto.makeabake.R;
import com.example.guto.makeabake.data.ImageAssets;
import com.example.guto.makeabake.model.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUTO on 02/01/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{
    Context context;
    private ArrayList<RecipeModel> recipeModels;
    private ArrayList<Integer> mRecipeDrawables = (ArrayList<Integer>) ImageAssets.getImagesCardView();

    public RecipeAdapter( ArrayList<RecipeModel> recipeModels) {
        this.recipeModels = recipeModels;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.textNameRecipe.setText(recipeModels.get(position).getName());
        holder.textServingRecipe.setText(String.format("%d pessoas.", recipeModels.get(position).getServings()));
        Picasso.with(context)
                .load(mRecipeDrawables.get(position))
                .into(holder.imageRecipe);

        holder.buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Foi clicado aqui.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (recipeModels == null) return 0;

        return recipeModels.size();
    }

    public void setData(ArrayList<RecipeModel> mRList) {
        recipeModels = mRList;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageRecipe;
        TextView textNameRecipe;
        TextView textServingRecipe;
        Button buttonAction;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            this.imageRecipe = itemView.findViewById(R.id.image_recipe);
            this.textNameRecipe = itemView.findViewById(R.id.text_recipe_name);
            this.textServingRecipe = itemView.findViewById(R.id.text_servings);
            this.buttonAction = itemView.findViewById(R.id.button_action);
        }
    }
}
