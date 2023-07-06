/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.Vista;

import com.mycompany.Controlador.Connect;
import com.mycompany.musictraining.Canciones;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author dylan
 */
public class PlayMusic extends javax.swing.JFrame {
    public Connection link;
    public Connect connect;
    private Clip cancion;
    private boolean paused=true;
    String query;
    String Category;
    int Id,playing=0,Back=0;
    int count;
    ArrayList<Canciones>SongList=new ArrayList<Canciones>();
    public PlayMusic() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Player");
        Stop.setVisible(false);
    }
    public PlayMusic(String cat,int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Player");
        Category=cat;
        Id=id;
        Stop.setVisible(false);
        connect=new Connect();
        link=connect.ConnectToDB();
        try{
            Statement s=link.createStatement();
            query = "SELECT * FROM Song  WHERE Category = '" + Category + "'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                int idSong=rs.getInt("idSong");
                String songName=rs.getString("Name");
                String author=rs.getString("Author");
                String genre=rs.getString("Genre");
                String category=rs.getString("Category");
                Blob blob = rs.getBlob("audio");
                // Guarda el valor del BLOB en una variable
                byte[] blobData = blob.getBytes(1, (int) blob.length());
                //comvierto el archivo blob a wav para poder reproducirlo
                try {                    
                    File wavFile=File.createTempFile("audio", ".wav");
                    //escribo los datos del blob en el wav
                    FileOutputStream fos=new FileOutputStream(wavFile);
                    fos.write(blobData);
                    fos.close();
                    //reproduzco el wav
                    Canciones songs=new Canciones(idSong,songName,author,genre,category,wavFile);
                    SongList.add(songs);
                } catch (IOException ex) {
                    Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connect.CloseConection();
            Name.setText(SongList.get(count).getName());
            Author.setText(SongList.get(count).getAutor());
            PlaySong();
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
            connect.CloseConection();
        }
    }
    public PlayMusic(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Player");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Id=id;
        Stop.setVisible(false);
        connect=new Connect();
        link=connect.ConnectToDB();
        try{
            Statement s=link.createStatement();
            query = "SELECT * FROM Song JOIN UserSongs ON Song.idSong = UserSongs.idSong WHERE UserSongs.idUser = '" + Id + "'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                int idSong=rs.getInt("idSong");
                String songName=rs.getString("Name");
                String author=rs.getString("Author");
                String genre=rs.getString("Genre");
                String category=rs.getString("Category");
                Blob blob = rs.getBlob("audio");
                // Guarda el valor del BLOB en una variable
                byte[] blobData = blob.getBytes(1, (int) blob.length());
                //comvierto el archivo blob a wav para poder reproducirlo
                try {                    
                    File wavFile=File.createTempFile("audio", ".wav");
                    //escribo los datos del blob en el wav
                    FileOutputStream fos=new FileOutputStream(wavFile);
                    fos.write(blobData);
                    fos.close();
                    //reproduzco el wav
                    Canciones songs=new Canciones(idSong,songName,author,genre,category,wavFile);
                    SongList.add(songs);
                } catch (IOException ex) {
                    Logger.getLogger(MyLibrary.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            connect.CloseConection();
            Name.setText(SongList.get(count).getName());
            Author.setText(SongList.get(count).getAutor());
            PlaySong();
        }catch(SQLException e){
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, e);
            connect.CloseConection();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Play = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        Previous = new javax.swing.JButton();
        Name = new javax.swing.JLabel();
        Author = new javax.swing.JLabel();
        Favourite = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\MusicTraining.png")); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 350, 60));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\Logo.png")); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 140, 130));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 650, 10));

        Play.setBackground(new java.awt.Color(0, 0, 0));
        Play.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\play.png")); // NOI18N
        Play.setBorderPainted(false);
        Play.setContentAreaFilled(false);
        Play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Play.setFocusPainted(false);
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });
        jPanel1.add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 90, 70));

        Stop.setBackground(new java.awt.Color(0, 0, 0));
        Stop.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\stop.png")); // NOI18N
        Stop.setBorder(null);
        Stop.setBorderPainted(false);
        Stop.setContentAreaFilled(false);
        Stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Stop.setEnabled(false);
        Stop.setFocusPainted(false);
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });
        jPanel1.add(Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 90, 70));

        Next.setBackground(new java.awt.Color(0, 0, 0));
        Next.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\next.png")); // NOI18N
        Next.setBorderPainted(false);
        Next.setContentAreaFilled(false);
        Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Next.setFocusPainted(false);
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });
        jPanel1.add(Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 100, 80));

        Previous.setBackground(new java.awt.Color(0, 0, 0));
        Previous.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\previous.png")); // NOI18N
        Previous.setBorderPainted(false);
        Previous.setContentAreaFilled(false);
        Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Previous.setFocusPainted(false);
        Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousActionPerformed(evt);
            }
        });
        jPanel1.add(Previous, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 110, 80));

        Name.setBackground(new java.awt.Color(0, 0, 0));
        Name.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 300, 40));

        Author.setBackground(new java.awt.Color(0, 0, 0));
        Author.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Author.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.add(Author, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 170, 30));

        Favourite.setBackground(new java.awt.Color(0, 0, 0));
        Favourite.setIcon(new javax.swing.ImageIcon("C:\\Users\\dylan\\OneDrive\\Documentos\\NetBeansProjects\\MusicTraining\\src\\main\\java\\images\\favourite.png")); // NOI18N
        Favourite.setBorderPainted(false);
        Favourite.setContentAreaFilled(false);
        Favourite.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Favourite.setFocusPainted(false);
        Favourite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FavouriteActionPerformed(evt);
            }
        });
        jPanel1.add(Favourite, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 100, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        // TODO add your handling code here:
        //reproducir cancion
        Play.setVisible(false);
        Play.setEnabled(false);
        Stop.setEnabled(true);
        Stop.setVisible(true);
        if(playing==0){
            PlaySong();
            playing++;
        }
        
        if (cancion != null && !cancion.isRunning()) {
            if (paused) {
                cancion.start();
                paused = false;
            } 
            else {
                cancion.setFramePosition(0);
                cancion.start();
            }
        }
        
    }//GEN-LAST:event_PlayActionPerformed
    public void PlaySong(){
        
        try {
            AudioInputStream aui=AudioSystem.getAudioInputStream(SongList.get(count).getAudio());
            try{
                Clip clip=AudioSystem.getClip();
                clip.open(aui);
                cancion=clip;
                Name.setText(SongList.get(count).getName());
                Author.setText(SongList.get(count).getAutor());
            }catch(IOException | LineUnavailableException e){
            }
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(PlayMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        // TODO add your handling code here:
        Stop.setVisible(false);
        Stop.setEnabled(false);
        Play.setVisible(true);
        Play.setEnabled(true);
        if (cancion!=null && cancion.isRunning()) {
            cancion.stop();
            paused=true;
        }
    }//GEN-LAST:event_StopActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        cancion.stop();
        count++;
        Back=0;
        Play.setVisible(false);
        Play.setEnabled(false);
        Stop.setEnabled(true);
        Stop.setVisible(true);
        PlaySong();
        cancion.setFramePosition(0);
        cancion.start();
        if(count+1==SongList.size()){
            Next.setVisible(false);
            Next.setEnabled(false);
        }
        else{
            Next.setVisible(true);
            Next.setEnabled(true);
        }
    }//GEN-LAST:event_NextActionPerformed

    private void PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousActionPerformed
        // TODO add your handling code here:
        if(count!=0 && Back==1){
            cancion.stop();
            count--;
            Back=0;
            Next.setVisible(true);
            Next.setEnabled(true);
            Play.setVisible(false);
            Play.setEnabled(false);
            Stop.setEnabled(true);
            Stop.setVisible(true);
            PlaySong();
            cancion.setFramePosition(0);
            cancion.start();
        }
        
        else{
            cancion.stop();
            cancion.setFramePosition(0);
            cancion.start();
            Back=1;
        }
        
    }//GEN-LAST:event_PreviousActionPerformed

    private void FavouriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FavouriteActionPerformed
        // TODO add your handling code here:
        Canciones song=new Canciones();
        if(song.AddSong(Id, SongList.get(count).getId())){
            JOptionPane.showMessageDialog(null, "Cancion agregada!");
        }
    }//GEN-LAST:event_FavouriteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (cancion!=null && cancion.isRunning())
            cancion.stop();
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlayMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayMusic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayMusic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Author;
    private javax.swing.JButton Favourite;
    private javax.swing.JLabel Name;
    private javax.swing.JButton Next;
    private javax.swing.JButton Play;
    private javax.swing.JButton Previous;
    private javax.swing.JButton Stop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
