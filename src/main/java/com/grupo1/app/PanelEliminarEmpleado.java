package com.grupo1.app;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEliminarEmpleado extends JPanel {
    public PanelEliminarEmpleado() {
        setOpaque(false);
        initUI();
    }

    private void initUI() {
        add(new JLabel("aqui va la ui para eliminar empleados"));
    }
}
