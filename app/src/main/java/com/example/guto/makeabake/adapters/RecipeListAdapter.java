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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by GUTO on 06/01/2018.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {
    public List<RecipeModel> recipeModelList;
    //armazena as imagens
    private List<Integer> mRecipeDrawables = ImageAssets.getImagesCardView();

    Context context;

    public RecipeListAdapter(List<RecipeModel> recipeList) {
        this.recipeModelList = recipeList;
    }


    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return new RecipeListViewHolder(layoutInflater.inflate(R.layout.recipe_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeListViewHolder holder, int position) {
        holder.textRecipeName.setText(recipeModelList.get(position).getName());
        holder.textServings.setText(String.format("%d pessoas", recipeModelList.get(position).getServings()));
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
        if (recipeModelList != null) {
            return recipeModelList.size();
        } else {
            return 0;
        }
    }

    //Done SetData function
    public void setData(List<RecipeModel> mRList) {
        recipeModelList = mRList;
        notifyDataSetChanged();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_recipe)
        ImageView imageRecipe;
        @BindView(R.id.text_recipe_name)
        TextView textRecipeName;
        @BindView(R.id.text_servings)
        TextView textServings;
        @BindView(R.id.button_action)
        Button buttonAction;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
