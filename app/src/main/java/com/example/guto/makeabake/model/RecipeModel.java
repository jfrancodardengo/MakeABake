package com.example.guto.makeabake.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GUTO on 28/12/2017.
 */

public class RecipeModel implements Parcelable {
    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };
    private int mIdRecipe;
    private String mNameRecipe;
    private int mServings;
    private String mImage;

    public RecipeModel() {
    }

    protected RecipeModel(Parcel in) {
        mIdRecipe = in.readInt();
        mNameRecipe = in.readString();
        mServings = in.readInt();
        mImage = in.readString();
    }

    public int getIdRecipe() {
        return mIdRecipe;
    }

    public void setIdRecipe(int mIdRecipe) {
        this.mIdRecipe = mIdRecipe;
    }

    public String getName() {
        return mNameRecipe;
    }

    public void setName(String nName) {
        this.mNameRecipe = nName;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int nServings) {
        this.mServings = nServings;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mIdRecipe);
        parcel.writeString(mNameRecipe);
        parcel.writeInt(mServings);
        parcel.writeString(mImage);
    }
}
