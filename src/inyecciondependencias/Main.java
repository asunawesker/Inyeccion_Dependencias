/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstudentlate file, choose Tools | Tstudentlates
 * and open the tstudentlate in the editor.
 */
package inyecciondependencias;

import database.ConnectionDB;
import java.sql.SQLException;
import model.DaoPersona;
import model.Persona;

/**
 *
 * @author asunawesker
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        ConnectionDB connectionDB = ConnectionDB.getInstance();
        
        Persona p1 = new Persona();
        p1.setClave(1);
        p1.setNombre("Irais Aguirre Valente");
        p1.setDireccion("Norte 16");
        p1.setTelefono("2721670898");
        
        DaoPersona personaDao = new DaoPersona();
        
        personaDao.create(p1);
        
    }
    
}
