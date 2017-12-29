package com.example.guto.makeabake.utils;

import com.example.guto.makeabake.model.IngredientsModel;
import com.example.guto.makeabake.model.RecipeModel;
import com.example.guto.makeabake.model.StepsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by GUTO on 28/12/2017.
 */

public class JsonService {
    private static ArrayList<IngredientsModel> sIngredientsModels = new ArrayList<IngredientsModel>();
    private static ArrayList<StepsModel> sStepsModels = new ArrayList<StepsModel>();
    private final String jsonData;
    private ArrayList<RecipeModel> mRecipeModels = new ArrayList<RecipeModel>();

    public JsonService(ArrayList<RecipeModel> mRecipeModels, String jsonData) {
        this.mRecipeModels = mRecipeModels;
        this.jsonData = jsonData;
    }

    public Boolean parse() {
        mRecipeModels.clear();
        sStepsModels.clear();
        sIngredientsModels.clear();
        RecipeModel recipeModel;
        IngredientsModel ingredientsModel;
        StepsModel stepsModel;
        try {
            JSONObject jsonObject;
            JSONObject reader = new JSONObject(jsonData);
            int idRecipe = reader.getInt("id");
            String nameRecipe = reader.getString("name");
            JSONArray jsonArrayIngredients = reader.getJSONArray("ingredients");

            for (int i = 0; i < jsonArrayIngredients.length(); i++) {
                jsonObject = jsonArrayIngredients.getJSONObject(i);
                int quantity = jsonObject.getInt("quantity");
                String measure = jsonObject.getString("measure");
                String ingredient = jsonObject.getString("ingredient");

                ingredientsModel = new IngredientsModel();
                ingredientsModel.setQuantity(quantity);
                ingredientsModel.setMeasure(measure);
                ingredientsModel.setIngredient(ingredient);
                sIngredientsModels.add(ingredientsModel);
            }

            JSONArray jsonArraySteps = reader.getJSONArray("steps");
            for (int j = 0; j < jsonArraySteps.length(); j++) {
                jsonObject = jsonArraySteps.getJSONObject(j);
                int idStep = jsonObject.getInt("id");
                String shortDescription = jsonObject.getString("shortDescription");
                String description = jsonObject.getString("description");
                String videoUrl = jsonObject.getString("videoURL");
                String thumbnailUrl = jsonObject.getString("thumbnailURL");

                stepsModel = new StepsModel();
                stepsModel.setIdStep(idStep);
                stepsModel.setShortDescription(shortDescription);
                stepsModel.setDescription(description);
                stepsModel.setVideoUrl(videoUrl);
                stepsModel.setThumbnailUrl(thumbnailUrl);
                sStepsModels.add(stepsModel);
            }

            int servings = reader.getInt("servings");
            String image = reader.getString("image");

            recipeModel = new RecipeModel();
            recipeModel.setIdRecipe(idRecipe);
            recipeModel.setName(nameRecipe);
            recipeModel.setServings(servings);
            recipeModel.setImage(image);
            mRecipeModels.add(recipeModel);

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
