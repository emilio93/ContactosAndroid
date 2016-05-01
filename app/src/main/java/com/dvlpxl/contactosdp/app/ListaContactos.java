package com.dvlpxl.contactosdp.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.dvlpxl.contactosdp.R;
import com.dvlpxl.contactosdp.bd.ContactosDB;
import com.dvlpxl.contactosdp.modelo.Contacto;

import java.util.List;

public class ListaContactos extends Activity {
    private static final String TAG = "ListaContactosDBG";
    ContactosDB bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_lista_contactos);
        cargarContactos(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarContactos(null);
    }

    public void cargarContactos(View view) {
        ContactosDB cbd = null;
        try {
            cbd = new ContactosDB(this);
        } catch (Exception e) {
            Log.w(TAG, e.getClass().getName() + "No se abrió la base de datos. " + e.getMessage());
        }
        try{
            List<Contacto> contactos = cbd.obtenerContactos();
            ((LinearLayout) findViewById(R.id.contactosLista)).removeAllViews();
            for (int i = 0; i < contactos.size(); i++) {
                final int j = i;
                Button btn = new Button(this);
                btn.setText(contactos.get(i).getNombre() + " " + contactos.get(i).getApellido());
                btn.setBackground(((Drawable) getDrawable(R.color.gris80)));
                btn.setTextColor(((ColorStateList) getColorStateList(R.color.blanco)));
                btn.setTextSize(20);
                final Context th = (Context)this;
                final Contacto cntc = contactos.get(i);

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(th, VistaContacto.class);
                        Log.d(TAG, "id: " + cntc.getId());
                        intent.putExtra("id", cntc.getId());
                        intent.putExtra("nombre", cntc.getNombre());
                        intent.putExtra("apellido", cntc.getApellido());
                        intent.putExtra("fijo", cntc.getTelefonoFijo());
                        intent.putExtra("movil", cntc.getTelefonoMovil());
                        intent.putExtra("email", cntc.getEmail());
                        intent.putExtra("direccion", cntc.getDireccion());
                        intent.putExtra("imagen", cntc.getImagen());

                        startActivity(intent);
                    }
                });

                FrameLayout lyt = new FrameLayout(this);
                lyt.setPadding(20, 20, 20, 20);
                lyt.addView(btn);

                LinearLayout ll = (LinearLayout) findViewById(R.id.contactosLista);

                ll.addView(lyt);
            }
            Toast.makeText(this, "Contactos Actualizados", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.w(TAG, e.getClass().getName() + "No se Completo el try" + e.getMessage());
        }
    }

    public void agregarContacto(View view) {
        Intent intent = new Intent(this, CamposContacto.class);
        intent.putExtra("modo", 1);
        startActivity(intent);
    }
}
