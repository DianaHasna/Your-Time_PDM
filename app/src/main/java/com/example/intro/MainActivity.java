package com.example.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button buttonO = (Button) findViewById(R.id.buttonO);
        Button buttonC = (Button) findViewById(R.id.buttonC);





        buttonO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentO = new Intent(MainActivity.this, VizualizareOrar.class);
                startActivity(intentO);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent(MainActivity.this, CreareOrar.class);
                startActivity(intentC);
            }
        });

        }
    }