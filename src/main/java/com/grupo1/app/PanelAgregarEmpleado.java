package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import com.grupo1.app.Clases.EmpleadoFijo;
import com.grupo1.app.Clases.Empresa;

public class PanelAgregarEmpleado extends JPanel { // lo moví acá, se estaba volviendo muy grande el SistemaGestionEmpleados.java
    private final Empresa empresa;
    private final Runnable alGuardarExitosamente;

    public PanelAgregarEmpleado(Empresa empresa, Runnable alGuardarExitosamente) {
        this.empresa = empresa;
        this.alGuardarExitosamente = alGuardarExitosamente;
        setOpaque(false);
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        JPanel formularioPanel = new JPanel(new GridBagLayout());
        formularioPanel.setOpaque(false);
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNombre = new JTextField(15);
        JTextField txtApellidos = new JTextField(15);
        JTextField txtDui = new JTextField(15);
        JTextField txtSalarioBase = new JTextField(15);
        JTextField txtSalarioMensual = new JTextField(15);
        JButton btnGuardar = new JButton("Guardar Empleado Fijo");

        // Fila 0: Nombre
        c.gridx = 0; c.gridy = 0; formularioPanel.add(new JLabel("Nombre:"), c);
        c.gridx = 1; formularioPanel.add(txtNombre, c);

        // Fila 1: Apellidos
        c.gridx = 0; c.gridy = 1; formularioPanel.add(new JLabel("Apellidos:"), c);
        c.gridx = 1; formularioPanel.add(txtApellidos, c);

        // Fila 2: DUI
        c.gridx = 0; c.gridy = 2; formularioPanel.add(new JLabel("DUI:"), c);
        c.gridx = 1; formularioPanel.add(txtDui, c);

        // Fila 3: Salario Base
        c.gridx = 0; c.gridy = 3; formularioPanel.add(new JLabel("Salario Base ($):"), c);
        c.gridx = 1; formularioPanel.add(txtSalarioBase, c);

        // Fila 4: Salario Mensual
        c.gridx = 0; c.gridy = 4; formularioPanel.add(new JLabel("Salario Mensual Fijo ($):"), c);
        c.gridx = 1; formularioPanel.add(txtSalarioMensual, c);

        // Fila 5: Botón Guardar
        c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        formularioPanel.add(btnGuardar, c);

        btnGuardar.addActionListener(e -> {
            try {
                String nom = txtNombre.getText();
                String ape = txtApellidos.getText();
                String dui = txtDui.getText();
                double base = Double.parseDouble(txtSalarioBase.getText().replace(",", ""));
                double mensual = Double.parseDouble(txtSalarioMensual.getText().replace(",", ""));

                EmpleadoFijo nuevoEmpleado = new EmpleadoFijo(nom, ape, dui, base, mensual);
                empresa.agregarEmpleado(nuevoEmpleado);

                // Callback para notificar que la tabla general necesita refrescarse
                if (alGuardarExitosamente != null) {
                    alGuardarExitosamente.run();
                }

                JOptionPane.showMessageDialog(this, "¡Empleado Fijo guardado con éxito!\n" + nuevoEmpleado);

                // Limpiar campos
                txtNombre.setText(""); txtApellidos.setText(""); txtDui.setText("");
                txtSalarioBase.setText(""); txtSalarioMensual.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce valores numéricos válidos en los salarios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(formularioPanel);
    }
}