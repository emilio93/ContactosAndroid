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
        cargarContactos();
    }

    public void cargarContactos() {
        ContactosDB cbd = null;
        try {
            cbd = new ContactosDB(this);
            Log.d(TAG, "BD abierta");
        } catch (Exception e) {
            Log.w(TAG, e.getClass().getName() + "No se abrió la base de datos. " + e.getMessage());
        }
        try{
            cbd.agregarContacto("emilio", "rojas", "emilio93@gmail.com", "22340687", "88545404",
                    "frente a escuela", "null");
            Log.d(TAG, "Usuario Agregado");

        } catch (SQLiteConstraintException e) {
            Log.w(TAG, "No se insertó el usuario. " + e.getMessage());
        }
        try{
            final List<Contacto> contactos = cbd.obtenerContactos();
            for (int i = 0; i < contactos.size(); i++) {
                final int j = i;
                Button btn = new Button(this);
                btn.setText(contactos.get(i).getNombre() + " " + contactos.get(i).getApellido());
                btn.setBackground(((Drawable) getDrawable(R.color.gris80)));
                btn.setTextColor(((ColorStateList) getColorStateList(R.color.blanco)));
                btn.setTextSize(20);
                final Context th = (Context)this;

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(th, VistaContacto.class);

                        intent.putExtra("id", contactos.get(j).getId());
                        intent.putExtra("nombre", contactos.get(j).getNombre());
                        intent.putExtra("apellido", contactos.get(j).getApellido());
                        intent.putExtra("fijo", contactos.get(j).getTelefonoFijo());
                        intent.putExtra("movil", contactos.get(j).getTelefonoMovil());
                        intent.putExtra("email", contactos.get(j).getEmail());
                        intent.putExtra("direccion", contactos.get(j).getDireccion());
                        intent.putExtra("imagen", contactos.get(j).getImagen());

                        startActivity(intent);
                    }
                });

                FrameLayout lyt = new FrameLayout(this);
                lyt.setPadding(20, 20, 20, 20);
                lyt.addView(btn);

                LinearLayout ll = (LinearLayout) findViewById(R.id.contactosLista);
                ll.addView(lyt);
            }
            Log.d(TAG, "Usuarios Listados");
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
