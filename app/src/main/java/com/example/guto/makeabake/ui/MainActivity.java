package com.example.guto.makeabake.ui;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.guto.makeabake.R;


public class MainActivity extends AppCompatActivity {
    //constante que vai conter o estado da lista de receitas
    public static final String RECIPE_LIST = "recipe_list";


    //fragmento com a lista de receitas
    Fragment recipeList;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            recipeList = new RecipeListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer,recipeList)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState,RECIPE_LIST,recipeList);
    }
}


