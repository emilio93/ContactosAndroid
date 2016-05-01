package com.dvlpxl.contactosdp.modelo;

import java.util.Map;

public class Contacto {
    /**
     * Nombre del contacto.
     */
    private String nombre;
    
    /**
     * Apellido/s del contacto.
     */
    private String apellido;
    
    /**
     * Tipos de teléfono(móvil, casa, fax, etc) y números del contacto.
     */
    private Map<String, String> telefonos;
    
    /**
     * Tipos de email(personal, trabajo, etc) y emails del contacto.
     */
    private Map<String, String> emails;
    
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

    /**
     * Asigna todos los valores del contacto
     * @param nombre Nombre del contacto.
     * @param apellido Apellido/s del contacto.
     * @param telefonos Tipos de teléfono(móvil, casa, fax, etc) y números del contacto.
     * @param emails Tipos de email(personal, trabajo, etc) y emails del contacto.
     * @param direccion Dirección del contacto.
     * @param imagen Ruta a la imágen del contacto.
     */
    public Contacto(
            String nombre, 
            String apellido, 
            Map<String, String> telefonos, 
            Map<String, String> emails, 
            String direccion, 
            String imagen
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefonos = telefonos;
        this.emails = emails;
        this.direccion = direccion;
        this.imagen = imagen;
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

    public Map<String, String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Map<String, String> telefonos) {
        this.telefonos = telefonos;
    }

    public Map<String, String> getEmails() {
        return emails;
    }

    public void setEmails(Map<String, String> emails) {
        this.emails = emails;
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

    public Object[] getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
