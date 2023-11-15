package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.EditText;
public class ErrandsActivity extends AppCompatActivity {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private void setupSharedPrefs() {
        editor = prefs.edit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.errands_layout);
        ListView errandsListView = findViewById(R.id.main_menu);
//        setupSharedPrefs();

        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);
        String errandsNamesJson = prefs.getString("DATA", null);

        System.out.println(errandsNamesJson);

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> errandsNames = new Gson().fromJson(errandsNamesJson, listType);

        System.out.println(errandsNames);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, errandsNames);
        errandsListView.setAdapter(adapter);

        errandsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                System.out.println(selectedItem);

                Intent intent = new Intent(ErrandsActivity.this, EditTaskActivity.class);
                System.out.println(intent);
                intent.putExtra("TaskData", selectedItem);

                startActivity(intent);
            }
        });

    }

    public void goBack(View view){
        Intent intent = new Intent( ErrandsActivity.this, MainActivity.class);
        System.out.println(intent);
        startActivity(intent);
    }


}