package com.grupo1.app;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class PanelInicio extends JPanel {
    public PanelInicio() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Título
        gbc.gridy = 0;
        JLabel textoBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Empleados - Grupo 1");
        textoBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 17));
        add(textoBienvenida, gbc);

        // GIF Animado
        gbc.gridy = 1;
        URL gifAnimadoUwU = getClass().getResource("/img/aaaaaaaaaaaa.gif");
        if (gifAnimadoUwU != null) {
            add(new JLabel(new ImageIcon(gifAnimadoUwU)), gbc);
        } else {
            System.err.println("No se pudo cargar el GIF animado");
        }

        // Integrantes
        gbc.gridy = 2;
        String[] integrantes = { 
            "Hecho por:", "Karla Estefani Siguachi Polanco",
            "Yanci Noemy Corea Mendoza", "Marvin Gerardo Melendez Zometa",
            "Wilfredo Alexander Olmedo Portillo" 
        };
        for (String integrante : integrantes) {
            add(new JLabel(integrante), gbc);
            gbc.gridy++;
        }
    }
}