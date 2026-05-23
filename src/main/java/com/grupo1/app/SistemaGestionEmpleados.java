package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.FlatDarkLaf;
import com.grupo1.app.Clases.Empresa;

public class SistemaGestionEmpleados extends JFrame {
    private JPanel contenedorPrincipal;
    private CardLayout cardLayout;
    private Empresa empresa;

    // Guardamos referencias directas a las vistas modulares
    private PanelInfoEmpleados panelInfo;
    private PanelAgregarEmpleado panelAgregar;
    private PanelConfig panelConfig;
    private PanelEliminarEmpleado panelEliminar;

    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception ex) {
            System.err.println("Error al inicializar FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            SistemaGestionEmpleados sistema = new SistemaGestionEmpleados();
            sistema.setVisible(true);
        });
    }

    public SistemaGestionEmpleados() {
        setTitle("Sistema de Gestión de Empleados");
        setSize(900, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        empresa = new Empresa();
        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        // Inicializar los componentes modulares
        panelInfo = new PanelInfoEmpleados(empresa);
        // Le pasamos un callback funcional (Runnable) para actualizar la tabla en vivo
        // al guardar
        panelAgregar = new PanelAgregarEmpleado(empresa, () -> panelInfo.actualizarTabla());
        panelConfig = new PanelConfig();
        panelEliminar = new PanelEliminarEmpleado();

        // Registrar paneles en el CardLayout
        contenedorPrincipal.add(new PanelInicio(), "Inicio");
        contenedorPrincipal.add(panelConfig, "Configuración");
        contenedorPrincipal.add(panelAgregar, "Agregar Empleados");
        contenedorPrincipal.add(panelInfo, "Información Empleados");
        contenedorPrincipal.add(panelEliminar, "Eliminar Empleados");

        configurarMenuBar(contenedorPrincipal, cardLayout);
        add(contenedorPrincipal, BorderLayout.CENTER);
    }

    private void configurarMenuBar(JPanel contenedorPrincipal, CardLayout cardLayout) {
        JMenuBar menuBar = new JMenuBar();

        java.net.URL logoUrl = getClass().getClassLoader().getResource("img/cboya.jpg");
        if (logoUrl != null) {
            ImageIcon original = new ImageIcon(logoUrl);
            int h = 36; // tamaño del lodo de la cboya XD
            int w = (int) ((double) original.getIconWidth() / original.getIconHeight() * h);
            Image scaled = original.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaled));
            logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            menuBar.add(logoLabel);
        }

        JMenu menuEmpleados = new JMenu("Gestión de Empleados");
        JMenu menuOpciones = new JMenu("Opciones");

        // sección de menú para gestionar empleados
        JMenuItem itemInicio = new JMenuItem("Inicio");
        JMenuItem itemAddEmpleados = new JMenuItem("Agregar Empleados");
        JMenuItem itemInfoEmpleados = new JMenuItem("Información Empleados");
        JMenuItem itemElimEmpleados = new JMenuItem("Eliminar Empleados");

        // sección del menu para opciones
        JMenuItem itemConf = new JMenuItem("Configuración");

        // Al cambiar a la pestaña de "Información Empleados", forzamos un refresco por
        // si acaso
        itemInfoEmpleados.addActionListener(e -> {
            panelInfo.actualizarTabla();
            cardLayout.show(contenedorPrincipal, "Información Empleados");
        });

        itemInicio.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Inicio"));
        // itemConfEmpresa.addActionListener(e -> cardLayout.show(contenedorPrincipal,
        // "Configurar Empresa"));
        itemAddEmpleados.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Agregar Empleados"));
        itemElimEmpleados.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Eliminar Empleados"));
        itemConf.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Configuración"));

        menuEmpleados.add(itemInicio);
        menuEmpleados.add(itemAddEmpleados);
        menuEmpleados.add(itemInfoEmpleados);
        menuEmpleados.add(itemElimEmpleados);

        menuOpciones.add(itemConf);

        menuBar.add(menuEmpleados);
        menuBar.add(menuOpciones);

        menuBar.add(Box.createHorizontalGlue());
        JLabel tituloApp = new JLabel("Grupo 1");
        tituloApp.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        menuBar.add(tituloApp);

        setJMenuBar(menuBar);
    }
}