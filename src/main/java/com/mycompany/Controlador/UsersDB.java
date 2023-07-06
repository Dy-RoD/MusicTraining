/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Controlador;

import com.mycompany.musictraining.User;
import java.sql.Connection;
import java.util.ArrayList;


public interface UsersDB {
    ArrayList<User>UserList=new ArrayList<User>();
    public boolean Create(User user);
    public boolean Update(User user);
    public boolean Delete(int id);
    public ArrayList<User> Read(Connection link);
    public User SearchUser(Connection link, String username, String Mail);
}
