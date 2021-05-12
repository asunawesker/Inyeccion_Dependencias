/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstudentlate file, choose Tools | Tstudentlates
 * and open the tstudentlate in the editor.
 */
package inyecciondependencias;

import database.ConnectionDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        
/*        Persona p1 = new Persona();
        p1.setClave(2);
        p1.setNombre("Maricela Cruz Arenas");
        p1.setDireccion("Mártires");
        p1.setTelefono("2721671501");
        
        DaoPersona personaDao = new DaoPersona();
        
        personaDao.create(p1);*/

//        DaoPersona personaEliminada = new DaoPersona();
//        
//        personaEliminada.delete(2);

        DaoPersona personas =new DaoPersona();
        List ls = new ArrayList();
        ls = personas.readAll();
        
        verPersonas(ls);
                        
        System.out.println(personas.readSingle(1).getNombre());
        
    }
    
    public static void verPersonas(List<Persona> personas){
        personas.forEach(persona -> {
            System.out.println("Nombre: "+persona.getNombre());
        });
    }
}
