package com.example.myapplication;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CreateTaskActivity extends AppCompatActivity {

    public static final String DATA = "DATA";

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private EditText nameEditText;
    private Switch statusEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtask_activity);
        setupSharedPrefs();

        System.out.println("Something is up fr fr");
    }

    private void setupSharedPrefs() {
        prefs = getSharedPreferences("LocalStorage", MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void btnSubmitTask(View view) {
        System.out.println("COME ON THIS HAS TO WORK");

        nameEditText = findViewById(R.id.nameEditText);
        statusEditText = findViewById(R.id.statusEditText);

        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);

        // Retrieving the Data as String
        String errandsNamesJson = prefs.getString("DATA", null);

        System.out.println(errandsNamesJson);

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> errandsNames = new Gson().fromJson(errandsNamesJson, listType);

        System.out.println(errandsNames);
        System.out.println(nameEditText.getText() + " - " + statusEditText.getText());

        String status = "";

        if(statusEditText.isChecked() == false){
            status = "Done";
        }else{
            status = "Due";
        }

        errandsNames.add((errandsNames.toArray().length + 1) + " - " + nameEditText.getText() + " - " + status);

        System.out.println(errandsNames);

        Gson gson = new Gson();
        String booksString = gson.toJson(errandsNames);

        editor.putString(DATA, booksString);
        editor.commit();

        Intent intent = new Intent(CreateTaskActivity.this, MainActivity.class);
        System.out.println(intent);
        startActivity(intent);

    }


}