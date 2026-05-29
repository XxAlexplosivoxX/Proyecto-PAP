package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.grupo1.app.Clases.Empleado;
import com.grupo1.app.Clases.EmpleadoFijo;
import com.grupo1.app.Clases.Empresa;

public class PanelInfoEmpleados extends JPanel {
    private final Empresa empresa;
    private DefaultTableModel modeloTabla;

    public PanelInfoEmpleados(Empresa empresa) {
        this.empresa = empresa;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initUI();
    }

    private void initUI() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);

         // Título
        JLabel titleLabel = new JLabel("Info Empleados");
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

        modeloTabla = new DefaultTableModel(
                new Object[][] {},
                new String[] { "DUI", "Nombre", "Apellidos", "Salario Calculado", "Tipo Empleado" });

        JTable tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaEmpleados.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        infoPanel.add(scrollPane);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(infoPanel);
        actualizarTabla();
    }

    public void actualizarTabla() {
        if (modeloTabla == null)
            return;
        modeloTabla.setRowCount(0);
        for (Empleado emp : empresa.getEmpleados()) {
            String tipo = (emp instanceof EmpleadoFijo) ? "Fijo" : "Por Hora";
            modeloTabla.addRow(new Object[] {
                    emp.getDui(),
                    emp.getNombre(),
                    emp.getApellidos(),
                    emp.calcularSalario(),
                    tipo
            });
        }
    }
}