package com.dvlpxl.contactos.bd;

import com.dvlpxl.modelo.Contacto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContactosBD extends Consultor{
    
    /*
    CREATE
    */
    
    public boolean addContacto(Contacto c) {
        boolean success = false;
        try {
            PreparedStatement stmt = preparar(true, "call addContacto(?, ?, ?, ?)", c.getNombre(),c.getApellido(), c.getDireccion(), c.getImagen());
            success = stmt.executeUpdate() == 1;
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if(rs.next()) id = rs.getInt(1);
            
            if(id != 0) {
               Iterator it = c.getTelefonos().entrySet().iterator();
                while (it.hasNext()) {
                    HashMap.Entry par = (HashMap.Entry)it.next();
                    success = success && addTelefono(id, (String) par.getKey(), (String) par.getValue());
                    it.remove();
                }

                it = c.getEmails().entrySet().iterator();
                while(it.hasNext()) {
                    HashMap.Entry par = (HashMap.Entry)it.next();
                    success = success && addEmail(id, (String) par.getKey(), (String) par.getValue());
                    it.remove();
                } 
            }
            
        } catch (SQLException e) {
            appendError("ExecuteUpdate no ejecutado @ ContactosBD.addContacto(Contacto). ");
        }
        
        return success;
    }
    
    private boolean addEmail(int id, String email, String tipo) {
        boolean success = false;
        try {
            int count = preparar("call addEmail(?, ?, ?)", id, email, tipo).executeUpdate();
            success = count == 1;
        } catch (SQLException e) {
            appendError("ExecuteUpdate no ejecutado @ ContactosBD.addEmail(String, String). ");
        }
        return success;
    }
    
    private boolean addTelefono(int id, String telefono, String tipo) {
        boolean success = false;
        try {
            int count = preparar("call addTelefono(?, ?, ?)", id, telefono, tipo).executeUpdate();
            success = count == 1;
        } catch (SQLException e) {
            appendError("ExecuteUpdate no ejecutado @ ContactosBD.addTelefono(String, String). ");
        }
        return success;
    }
    
    /*
    READ
    */
    
    public Contacto getContacto(int id) {
        ResultSet rs = null;
        Contacto c = null;
        try {
            rs = preparar("call getContacto(?)", id).executeQuery();
            if (rs.next()) {
                c = new Contacto(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    getTelefonos(rs.getInt("id")),
                    getEmails(rs.getInt("id")),
                    rs.getString("direccion"),
                    rs.getString("imagen")
                );
            } else {
                appendError("Usuario no existe. ");
            }
        } catch (SQLException e) {
            appendError("ResultSet no obtenido @ ContactosBD.getContacto(int). ");
        }
        return c;
    }
    
    private List<Contacto> getContactos() {
        ResultSet rs = null;
        List<Contacto> lc = null;
        try {
            rs = preparar("call getContactos()").executeQuery();
            lc = new ArrayList<Contacto>();
            while (rs.next()) {
                lc.add(new Contacto(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    getTelefonos(rs.getInt("id")),
                    getEmails(rs.getInt("id")),
                    rs.getString("direccion"),
                    rs.getString("imagen")
                ));
            }
        } catch (SQLException e) {
            appendError("ResultSet no obtenido @ ContactosBD.getContactos(int). ");
        }
        return lc;
    }
    
    private Map<String,String> getTelefonos(int id) {
        ResultSet rs = null;
        Map telefonos = new HashMap<String, String>();
        try {
            rs = preparar("call getTelefonos(?)", id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    telefonos.put(rs.getString("telefono"), rs.getString("tipo"));
                }
            }
        } catch (SQLException ex) {
            appendError("ResultSet no obtenido @ ContactosBD.getTelefonos(int). ");
        }
       
        return telefonos;
    }
    
    private Map<String,String> getEmails(int id) {
        ResultSet rs = null;
        Map emails = new HashMap<String, String>();
        try {
            rs = preparar("call getEmails(?)", id).executeQuery();
            if (rs != null) {
                while (rs.next()) {
                emails.put(rs.getString("email"), rs.getString("tipo"));
                }
            }
        } catch (SQLException ex) {
            appendError("ResultSet no obtenido @ ContactosBD.getEmails(int). ");
        }
       
        return emails;
    }
    
    /*
    UPDATE
    */
    
    public boolean updateContacto(Contacto c) {
        return false;
    }
    
    /*
    DELETE
    */
    
    public boolean deleteContacto(Contacto c) {
        boolean success = false;
        try {
            PreparedStatement stmt = preparar(true, "call deleteContacto(?)", c.getId());
            success = stmt.executeUpdate() == 1;
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if(rs.next()) id = rs.getInt(1);
            
            if(id != 0) {
               Iterator it = c.getTelefonos().entrySet().iterator();
                while (it.hasNext()) {
                    HashMap.Entry par = (HashMap.Entry)it.next();
                    success = success && addTelefono(id, (String) par.getKey(), (String) par.getValue());
                    it.remove();
                }

                it = c.getEmails().entrySet().iterator();
                while(it.hasNext()) {
                    HashMap.Entry par = (HashMap.Entry)it.next();
                    success = success && addEmail(id, (String) par.getKey(), (String) par.getValue());
                    it.remove();
                } 
            }
            
        } catch (SQLException e) {
            appendError("ExecuteUpdate no ejecutado @ ContactosBD.addContacto(Contacto). ");
        }
        
        return success;
    }
}
