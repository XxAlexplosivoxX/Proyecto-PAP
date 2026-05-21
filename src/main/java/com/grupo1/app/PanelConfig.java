package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatLaf;

public class PanelConfig extends JPanel {
    
    public PanelConfig() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        initUI();
    }

    private void initUI() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        JLabel lblTitulo = new JLabel("Configuración del Sistema");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(lblTitulo, c);

        c.gridy = 1;
        JSeparator separador = new JSeparator();
        add(separador, c);

        c.gridwidth = 1;

        c.gridx = 0;
        c.gridy = 2;
        JLabel lblTema = new JLabel("Tema de la Interfaz:");
        lblTema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(lblTema, c);

        c.gridx = 1;
        String[] opcionesTemas = { "Modo Oscuro (Default)", "Modo Claro" };
        JComboBox<String> comboTemas = new JComboBox<>(opcionesTemas);
        comboTemas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        add(comboTemas, c);

        comboTemas.addActionListener(e -> {
            int seleccion = comboTemas.getSelectedIndex();
            
            try {
                if (seleccion == 0) {
                    // Configurar el tema oscuro
                    FlatDarkLaf.setup();
                } else {
                    // Configurar el tema claro
                    FlatLightLaf.setup();
                }
                FlatLaf.updateUI(); // Actializa la ui de todo
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error al cambiar el tema visual.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        JLabel lblNota = new JLabel("Nota: Los cambios se aplican inmediatamente a todas las ventanas.");
        lblNota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNota.setForeground(UIManager.getColor("Label.disabledForeground")); 
        add(lblNota, c);
    }   
}