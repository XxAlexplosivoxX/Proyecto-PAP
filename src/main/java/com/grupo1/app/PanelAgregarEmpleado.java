package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import com.grupo1.app.Clases.EmpleadoFijo;
import com.grupo1.app.Clases.EmpleadoPorHora;
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
        JRadioButton radEmpleadoFijo = new JRadioButton("Empleado fijo");
        JRadioButton radEmpleadoPorHora = new JRadioButton("Empleado por hora");
        ButtonGroup tipoGroup = new ButtonGroup();
        tipoGroup.add(radEmpleadoFijo);
        tipoGroup.add(radEmpleadoPorHora);
        radEmpleadoFijo.setSelected(true);

        JTextField txtSalarioBase = new JTextField(15);
        JTextField txtSalarioMensual = new JTextField(15);
        JTextField txtHorasTrabajadas = new JTextField(15);
        JTextField txtTarifaPorHora = new JTextField(15);

        JPanel tarjetaCampos = new JPanel(new CardLayout());
        JPanel panelFijo = new JPanel(new GridBagLayout());
        JPanel panelPorHora = new JPanel(new GridBagLayout());
        
        GridBagConstraints cFijo = new GridBagConstraints();
        cFijo.insets = new Insets(8, 8, 8, 8);
        cFijo.fill = GridBagConstraints.HORIZONTAL;

        cFijo.gridx = 0; cFijo.gridy = 0; panelFijo.add(new JLabel("Salario Mensual Fijo ($):"), cFijo);
        cFijo.gridx = 1; panelFijo.add(txtSalarioMensual, cFijo);

        GridBagConstraints cHora = new GridBagConstraints();
        cHora.insets = new Insets(8, 8, 8, 8);
        cHora.fill = GridBagConstraints.HORIZONTAL;
        
        cHora.gridx = 0; cHora.gridy = 1; panelPorHora.add(new JLabel("Horas Trabajadas:"), cHora);
        cHora.gridx = 1; panelPorHora.add(txtHorasTrabajadas, cHora);
        cHora.gridx = 0; cHora.gridy = 3; panelPorHora.add(new JLabel("Tarifa por Hora ($):"), cHora);
        cHora.gridx = 1; panelPorHora.add(txtTarifaPorHora, cHora);
        
        tarjetaCampos.add(panelFijo, "FIJO");
        tarjetaCampos.add(panelPorHora, "POR_HORA");

        JButton btnGuardar = new JButton("Guardar Empleado");

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
        // c.gridx = 0; c.gridy = 3; formularioPanel.add(new JLabel("Salario Base ($): (ni sé si va aqui lvd)"), c);
        // c.gridx = 1; formularioPanel.add(txtSalarioBase, c);
        
        // Fila 3: Selección de tipo de empleado
        c.gridx = 0; c.gridy = 3; formularioPanel.add(new JLabel("Tipo Empleado:"), c);
        c.gridx = 1; formularioPanel.add(radEmpleadoFijo, c); c.gridx = 2; formularioPanel.add(radEmpleadoPorHora, c);

        // Fila 4: Campos variables según el tipo de empleado
        c.gridx = 0; c.gridy = 4; c.gridwidth = 3;
        formularioPanel.add(tarjetaCampos, c);

        CardLayout cardLayout = (CardLayout) tarjetaCampos.getLayout();
        radEmpleadoFijo.addActionListener(e -> cardLayout.show(tarjetaCampos, "FIJO"));
        radEmpleadoPorHora.addActionListener(e -> cardLayout.show(tarjetaCampos, "POR_HORA"));

        // Fila 5: Botón Guardar
        c.gridx = 0; c.gridy = 5; c.gridwidth = 3;
        c.fill = GridBagConstraints.NONE;
        formularioPanel.add(btnGuardar, c);

        btnGuardar.addActionListener(e -> {
            try {
                String nom = txtNombre.getText();
                String ape = txtApellidos.getText();
                String dui = txtDui.getText();

                if (radEmpleadoFijo.isSelected()) {
                    double salarioMensual = Double.parseDouble(txtSalarioMensual.getText().replace(",", ""));
                    EmpleadoFijo nuevoEmpleado = new EmpleadoFijo(nom, ape, dui, salarioMensual, salarioMensual);
                    empresa.agregarEmpleado(nuevoEmpleado);
                    if (alGuardarExitosamente != null) alGuardarExitosamente.run();
                    JOptionPane.showMessageDialog(this, "¡Empleado Fijo guardado con éxito!\n" + nuevoEmpleado);
                } else {
                    int horasTrabajadas = Integer.parseInt(txtHorasTrabajadas.getText().replace(",", ""));
                    double tarifaPorHora = Double.parseDouble(txtTarifaPorHora.getText().replace(",", ""));
                    EmpleadoPorHora nuevoEmpleado = new EmpleadoPorHora(nom, ape, dui, horasTrabajadas * tarifaPorHora, horasTrabajadas, tarifaPorHora);
                    empresa.agregarEmpleado(nuevoEmpleado);
                    if (alGuardarExitosamente != null) alGuardarExitosamente.run();
                    JOptionPane.showMessageDialog(this, "¡Empleado por hora guardado con éxito!\n" + nuevoEmpleado);
                }

                // Limpiar campos
                txtNombre.setText(""); txtApellidos.setText(""); txtDui.setText("");
                txtSalarioBase.setText(""); txtSalarioMensual.setText(""); txtHorasTrabajadas.setText(""); txtTarifaPorHora.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce valores numéricos válidos en los salarios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(formularioPanel);
    }
}