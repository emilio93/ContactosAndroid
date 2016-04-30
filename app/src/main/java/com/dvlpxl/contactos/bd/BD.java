package com.dvlpxl.contactos.bd;

import android.database.sqlite.SQLiteDatabase;

/**
 * Maneja la conexión con la base de datos.
 * @author Emilio Rojas
 */
public final class BD {
    private boolean debug = false;

    private SQLiteDatabase db = null;

    private String error = "";
    private String mssg = "";

    public BD() {
        abrirBaseDatos();
    }

    public String getError() {
        return error;
    }
    private String appendError(String txt) {
        error += txt + '\n';
        return error;
    }

    public String getMssg() {
        return mssg;
    }
    private String appendMssg(String txt) {
        mssg += txt + '\n';
        return mssg;
    }


    /**
     * Crea o abre la base de datos que se utiliza.
     * @return Indica si se crea o abre(true), o si no es posible ninguna de las dos(false).
     */
    private boolean abrirBaseDatos() {
        boolean creada = false;
        try {
            db = SQLiteDatabase.openOrCreateDatabase("ContactosDP", null);
            creada = true;
        } catch(Exception e) {
            appendError(e.getClass().getName() + "@BD.crearBaseDatos(): " + e.getMessage());
        }
        return creada;
    }

    /**
     * En caso que no existan las tablas, se crean.
     * @return Indica si se crearon o no todas las tablas.
     */
    public boolean crearTablas() {
        boolean creadas = true;
        try {
               /* Create a Table in the Database. */
            db.execSQL("CREATE TABLE IF NOT EXISTS `contactos` (" +
                    "    `id` INTEGER PRIMARY KEY," +
                    "    `nombre` TEXT," +
                    "    `apellido` TEXT," +
                    "    `direccion` TEXT," +
                    "    `imagen` TEXT" +
                    ");");
            appendMssg("Tabla contactos creada. ");

            db.execSQL("CREATE TABLE IF NOT EXISTS `emails` (" +
                    "    `id` INTEGER PRIMARY KEY," +
                    "    `idContacto` INTEGER INDEX," +
                    "    `tipo` TEXT," +
                    "    `email` TEXT," +
                    "    FOREIGN KEY (`idContacto`) REFERENCES contactos(`id`)" +
                    ");");
            appendMssg("Tabla emails creada. ");

            db.execSQL("CREATE TABLE IF NOT EXISTS `telefonos` (" +
                    "    `id` INTEGER PRIMARY KEY," +
                    "    `idContacto` INTEGER INDEX," +
                    "    `tipo` TEXT," +
                    "    `telefono` TEXT" +
                    "    FOREIGN KEY (`idContacto`) REFERENCES contactos(`id`)" +
                    ");");
            appendMssg("Tabla telefonos creada. ");

            creadas = true;

        } catch (Exception e) {
            appendError(e.getClass().getName() + "@BD.crearTablas(): " + e.getMessage());
        }
        return creadas;

    }
}

