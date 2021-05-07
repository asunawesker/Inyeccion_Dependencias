/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.ConnectionDB;
import database.TransactionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asunawesker
 */
public class DaoPersona implements IDaoGeneral<Persona, Integer>{
    
    private PreparedStatement pst;
    
    private final String[] QUERIES = {
        "INSERT INTO persona (clave, nombre, direccion, telefono) VALUES (?, ?, ?, ?)",
        "DELETE FROM persona WHERE clave= ?",
        "SELECT * FROM persona WHERE clave= ?",
        "SELECT * FROM persona",
        "UPDATE persona SET crs_name = ? WHERE (clave= ?)"
    };

    @Override
    public boolean create(Persona pojo) throws SQLException {
        TransactionDB t ;
        
        t = new TransactionDB<Persona>(pojo) {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                
                try {
                    pst = connection.prepareStatement(QUERIES[0]);
                    
                    pst.setLong(1, this.pojo.getClave());
                    pst.setString(2, this.pojo.getNombre());
                    pst.setString(3, this.pojo.getDireccion());
                    pst.setString(4, this.pojo.getTelefono());
                    
                    int rows = pst.executeUpdate(); 
                    
                    System.out.println("Persona guardada");
                    
                    response = true;
                    
                    return response;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return response;
            }
        };
        
        ConnectionDB connection = ConnectionDB.getInstance();
        boolean response = connection.execute(t);
        return response;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona readSingle(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Persona pojo, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
