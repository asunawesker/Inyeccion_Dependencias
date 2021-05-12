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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asunawesker
 */
public class DaoPersona implements IDaoGeneral<Persona, Integer>{
    
    private PreparedStatement  preparedStatement;
    private ConnectionDB connection ;
    private List <Persona> ls;
    private Persona persona;

    public DaoPersona() throws SQLException {
        connection = ConnectionDB.getInstance();
        ls = new ArrayList<>();
    }        
    
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
                    preparedStatement = connection.prepareStatement(QUERIES[0]);
                    
                    preparedStatement.setLong(1, this.pojo.getClave());
                    preparedStatement.setString(2, this.pojo.getNombre());
                    preparedStatement.setString(3, this.pojo.getDireccion());
                    preparedStatement.setString(4, this.pojo.getTelefono());
                    
                    preparedStatement.executeUpdate(); 
                    
                    System.out.println("Persona guardada");
                     
                     response = true;
                     
                     return response;
                 } catch (SQLException ex) {
                     Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 return response;
             }
         };        
         
         boolean response = connection.execute(t);
         return response;
     }            

    @Override
    public boolean delete(Integer id) throws SQLException {
        TransactionDB t;
        
        t = new TransactionDB<Persona>(){
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    preparedStatement = connection.prepareStatement(QUERIES[1]);
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                    
                    response = true;
                    return response;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
                return response;
            }            
        };
        
        boolean response = connection.execute(t);
        
        return response;
    }

    @Override
    public Persona readSingle(Integer id) throws SQLException {
       TransactionDB t;
        
        t = new TransactionDB<Persona>(){
           @Override
           public boolean execute(Connection connection) {
               boolean response = false;
               try {
                   preparedStatement = connection.prepareStatement(QUERIES[2]);
                   preparedStatement.setInt(1, id);
                   ResultSet result = preparedStatement.executeQuery();
                   persona = new Persona();
                   
                   while(result.next()){
                       persona.setClave(result.getInt("clave"));
                        persona.setNombre(result.getString("nombre"));
                        persona.setDireccion(result.getString("direccion"));
                        persona.setTelefono(result.getString("telefono"));
                   }
                   response = true;
                   return response;
               } catch (SQLException ex) {
                   Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
               }
               return response;
           }  
        };
        connection.execute(t);
        return persona;
    }

    @Override
    public List<Persona> readAll() throws SQLException {
        TransactionDB t;
                
        t = new TransactionDB<Persona>() {
            @Override
            public boolean execute(Connection connection) {
                boolean response = false;
                try {
                    Persona persona;
                    
                    preparedStatement = connection.prepareStatement(QUERIES[3]);
                    ResultSet result = preparedStatement.executeQuery();
                    
                    while  (result.next()){
                        persona = new Persona();
                        persona.setClave(result.getInt("clave"));
                        persona.setNombre(result.getString("nombre"));
                        persona.setDireccion(result.getString("direccion"));
                        persona.setTelefono(result.getString("telefono"));
                        ls.add(persona);
                    }
                    response = true;
                    return response;
                } catch (SQLException ex) {
                    Logger.getLogger(DaoPersona.class.getName()).log(Level.SEVERE, null, ex);
                }
                return response;
            }            
        };
         boolean response = connection.execute(t);        
         return ls;
    }

    @Override
    public boolean update(Persona pojo, Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");  //To change body of generated methods, choose Tools | Templates.
    }
        
}
