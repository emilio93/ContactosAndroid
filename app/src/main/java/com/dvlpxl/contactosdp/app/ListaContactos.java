package com.dvlpxl.contactosdp.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class ListaContactos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.dvlpxl.contactosdp.R.layout.activity_lista_contactos);
    }
    public void agregarContacto(View view) {
        Intent intent = new Intent(this, CamposContacto.class);
        String titulo = "Agregar Contacto";
        intent.putExtra("titulo", titulo);
        startActivity(intent);
    }
}
