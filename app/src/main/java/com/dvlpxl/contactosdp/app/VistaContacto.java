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

public class VistaContacto extends Activity {
    private static final String TAG = "VistaContactoDBG";

    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dvlpxl.contactosdp.R.layout.activity_vista_contacto);
        setFields(null);

    }

    public void setFields(View view) {
        Intent intent = getIntent();
        ((TextView) findViewById(R.id.titulo)).setText(intent.getCharSequenceExtra("nombre") + " " +
                intent.getCharSequenceExtra("apellido"));
        id = intent.getIntExtra("id", 0);
        Log.d(TAG, "id es " + id);
        ((TextView) findViewById(R.id.nombre)).setText(intent.getCharSequenceExtra("nombre") + " " +
                intent.getCharSequenceExtra("apellido"));
        ((TextView) findViewById(R.id.email)).setText(intent.getCharSequenceExtra("email"));
        ((TextView) findViewById(R.id.fijo)).setText(intent.getCharSequenceExtra("fijo"));
        ((TextView) findViewById(R.id.movil)).setText(intent.getCharSequenceExtra("movil"));
        ((TextView) findViewById(R.id.direccion)).setText(intent.getCharSequenceExtra("direccion"));

    }

    private String getTxt(int i) {
        return ((EditText) findViewById(i)).getText().toString();
    }

    @Override
    public void onBackPressed() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void eliminarContacto(View view) {
        try{
            ContactosDB cbd = new ContactosDB(this);
            cbd.eliminarContacto(id);
            Toast.makeText(this, "Contacto Eliminado", Toast.LENGTH_SHORT).show();

            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);

        } catch (SQLiteException e) {
            Log.w(TAG, "Usuario no eliminado: " + e.getMessage());
            Toast.makeText(this, "Usuario no eliminado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarContacto(View view) {
        Intent intent = new Intent(this, CamposContacto.class);
        intent.putExtra("modo", 2);
        intent.putExtra("id", id);
        intent.putExtra("nombre", getIntent().getStringExtra("nombre"));
        intent.putExtra("apellido", getIntent().getStringExtra("apellido"));
        intent.putExtra("fijo", getIntent().getStringExtra("fijo"));
        intent.putExtra("movil", getIntent().getStringExtra("movil"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("direccion", getIntent().getStringExtra("direccion"));
        startActivity(intent);
    }

}
