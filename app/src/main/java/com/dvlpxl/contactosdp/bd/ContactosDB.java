package com.dvlpxl.contactosdp.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Button;

import com.dvlpxl.contactosdp.modelo.Contacto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emili on 4/30/2016.
 */
public class ContactosDB extends SQLiteOpenHelper {
    public ContactosDB(Context context) {
        super(context, "ContactosDP.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `contactos` (" +
                "`id` INTEGER PRIMARY KEY," +
                "`nombre` TEXT," +
                "`apellido` TEXT," +
                "`telefonoFijo` TEXT UNIQUE," +
                "`telefonoMovil` TEXT UNIQUE," +
                "`email` TEXT," +
                "`direccion` TEXT," +
                "`imagen` TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS `contactos`;");
        onCreate(db);
    }

    public void agregarContacto(String nombre,String apellido, String email, String telefonoFijo,
                                String telefonoMovil, String direccion, String imagen) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("apellido", apellido);
        cv.put("email", email);
        cv.put("telefonoFijo", telefonoFijo);
        cv.put("telefonoMovil", telefonoMovil);
        cv.put("direccion", direccion);
        cv.put("imagen", imagen);
        this.getWritableDatabase().insertOrThrow("contactos", "", cv);
    }

    public void eliminarContacto(int id) {
        this.getWritableDatabase().delete("contactos", "id=" + id, null);
    }

    public void actualizarContacto(int id, String nombre,String apellido, String email,
                                   String telefonoFijo, String telefonoMovil, String direccion,
                                   String imagen) {
        this.getWritableDatabase().execSQL("UPDATE contactos " +
                "SET " +
                "nombre='" + nombre + "'," +
                "apellido='" + apellido + "'," +
                "email='" + email + "'," +
                "telefonoFijo='" + telefonoFijo + "'," +
                "telefonoMovil='" + telefonoMovil + "'," +
                "direccion='" + direccion + "'," +
                "imagen='" + imagen +
                "' WHERE id=" + id + ";");
    }

    public List<Contacto> obtenerContactos() {
        String sql = "SELECT * FROM contactos";
        List<Contacto> contactos = new ArrayList<>();
        Cursor cursor = this.getWritableDatabase().rawQuery(sql, null);
        while(cursor.moveToNext()) {
            contactos.add(new Contacto(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7)));
        }
        return contactos;
    }
}
