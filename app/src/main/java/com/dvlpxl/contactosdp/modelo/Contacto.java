package com.dvlpxl.contactosdp.modelo;

import java.util.Map;

public class Contacto {

    /**
     * id del contacto.
     */
    private int id;

    /**
     * Nombre del contacto.
     */
    private String nombre;
    
    /**
     * Apellido/s del contacto.
     */
    private String apellido;
    
    /**
     * Telefono fijo.
     */
    private String telefonoFijo;

    /**
     * Telefono movil.
     */
    private String telefonoMovil;
    
    /**
     * Email del contacto.
     */
    private String email;
    
    /**
     * Dirección del contacto.
     */
    private String direccion;
    
    /**
     * Ruta a la imagen del contacto.
     */
    private String imagen;
    
    /**
     * Inicializa un contacto vacío.
     */
    public Contacto() {}

    public Contacto(
            String nombre,
            String apellido,
            String telefonoFijo,
            String telefonoMovil,
            String email,
            String direccion,
            String imagen
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.email = email;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public Contacto(
            int id,
            String nombre,
            String apellido,
            String telefonoFijo,
            String telefonoMovil,
            String email,
            String direccion,
            String imagen
    ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.email = email;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }
    public void setTelefonoFijo(String telefonoFijo) { this.telefonoFijo = telefonoFijo; }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }
    public void setTelefonoMovil(String telefonoMovil) { this.telefonoMovil = telefonoMovil; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
