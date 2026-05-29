package com.grupo1.app;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class PanelInicio extends JPanel {
    public PanelInicio() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initUI();
    }

    private void initUI() {
        // Título
        JLabel titleLabel = new JLabel("Bienvenido al Sistema de Gestión de Empleados");
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

        JLabel integrantesLabel = new JLabel("Hecho por:");
        integrantesLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        integrantesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(integrantesLabel);

        JPanel panelInicio = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // GIF Animado a la izquierda (columna 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER; // ocupará tantas filas como le sean posibles
        gbc.anchor = GridBagConstraints.NORTHWEST;
        URL gifAnimadoUwU = getClass().getResource("/img/aaaaaaaaaaaa.gif");
        if (gifAnimadoUwU != null) {
            panelInicio.add(new JLabel(new ImageIcon(gifAnimadoUwU)), gbc);
        } else {
            System.err.println("No se pudo cargar el GIF animado");
        }

        // Integrantes a la derecha (columna 1)
        String[] integrantes = {
                "Karla Estefani Siguachi Polanco",
                "Yanci Noemy Corea Mendoza", "Marvin Gerardo Melendez Zometa",
                "Wilfredo Alexander Olmedo Portillo"
        };
        gbc.gridx = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;
        for (String integrante : integrantes) {
            panelInicio.add(new JLabel(integrante), gbc);
            gbc.gridy++;
        }
        panelInicio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelInicio.setOpaque(false);
        panelInicio.setMaximumSize(new Dimension(400, 200));
        add(panelInicio);
    }
}