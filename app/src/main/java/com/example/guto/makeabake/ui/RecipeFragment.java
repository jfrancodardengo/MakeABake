package com.example.guto.makeabake.ui;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guto.makeabake.R;
import com.example.guto.makeabake.adapters.RecipeAdapter;
import com.example.guto.makeabake.model.RecipeModel;
import com.example.guto.makeabake.utils.ConnectService;
import com.example.guto.makeabake.utils.JsonService;

import java.io.IOException;
import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    ArrayList<RecipeModel> recipeModels;
    RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Bundle bundle;
    boolean tablet;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String jsonResponse;

    public RecipeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bundle = savedInstanceState;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();

        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        tablet = (rootView.findViewById(R.id.fragmentMainTablet) !=null);
        if(!tablet){
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(),3));
        }

        recipeModels = new ArrayList<>();
        if(bundle != null){
            recipeModels = savedInstanceState.getParcelableArrayList("recipeList");
            if(recipeModels.size() > 0){
                recipeAdapter = new RecipeAdapter(recipeModels);
                recyclerView.setAdapter(recipeAdapter);
            }else{
                if(ConnectService.isConnected(getContext())){
                    new FetchRecipes().execute();
                }else {
                    Toast.makeText(getContext(),"Sem Internet.",Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(!recipeModels.isEmpty()){
            Log.d("Check Run", recipeModels.get(0).getName());
        }
        if(bundle==null){
            if(ConnectService.isConnected(getContext())){
                new FetchRecipes().execute();
            }else {
                Toast.makeText(getContext(),"Sem Internet.",Toast.LENGTH_SHORT).show();
            }
        }

        return rootView;
    }

    private class FetchRecipes extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            jsonResponse = null;
            jsonResponse = JsonService.download(JsonService.JSON_URL);
            editor.putString("recipeResponse",jsonResponse);
            editor.apply();
            recipeModels = JsonService.getJsonRecipe(jsonResponse);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(recipeModels==null){
                Toast.makeText(getContext(),"Sem Internet.",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"Não foi possível obter os dados.",Toast.LENGTH_SHORT).show();
            }else{
                recipeAdapter = new RecipeAdapter(recipeModels);
                recyclerView.setAdapter(recipeAdapter);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("recipeList",recipeModels);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
