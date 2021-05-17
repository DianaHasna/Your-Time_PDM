package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Alegerea extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alegerea);

        textView1 = (TextView)findViewById(R.id.button1);
        textView2 = (TextView)findViewById(R.id.button2);


        textView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Alegerea.this,Para.class);
                startActivity(intent);

                Toast.makeText(Alegerea.this,"Saptamana para",Toast.LENGTH_SHORT).show();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Alegerea.this,Impara.class);
                startActivity(intent);

                Toast.makeText(Alegerea.this,"Saptamana impara",Toast.LENGTH_SHORT).show();
            }
        });
    }

}