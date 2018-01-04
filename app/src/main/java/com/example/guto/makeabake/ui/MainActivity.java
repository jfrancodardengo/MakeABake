package com.example.guto.makeabake.ui;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.guto.makeabake.R;
import com.example.guto.makeabake.ui.RecipeFragment;

import com.example.guto.makeabake.adapters.RecipeAdapter;
import com.example.guto.makeabake.model.RecipeModel;
import com.example.guto.makeabake.utils.ConnectService;
import com.example.guto.makeabake.utils.JsonService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecipeFragment recipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            recipeFragment = new RecipeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.phone_view,recipeFragment)
                    .commit();
        }


//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("BUNDLE",JSON_URL);
//        jsonUrl = bundle.getString("BUNDLE");
//        if(ConnectService.isConnected(MainActivity.this)) {
//            getSupportLoaderManager().initLoader(0, bundle, dataResultLoaderRecipe);
//        }else{
//            Toast.makeText(MainActivity.this, "Sem Internet.", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        getSupportFragmentManager().putFragment(outState,"recipe_list",recipeFragment);
    }

    //    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }

//    private final LoaderManager.LoaderCallbacks<String> dataResultLoaderRecipe = new LoaderManager.LoaderCallbacks<String>() {
//        @Override
//        public Loader<String> onCreateLoader(int id, final Bundle args) {
//            return new AsyncTaskLoader<String>(MainActivity.this) {
//
//                @Override
//                protected void onStartLoading() {
//                    super.onStartLoading();
//                    if (args == null) {
//                        return;
//                    }
//
//                    forceLoad();
//                }
//
//                @Override
//                public String loadInBackground() {
//                    Log.v("Chegou aqui: ",jsonUrl);
//                    return download(jsonUrl);
//                }
//            };
//        }
//
//        @Override
//        public void onLoadFinished(Loader<String> loader, String data) {
//            mRecipeModels = JsonService.getmRecipeModels();
//
//            Boolean parse = new JsonService(mRecipeModels,data).parse();
//
//            if (parse) {
//                adapter = new RecipeAdapter(MainActivity.this, mRecipeModels);
//                recyclerView.setAdapter(adapter);
//            } else {
//                Toast.makeText(MainActivity.this, "Unable To Parse,Check Your Log output", Toast.LENGTH_LONG).show();
//            }
//
//        }
//
//        @Override
//        public void onLoaderReset(Loader loader) {
//
//        }
//    };

//    public String download(String url) {
//        Object connection = ConnectService.connect(url);
//        if (connection.toString().startsWith("Error")) {
//            return connection.toString();
//        }
//        try {
//            HttpURLConnection con = (HttpURLConnection) connection;
//            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream is = new BufferedInputStream(con.getInputStream());
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String line;
//                StringBuilder jsonData = new StringBuilder();
//
//                while ((line = br.readLine()) != null) {
//                    jsonData.append(line).append("\n");
//                }
//                br.close();
//                is.close();
//                return jsonData.toString();
//            } else {
//                return String.format("Error %s", con.getResponseMessage());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return String.format("Error %s", e.getMessage());
//        }
//    }
}


