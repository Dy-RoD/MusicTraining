/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Vista;

/**
 *
 * @author dylan
 */
import javax.swing.*;
import java.awt.*;

public class CustomListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Establecer el color de fondo y el color de texto de las celdas
        renderer.setBackground(list.getBackground());
        renderer.setForeground(list.getForeground());

        // Establecer los márgenes para eliminar los bordes blancos
        ((JLabel) renderer).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        return renderer;
    }
}
