package com.example.guto.makeabake.ui;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guto.makeabake.R;
import com.example.guto.makeabake.adapters.RecipeListAdapter;
import com.example.guto.makeabake.model.RecipeModel;
import com.example.guto.makeabake.utils.ConnectService;
import com.example.guto.makeabake.utils.JsonService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    //constante que vai conter o estado da lista completa
    public static final String WHOLE_ARRAY = "whole_array";
    //constante que vai conter o estado da resposta do json
    public static final String WHOLE_RESPONSE = "whole_response";

    //lista das receitas
    List<RecipeModel> recipeModelList;
    //adaptador das receitas
    RecipeListAdapter recipeListAdapter;
    //armazena o valor a ser retornado pelo onCreateView
    View rootView;
    //recyclerView para exibir a lista das receitas
    @BindView(R.id.recipeListRecycler)
    RecyclerView recipeListRecycler;
    //representa a diposição dos dados no telefone
    LinearLayoutManager linearLayoutManager;
    //representa o estado da activity
    Bundle bundle;
    //two-pane refere a tela do tablet e single-pane se refere a tela do telefone
    private boolean mTwoPane;
    //representa conexão com serviço
    Unbinder bind;
    //armazena os valores do estado em um arquivo de preferencias
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    @BindView(R.id.noInternet)
    TextView noInternet;
    String jsonResponse;
    ConnectService connectService;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bundle = savedInstanceState;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();

        rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        //se for frameLayout do telefone
        mTwoPane = (rootView.findViewById(R.id.recipeListFragment)==null);
        bind = ButterKnife.bind(this,rootView);
        if(!mTwoPane){
//            mTwoPane = false;
            recipeListRecycler.setLayoutManager(linearLayoutManager);
            recipeListRecycler.setHasFixedSize(true);
        }

        recipeModelList = new ArrayList<>();
        //se ja existe algo salvo no estado
        if(bundle!=null){
            recipeModelList = savedInstanceState.getParcelableArrayList(WHOLE_ARRAY);
            if(recipeModelList.size() > 0){
                recipeListAdapter = new RecipeListAdapter(recipeModelList);
                recipeListRecycler.setAdapter(recipeListAdapter);
                noInternet.setVisibility(View.GONE);
            }else{
                if(ConnectService.isConnected(getContext())){
                    //asyncTask para pegar os dados do JSON
                    new FetchRecipes().execute();
                    noInternet.setVisibility(View.GONE);
                }else {
                    noInternet.setVisibility(View.VISIBLE);
                }
            }
        }else{
//            noInternet.setVisibility(View.GONE);
            if(ConnectService.isConnected(getContext())){
                //asyncTask para pegar os dados do JSON
                new FetchRecipes().execute();
//                noInternet.setVisibility(View.GONE);
            }else {
                noInternet.setVisibility(View.VISIBLE);
            }
        }
        return rootView;
    }

    public class FetchRecipes extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            connectService = new ConnectService();
            try {
                jsonResponse = connectService.run(JsonService.JSON_URL);
                editor.putString(WHOLE_RESPONSE,jsonResponse);
                editor.apply();
                recipeModelList = JsonService.getJsonRecipe(jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(recipeModelList==null){
                noInternet.setVisibility(View.VISIBLE);
                noInternet.setText("Erro ao baixar os dados");
            }else{
                recipeListAdapter = new RecipeListAdapter(recipeModelList);
                recipeListRecycler.setAdapter(recipeListAdapter);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(WHOLE_ARRAY, (ArrayList<? extends Parcelable>) recipeModelList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
