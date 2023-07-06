/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.musictraining.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author dylan
 */

public class Users implements UsersDB{
    public String query;
    
    @Override
    public ArrayList<User> Read(Connection link) {
        try{
            Statement s=link.createStatement();
            query="select * from Users";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                User user=new User();
                user.setId(rs.getInt("idUser"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                UserList.add(user);
            }
            return UserList;
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    
    @Override
    public boolean Create(User user) {
        Connect connect=new Connect();
        try (Connection link = connect.ConnectToDB()) {
            if (link != null) {
                Statement s = link.createStatement();
                query = "insert into Users(Username,Password,Email) values ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getEmail() + "')";
                s.executeUpdate(query);
                UserList.add(user);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error: No se pudo establecer la conexión");
            }
        } catch (SQLException e) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, "aqui error: ", e);
        }
        return false;
    }

    @Override
    public boolean Update(User user) {
        Connect connect=new Connect();
        try (Connection link = connect.ConnectToDB()) {
            Statement s=link.createStatement();
            query="UPDATE Users set Password='"+user.getPassword()+"' where Username='"+user.getUsername()+"'";
            s.executeUpdate(query);
            int count=0;
            for(User u:UserList){
                if(u.getUsername().equals(user.getUsername())){
                    UserList.get(count).setPassword(user.getPassword());
                    return true;
                }
            }
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;        
    }

    @Override
    //String username,String password, String mail
    public boolean Delete(int id) {
        Connect connect=new Connect();
        try (Connection link = connect.ConnectToDB()) {
            for(User u: UserList){
                //u.getUsername().equals(username)
                if(u.getId()==id){
                    Statement s=link.createStatement();
                    query = "DELETE Users, UserSongs FROM Users INNER JOIN UserSongs ON Users.idUser = UserSongs.idUser WHERE Users.idUser = '" + id + "'";
                    //query="delete from Users where Username='" + username + "' AND Password='" + password + "' AND Email='" + mail + "'";
                    s.executeUpdate(query);
                    UserList.remove(u);
                    Users user=new Users();
                    user.Read(link);
                    return true;
                }
            }
            
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;  
    }

    public boolean checkUserExists(Connection link,String username) {
        try(link) {
            Statement s=link.createStatement();
            query="select * from Users where Username='"+username+"'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, "AQuierror: "+e);
        }
        return false;
    }

    @Override
    public User SearchUser(Connection link, String username,String email) {
        User user=new User();
        try{
            Statement s=link.createStatement();
            query="select * from Users where Username='"+username+"' and Email='"+email+"'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                user.setId(rs.getInt("idUser"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                return user;
            }
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    public int SingIn(Connection link,String username, String password){
        int count=0;
        int Id=-1;
        try{
            Statement s=link.createStatement();
            query="select * from Users where Username='"+username+"' and Password='"+password+"'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                count++;
                Id=rs.getInt("idUser");
            }
            if(count>0) return Id;
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
        }
        return Id;
    }

}