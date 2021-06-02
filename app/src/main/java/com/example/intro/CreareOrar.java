package com.example.intro;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class CreareOrar extends Activity implements OnClickListener {
    EditText editDenumire, editInceput,editSfarsit,  editLink, editComentarii, editData;
    Button btnAdd, btnDelete;
    SQLiteDatabase db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creare_orar);
        editDenumire = (EditText) findViewById(R.id.editDenumire);
        editInceput = (EditText) findViewById(R.id.editInceput);
        editSfarsit = (EditText) findViewById(R.id.editSfarsit);
        editLink = (EditText)findViewById(R.id.editLink);
        editComentarii = (EditText)findViewById(R.id.editComentarii);
        editData = (EditText)findViewById(R.id.editData);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        db = openOrCreateDatabase("YourTimeDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS materii(denumire VARCHAR,inceput VARCHAR,sfarsit VARCHAR,link VARCHAR,comentarii VARCHAR,data VARCHAR);");}


    public void onClick(View view) {
        if (view == btnAdd) {
            if (editDenumire.getText().toString().trim().length() == 0 ||
                    editInceput.getText().toString().trim().length() == 0 ||
                    editLink.getText().toString().trim().length() == 0 ||
                    editComentarii.getText().toString().trim().length() == 0 ||
                    editData.getText().toString().trim().length() == 0 ||
                    editSfarsit.getText().toString().trim().length() == 0) {
                showMessage("Error", "Te rog introdu toate datele corespunzatoare!");
                return;
            }
            db.execSQL("INSERT INTO materii VALUES('" + editDenumire.getText() + "','" + editInceput.getText() +
                    "','" + editSfarsit.getText() + "','" + editLink.getText() +
                    "','" + editComentarii.getText() + "','" + editData.getText() + "');");
            showMessage("Success", "Adaugare cu succes!");
            clearText();
        }
        if (view == btnDelete) {
            if (editDenumire.getText().toString().trim().length() == 0) {
                showMessage("Error", "Te rog introdu denumirea materiei!");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM materii WHERE denumire='" + editDenumire.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM materii WHERE denumire='" + editDenumire.getText() + "'");
                showMessage("Success", "Inregistrare stearsa");
            } else {
                showMessage("Error", "Materie inexistenta!");
            }
            clearText();
        }
    }
    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
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
        editDenumire.requestFocus();
    }
}