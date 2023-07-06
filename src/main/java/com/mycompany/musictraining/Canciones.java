/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musictraining;

import com.mycompany.Controlador.Connect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author dylan
 */

public class Canciones {
    private int Id;
    private String Name;
    private String Autor;
    private String Genre;
    private String Category;
    private File Audio;
    
    String query;
    public Canciones() {
    }

    public Canciones(String Name, String Autor, String Genre, String Category, File Audio) {
        this.Name = Name;
        this.Autor = Autor;
        this.Genre = Genre;
        this.Category = Category;
        this.Audio = Audio;
    }

    public Canciones(int Id, String Name, String Autor, String Genre, String Category, File Audio) {
        this.Id = Id;
        this.Name = Name;
        this.Autor = Autor;
        this.Genre = Genre;
        this.Category = Category;
        this.Audio = Audio;
    }

    public int getId() {
        return Id;
    }
    
    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    

    public String getName() {
        return Name;
    }

    public void setName(String NombreCancion) {
        this.Name = NombreCancion;
    }

    public File getAudio() {
        return Audio;
    }

    public void setAudio(File Audio) {
        this.Audio = Audio;
    }

    
    public String getAutor() {
        return Autor;
    }

    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    public boolean AddSong(int idUser, int idSong) {
        Connect connect=new Connect();
        try (Connection link = connect.ConnectToDB()) {
            if (link != null) {
                if(SearchSong(link,idUser,idSong)){
                    Statement s = link.createStatement();
                    query = "insert into UserSongs(idUser,idSong) values ('" + idUser + "','" + idSong +"')";
                    s.executeUpdate(query);
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Error: La cancion ya esta en su lista");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, "aqui error: ", e);
        }
        return false;
    }
    
    public boolean SearchSong(Connection link, int idUser, int idSong){
        try{
            Statement s=link.createStatement();
            query="select * from UserSongs where idUser='"+idUser+"' and idSong='"+idSong+"'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                return false;
            }
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return true;
    }
    public void addSongToBD(){
        Connect connect;
        connect=new Connect();
        String query;
        try (Connection link = connect.ConnectToDB()) {
            Statement s=link.createStatement();
            //insert audio in blob data type
            
            String location= "C:\\Users\\dylan\\Downloads\\cancionesProyecto\\yoga\\Peaceful Journeys - meditationMusic.wav";
            File file=new File(location);
            byte[] wavData;
            try(FileInputStream input=new FileInputStream(file);){
                wavData=new byte[(int) file.length()];
                input.read(wavData);
                String insertQuery=query="insert into Song(name,author,genre,category,audio) values (?,?,?,?,?)";
                try(PreparedStatement ps=link.prepareStatement(insertQuery)){
                    ps.setString(1, "Peaceful journeys");
                    ps.setString(2, "Meditation music");
                    ps.setString(3, "Chill Out");
                    ps.setString(4, "Yoga");
                    ps.setBytes(5, wavData);
                    ps.executeUpdate();
                    System.out.println("added!");
                    JOptionPane.showMessageDialog(null, "agregada!");
                    
                }catch(SQLException e){
                    e.printStackTrace();
                }
            
            }catch(IOException e){
                e.printStackTrace();
            }
        }catch (SQLException ex) {
            Logger.getLogger(Canciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
