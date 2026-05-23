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
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Info Empleados");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        infoPanel.add(titleLabel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new Object[][] {}, 
                new String[] { "Nombre", "Apellidos", "DUI", "Salario Calculado", "Tipo Empleado" }
        );

        JTable tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablaEmpleados.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        add(infoPanel);
        actualizarTabla();
    }

    public void actualizarTabla() {
        if (modeloTabla == null) return;
        modeloTabla.setRowCount(0);
        for (Empleado emp : empresa.getEmpleados()) {
            String tipo = (emp instanceof EmpleadoFijo) ? "Fijo" : "Por Hora";
            modeloTabla.addRow(new Object[] {
                    emp.getNombre(),
                    emp.getApellidos(),
                    emp.getDui(),
                    emp.calcularSalario(),
                    tipo
            });
        }
    }
}