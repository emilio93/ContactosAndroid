package com.dvlpxl.contactosdp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dvlpxl.contactosdp.R;

public class CamposContacto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_campos_contacto);
        TextView titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText(intent.getCharSequenceExtra("titulo"));
    }

}
