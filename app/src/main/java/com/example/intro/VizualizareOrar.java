package com.example.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VizualizareOrar extends Activity implements View.OnClickListener {
    EditText editDenumire, editInceput,editSfarsit,  editLink, editComentarii, editData;
    Button  btnSearch, btnViewAll;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_orar);
        editDenumire = (EditText) findViewById(R.id.editDenumire);
        editInceput = (EditText) findViewById(R.id.editInceput);
        editSfarsit = (EditText) findViewById(R.id.editSfarsit);
        editLink = (EditText)findViewById(R.id.editLink);
        editComentarii = (EditText)findViewById(R.id.editComentarii);
        editData = (EditText)findViewById(R.id.editData);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnViewAll = (Button) findViewById(R.id.btnViewAll);
        btnSearch.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        db = openOrCreateDatabase("YourTimeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS materii(denumire VARCHAR,inceput VARCHAR,sfarsit VARCHAR,link VARCHAR,comentarii VARCHAR,data VARCHAR);");}

    public void onClick(View view) {
        if (view == btnSearch) {
            if (editData.getText().toString().trim().length() == 0) {
                showMessage("Error", "Te rog introdu data!");
                return;
            }
            String sDate1 = editData.getText().toString();
            if (sDate1.equals("Luni") || sDate1.equals("Marti") || sDate1.equals("Miercuri") || sDate1.equals("Joi") || sDate1.equals("Vineri") || sDate1.equals("Sambata") || sDate1.equals("Duminica")) {
                Cursor c = db.rawQuery("SELECT * FROM materii WHERE data='" + editData.getText() + "'", null);
                StringBuffer buffer = new StringBuffer();
                if (c.getCount() == 0) {
                    showMessage("Error", "Nu exista nicio intalnire!");
                    return;
                }
                while(c.moveToNext()){
                    buffer.append("Denumire: " + c.getString(0) + "\n");
                    buffer.append("Inceput: " + c.getString(1) + "\n");
                    buffer.append("Sfarsit: " + c.getString(2) + "\n\n");
                    buffer.append("Link: " + c.getString(3) + "\n\n\n");
                    buffer.append("Comentarii: " + c.getString(4) + "\n\n\n\n");
                    buffer.append("Data: " + c.getString(5) + "\n\n\n\n\n");
                }
                showMessage("Your Time", buffer.toString());
                return;
            }
            if(sDate1.contains("/") || sDate1.contains(".")) {
                Date date1 = null;
                try {
                    if(sDate1.contains("/")) {
                        date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                    }
                    else if(sDate1.contains(".")){
                        date1 = new SimpleDateFormat("dd.MM.yyyy").parse(sDate1);
                    }
                } catch (ParseException e) {
                    //;
                }

                int i = 3;
                String s = "";
                for (int j = 0; j < i; j++) {
                    s = s + date1.toString().charAt(j);
                }


                switch (s) {
                    case "Mon":
                        s = "Luni";
                        break;
                    case "Tue":
                        s = "Marti";
                        break;
                    case "Wed":
                        s = "Miercuri";
                        break;
                    case "Thu":
                        s = "Joi";
                        break;
                    case "Fri":
                        s = "Vineri";
                        break;
                    case "Sat":
                        s = "Sambata";
                        break;
                    case "Sun":
                        s = "Duminica";
                        break;

                }

                Cursor c = db.rawQuery("SELECT * FROM materii WHERE data='" + s + "'", null);
                StringBuffer buffer = new StringBuffer();
                if (c.getCount() == 0) {
                    showMessage("Error", "Pentru ziua de " + s + " nu exista nicio intalnire!");
                    return;
                }
                while(c.moveToNext()){
                    buffer.append("Denumire: " + c.getString(0) + "\n");
                    buffer.append("Inceput: " + c.getString(1) + "\n");
                    buffer.append("Sfarsit: " + c.getString(2) + "\n\n");
                    buffer.append("Link: " + c.getString(3) + "\n\n\n");
                    buffer.append("Comentarii: " + c.getString(4) + "\n\n\n\n");
                    buffer.append("Data: " + c.getString(5) + "\n\n\n\n\n");
                }
                showMessage("Your Time", buffer.toString());
                return;

            }
        }
        if (view == btnViewAll) {
            Cursor c = db.rawQuery("SELECT * FROM materii", null);
            if (c.getCount() == 0) {
                showMessage("Error", "Date inexistente");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Denumire: " + c.getString(0) + "\n");
                buffer.append("Inceput: " + c.getString(1) + "\n");
                buffer.append("Sfarsit: " + c.getString(2) + "\n\n");
                buffer.append("Link: " + c.getString(3) + "\n\n\n");
                buffer.append("Comentarii: " + c.getString(4) + "\n\n\n\n");
                buffer.append("Ziua: " + c.getString(5) + "\n\n\n\n\n");
            }
            showMessage("Your Time", buffer.toString());
        }
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText() {
        editDenumire.setText("");
        editInceput.setText("");
        editSfarsit.setText("");
        editLink.setText("");
        editComentarii.setText("");
        editData.setText("");
        editData.requestFocus();
    }
}