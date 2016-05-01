package com.dvlpxl.contactosdp.app;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dvlpxl.contactosdp.R;
import com.dvlpxl.contactosdp.bd.ContactosDB;
import com.dvlpxl.contactosdp.modelo.Contacto;

public class CamposContacto extends Activity {
    private static final String TAG = "CamposContactoDBG";

    private int modo = 0;// 1 es agregar, 2 es editar

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_campos_contacto);
        TextView titulo = (TextView) findViewById(R.id.titulo);

        switch (intent.getIntExtra("modo", 0)) {
            case 1:
                modo = 1;
                titulo.setText("Agregar Contacto");
                break;
            case 2:
                modo = 2;

                titulo.setText("Actualizar Contacto");
                id = intent.getIntExtra("id", 0);
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("nombre"));
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("apellido"));
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("fijo"));
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("movil"));
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("email"));
                ((EditText) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("direccion"));
                break;
        }
    }

    private String getTxt(int i) {
        return ((EditText) findViewById(i)).getText().toString();
    }

    protected void agregarContacto(View view) {
        if (getTxt(R.id.nombre) == "" || getTxt(R.id.apellido) == "" || getTxt(R.id.fijo) == "" ||
                getTxt(R.id.movil) == "" || getTxt(R.id.email) == "" || getTxt(R.id.direccion) == ""){
            try{
                ContactosDB cbd = new ContactosDB(this);
                if (modo == 1) {
                    cbd.agregarContacto(getTxt(R.id.nombre),
                            getTxt(R.id.apellido), getTxt(R.id.fijo), getTxt(R.id.movil),
                            getTxt(R.id.email), getTxt(R.id.direccion), "null");
                    Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_SHORT).show();
                } else if (modo == 2) {
                    cbd.actualizarContacto(id, getTxt(R.id.nombre),
                            getTxt(R.id.apellido), getTxt(R.id.fijo), getTxt(R.id.movil),
                            getTxt(R.id.email), getTxt(R.id.direccion), "null");
                    Toast.makeText(this, "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                }

            } catch (SQLiteException e) {
                Log.w(TAG, "Usuario no agregado: " + e.getMessage());
                Toast.makeText(this, "Usuario o número ya existe.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Llenar Campos", Toast.LENGTH_SHORT).show();
        }

    }

}
