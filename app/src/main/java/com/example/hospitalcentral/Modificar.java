package com.example.hospitalcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText et_nombre, et_apellido;
    Button btn_modificar, btn_eliminar;
    int id;
    String nombre;
    String apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b = getIntent().getExtras();
        if (b!=null){
            id = b.getInt("Id");
            nombre = b.getString("Nombre");
            apellido = b.getString("Apellido");
        }


        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);

        btn_modificar = (Button) findViewById(R.id.btn_guardar);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(id);
                onBackPressed();
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(id, et_nombre.getText().toString(),et_apellido.getText().toString());
                onBackPressed();
            }
        });
    }


    private void Modificar (int Id, String Nombre, String Apellido){
        BD_Helper helper = new BD_Helper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "update PERSONAS set Nombre='" + Nombre + "', Apellido='" + Apellido + "' where Id="+Id;
        db.execSQL(sql);
        db.close();

    }


    private void Eliminar (int Id){
        BD_Helper helper = new BD_Helper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "delete from PERSONAS where Id="+Id;
        db.execSQL(sql);
        db.close();

    }
}