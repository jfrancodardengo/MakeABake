package com.example.guto.makeabake.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guto.makeabake.R;
import com.example.guto.makeabake.model.RecipeModel;
import com.example.guto.makeabake.utils.ConnectService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://go.udacity.com/android-baking-app-json";
    private ArrayList<RecipeModel> mRecipeModels = new ArrayList<RecipeModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public String download(String url) {
        Object connection = ConnectService.connect(url);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder jsonData = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    jsonData.append(line).append("\n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } else {
                return String.format("Error %s", con.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return String.format("Error %s", e.getMessage());
        }
    }
}
