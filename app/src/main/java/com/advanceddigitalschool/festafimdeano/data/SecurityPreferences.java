package com.advanceddigitalschool.festafimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {
    private SharedPreferences mSharedPreferences;


    //construct
    public SecurityPreferences(Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimAno", Context.MODE_PRIVATE );

    }

    public void storeString(String key , String value){
        this.mSharedPreferences.edit().putString(key,value).apply();
    }

    public String getstoredstring(String key){
        return this.mSharedPreferences.getString(key , "");
    }


}
