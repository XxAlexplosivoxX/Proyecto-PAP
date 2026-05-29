package com.grupo1.app;

import java.awt.*;
import javax.swing.*;

import com.grupo1.app.Clases.EmpleadoNoEncontradoException;
import com.grupo1.app.Clases.Empresa;

public class PanelEliminarEmpleado extends JPanel {
    private final Empresa empresa;
    private final Runnable alGuardarExitosamente;

    public PanelEliminarEmpleado(Empresa empresa, Runnable alGuardarExitosamente) { // Constructo para el panel pa eliminar empleados
        this.empresa = empresa;
        this.alGuardarExitosamente = alGuardarExitosamente;
        setOpaque(false);
        // Panel principal: usar BoxLayout vertical para simplicidad y mejor control del espaciado
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initUI();
    }

    private void initUI() {
        // Título
        JLabel titleLabel = new JLabel("Eliminar Empleados");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        // Separador visual
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(sep);
        add(Box.createRigidArea(new Dimension(0, 12)));

        // Subpanel con etiqueta + campo y boton bajo ellos
        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new BoxLayout(form, BoxLayout.X_AXIS));
        form.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta y campo DUI en un panel para mantenerlos juntos
        JPanel duiPanel = new JPanel();
        duiPanel.setOpaque(false);
        duiPanel.setLayout(new BoxLayout(duiPanel, BoxLayout.X_AXIS));
        duiPanel.add(new JLabel("DUI: "));
        duiPanel.add(Box.createRigidArea(new Dimension(6, 0)));
        JTextField txtDui = new JTextField(15);
        // Limitar el tamaño para que no se estire ocupando todo el espacio disponible
        txtDui.setMaximumSize(new Dimension(150, txtDui.getPreferredSize().height));
        txtDui.setPreferredSize(new Dimension(150, txtDui.getPreferredSize().height));
        duiPanel.add(txtDui);

        form.add(duiPanel);
        add(form);

        add(Box.createRigidArea(new Dimension(0, 12)));

        // Botón de acción alineado a la izquierda
        JButton btnEliminar = new JButton("Eliminar empleado");
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(btnEliminar);
        btnEliminar.addActionListener(e -> {
            try {
                String dui = txtDui.getText();
                empresa.eliminarEmpleado(dui);
                JOptionPane.showMessageDialog(this, "Empleado con el DUI \"" + dui + "\" Eliminado exitosamente", "Empleado eliminado", JOptionPane.INFORMATION_MESSAGE);
                if (alGuardarExitosamente != null) alGuardarExitosamente.run();
                txtDui.setText("");
            } catch (EmpleadoNoEncontradoException ex) {
                JOptionPane.showMessageDialog(this, "El empleado con ese DUI no existe, no se puede elimnar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
