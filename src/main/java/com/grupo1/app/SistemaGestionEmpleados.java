package com.grupo1.app;

import java.awt.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf; // no c si queda esee
// import com.formdev.flatlaf.FlatLightLaf;

public class SistemaGestionEmpleados extends JFrame // Clase principal
{
    private JPanel contenedorPrincipal;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
        } catch (Exception ex) {
            System.err.println("Error al inicializar FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            new SistemaGestionEmpleados().setVisible(true);
        });
    }

    private JPanel crearPanel(String texto) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 17));
        etiqueta.setForeground(Color.WHITE);
        
        panel.add(etiqueta);
        return panel;
    }

    private SistemaGestionEmpleados() {
        setTitle("Sistema de Gestión de Empleados - Grupo 1");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // layout principal para cambiar entre paneles
        cardLayout = new CardLayout();
        contenedorPrincipal = new JPanel(cardLayout);

        // crear paneles para cada sección (luego modifico la lógica para que quede mejorrr)
        JPanel panelInicio = crearPanel("Bienvenido al Sistema de Gestión de Empleados, todos los textos son un placeholder xdd");
        JPanel panelConfigEmpresa = crearPanel("pa configurar la empresa instanciada al ejecutar la wea");
        JPanel panelAddEmpleados = crearPanel("pa añadir empleados a la empresa");
        JPanel panelInfoEmpleados = crearPanel("aqui se debe ver la info de los empleados");
        JPanel panelElimEmpleados = crearPanel("aqui se eliminan los empleados po");

        contenedorPrincipal.add(panelInicio, "Inicio");
        contenedorPrincipal.add(panelConfigEmpresa, "Configurar Empresa");
        contenedorPrincipal.add(panelAddEmpleados, "Agregar Empleados");
        contenedorPrincipal.add(panelInfoEmpleados, "Información Empleados");
        contenedorPrincipal.add(panelElimEmpleados, "Eliminar Empleados");

        // La barra de menú q queda arriba d chill
        JMenuBar menuBar = new JMenuBar();
        JMenuItem Inicio = new JMenuItem("Inicio");
        JMenuItem confEmpresa = new JMenuItem("Configurar Empresa");
        JMenuItem addEmpleados = new JMenuItem("Agregar Empleados");
        JMenuItem infoEmpleados = new JMenuItem("Información Empleados");
        JMenuItem elimEmpleados = new JMenuItem("Eliminar Empleados");

        // los ActionListeners para cambiar entre paneles desde el menu d arriba
        Inicio.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Inicio"));
        confEmpresa.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Configurar Empresa"));
        addEmpleados.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Agregar Empleados"));
        infoEmpleados.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Información Empleados"));
        elimEmpleados.addActionListener(e -> cardLayout.show(contenedorPrincipal, "Eliminar Empleados"));

        menuBar.add(Inicio);
        menuBar.add(confEmpresa);
        menuBar.add(addEmpleados);
        menuBar.add(infoEmpleados);
        menuBar.add(elimEmpleados);
        setJMenuBar(menuBar);

        add(contenedorPrincipal, BorderLayout.CENTER);
    }
}
