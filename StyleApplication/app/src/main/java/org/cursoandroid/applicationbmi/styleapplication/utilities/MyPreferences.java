package org.cursoandroid.applicationbmi.styleapplication.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aatovarma on 1/02/2017.
 */

public class MyPreferences {
    private static final String PREF_NAME = "MyData";
    private static final int PRIVATE_MODE = 0;
    private static final String IS_LOGIN = "is_login";
    private static final String USER_NAME = "username";

    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLogin(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setStateLogin(boolean state){
        editor.putBoolean(IS_LOGIN, state);
        editor.apply();
    }

    public String getUserName(){
        return pref.getString(USER_NAME, "");
    }

    public void setUserName(String username){
        editor.putString(USER_NAME, username);
        editor.apply();
    }


}
