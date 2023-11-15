package com.example.myapplication;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
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
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class EditTaskActivity extends AppCompatActivity{
    public static final String DATA = "DATA";
    private int id;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    private String taskStatus = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_activity);
        setupSharedPrefs();

        Intent intent = getIntent();
        if (intent != null) {
            String taskData = intent.getStringExtra("TaskData");

            Switch editStatus = findViewById(R.id.status);
            EditText editTitle = findViewById(R.id.title);
            EditText editId = findViewById(R.id.ID);

            String[] split = taskData.split(" - ");

            String Id = split[0];
            String title = split[1];
            String status = split[2];
            id = Integer.parseInt(Id);

            editTitle.setText(title);
            taskStatus = status;

            if(status.toLowerCase().equals("due")){
                editStatus.setChecked(true);
            }else{
                editStatus.setChecked(false);
            }
            editId.setText(Id);

        }

        System.out.println("Lets get it");
    }

    private void setupSharedPrefs() {
        prefs = getSharedPreferences("LocalStorage", MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void btnUpdateTask(View view){

        Switch editStatus = findViewById(R.id.status);
        EditText editTitle = findViewById(R.id.title);
        EditText editId = findViewById(R.id.ID);

        String status = "";

        if(editStatus.isChecked() == false){
            status = "Done";
        }else{
            status = "Due";
        }

//        editTitle.setText(title);
//        editStatus.setText(status);
//

        String updated = editId.getText() + " - " + editTitle.getText() + " - " + status;

        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);
        String errandsNamesJson = prefs.getString("DATA", null);

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> errandsNames = new Gson().fromJson(errandsNamesJson, listType);

        System.out.println(errandsNames);

        errandsNames.set(id - 1, updated);

        System.out.println(errandsNames);

        Gson gson = new Gson();
        String booksString = gson.toJson(errandsNames);

        editor.putString(DATA, booksString);
        editor.commit();

        Intent intent = new Intent(EditTaskActivity.this, ErrandsActivity.class);
        System.out.println(intent);
        startActivity(intent);
    }
}
