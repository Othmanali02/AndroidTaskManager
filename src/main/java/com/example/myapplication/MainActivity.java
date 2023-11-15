package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private TextView txtResults;


    private ListView main_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPrefs();

        Errand[] errands = new Errand[6];
        errands[0] =    new Errand("Morning Run", "Due", 1);
        errands[1] =    new Errand("Django Database Modify", "Due", 2);
        errands[2] =    new Errand("Drive to Deir Ghassaneh", "Due", 3);
        errands[3] =    new Errand("Water the Trees", "Due", 4);
        errands[4] =    new Errand("Finish Anagram Scramble", "Due", 5);
        errands[5] =    new Errand("Fix in-game Time", "Due", 6);

        System.out.println("BRo come one");

        ArrayList<String> errandNames = new ArrayList<>();
        for (int i = 0; i < errands.length; i++) {
            errandNames.add(errands[i].getId() + " - " + errands[i].getName() + " - " + errands[i].getStatus());
        }

        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);
        String errandsNamesJson = prefs.getString("DATA", null);

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> localErrandsNames = new Gson().fromJson(errandsNamesJson, listType);
        boolean isEqual = errandNames.equals(localErrandsNames);

        if (isEqual) {
            savetoLocalStorage(errandNames);
        } else {
            System.out.println("No Change");
        }

    }
    private void setupSharedPrefs() {
        prefs = getSharedPreferences("LocalStorage", MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void savetoLocalStorage(ArrayList<String> errandsList) {
        editor = prefs.edit();

        Gson gson = new Gson();
        String errands = gson.toJson(errandsList);

        editor.putString("LocalStorage", errands);
        editor.commit();
    }

    public void btnShowALlTasks(View view){
        Intent intent = new Intent(MainActivity.this, ErrandsActivity.class);
        System.out.println(intent);
        startActivity(intent);
    }


    public void btnLoadCreateClass(View view) {
       System.out.println("COME ON THIS HAS TO WORK");
        Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
        System.out.println(intent);
        startActivity(intent);
    }




}
