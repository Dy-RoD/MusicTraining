/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

/**
 * coneccion a la base de datos MTBD.sql    
 * @author dylan
 */
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Connect {
   
    private String db;
    private String url;
    private String user;
    private String pass;
    private Connection link ;
    
    public Connect(){
        this.db="mtbd";
        this.url = "jdbc:mysql://localhost/" + db;
        this.user="root";
        this.pass="";
        this.link=null;
        
    }
    
    public Connection ConnectToDB() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            System.out.println("Conected.....");
            
            
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
            JOptionPane.showMessageDialog(null, "Error: No Se Pudo Conectar a La BD");
        }
        
        return link;
    }
    
    
    public void CloseConection(){
        
        try {
            link.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, "Error: No se pudo Cerrar La Coneccion");
        }
        
    }
    
    
    
}
