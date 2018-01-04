package com.example.guto.makeabake.data;

import com.example.guto.makeabake.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUTO on 02/01/2018.
 */

public class ImageAssets {
    private static final List<Integer> imagesCardView = new ArrayList<Integer>() {{
        add(R.drawable.nutella_pie);
        add(R.drawable.brownies);
        add(R.drawable.cheesecake);
        add(R.drawable.yellow_cake);
    }};

    public static List<Integer> getImagesCardView() {
        return imagesCardView;
    }
}
