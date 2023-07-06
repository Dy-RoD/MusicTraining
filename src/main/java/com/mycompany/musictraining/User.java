/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.musictraining;

import com.mycompany.Vista.Login;
import java.util.ArrayList;

/**
 *
 * @author dylan
 */
public class User {
    private int Id;
    private String Username;
    private String Password;
    private String Email;
    
    public ArrayList<Integer> IdCancion = new ArrayList<Integer>();
    
    public User(){
        
    }

    public User(String Username, String Password, String Email) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    
    public ArrayList<Integer> getIdCancion() {
        return IdCancion;
    }

    public void setIdCancion(ArrayList<Integer> IdCancion) {
        this.IdCancion = IdCancion;
    }
    
    

    
    
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

   
    public void setUsername(String username) {
        this.Username = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    
}
