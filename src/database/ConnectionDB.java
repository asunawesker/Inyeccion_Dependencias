package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asunawesker
 */
public class ConnectionDB {
    
    private static ConnectionDB connectionInstance = null;
    private Connection connectionDB = null;
    
    private final String URL = "jdbc:postgresql://localhost:5432/inyec_depen";
    private final String USER = "postgres";
    private final String PASSWORD = "yamaha112";
    
    private ConnectionDB() {
        try {
            connectionDB = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Felicidades, no eres tan inútil");            
        } catch(SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.WARNING, null, e);            
        }
    }
    
    public static ConnectionDB getInstance() throws SQLException{
        if (connectionInstance == null) 
            connectionInstance = new ConnectionDB();                   
        return connectionInstance;
    }
    
    public boolean execute(String sql) {
        // The Statement interface provides methods to execute queries with the database.
        Statement statement;
        boolean response = false;
            
        try {
            statement = connectionDB.createStatement();
            //  It's used to execute queries that may return multiple results.
            // Use this method to execute SQL DDL statements or when you need to use the truly dynamic SQL.
            statement.execute(sql);
            response = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    public boolean execute(TransactionDB transaction) {
        boolean response = transaction.execute(connectionDB);
        return response;
    }
}
