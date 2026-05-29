package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatLaf;

public class PanelConfig extends JPanel {
    
    public PanelConfig() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initUI();
    }

    private void initUI() {
       // Título
        JLabel titleLabel = new JLabel("Configuración");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Separador visual
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        sep.setForeground(UIManager.getColor("Separator.foreground"));
        sep.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(sep);
        add(Box.createRigidArea(new Dimension(0, 12)));

        JPanel comboTemasPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
            c.anchor = GridBagConstraints.CENTER;
            c.weighty = 0.0;
            c.weightx = 1.0;

        JLabel lblTema = new JLabel("Tema de la Interfaz:");
        lblTema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        c.gridx = 0; c.gridy = 0;
        comboTemasPanel.add(lblTema, c);

        String[] opcionesTemas = { "Modo Oscuro (Default)", "Modo Claro" };
        JComboBox<String> comboTemas = new JComboBox<>(opcionesTemas);
        comboTemas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        c.gridx = 1; c.gridy = 0;
        comboTemasPanel.add(comboTemas, c);

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

        JLabel lblNota = new JLabel("Nota: Los cambios se aplican inmediatamente a todas las ventanas.");
        lblNota.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNota.setForeground(UIManager.getColor("Label.disabledForeground")); 
        lblNota.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboTemasPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            comboTemasPanel.setOpaque(false);
            comboTemasPanel.setMaximumSize(new Dimension(400, 100));
        c.gridx = 0; c.gridy = 1; c.gridwidth = 2;
        comboTemasPanel.add(lblNota, c);
        add(comboTemasPanel);
    }   
}