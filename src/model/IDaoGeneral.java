/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asunawesker
 */
public interface IDaoGeneral <T,V>{
    
    public boolean create(T pojo)
        throws SQLException; 
    
    public boolean delete(V id) 
        throws SQLException; 
    
    public T readSingle(V id) 
        throws SQLException; 
    
    public List<T> readAll() 
        throws SQLException; 
    
    public boolean update(T pojo, V id) 
        throws SQLException;
    
}
