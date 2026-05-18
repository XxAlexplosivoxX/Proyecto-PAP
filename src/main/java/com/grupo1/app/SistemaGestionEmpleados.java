package com.grupo1.app;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

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
        switch (texto) {
            case "config":
                panel.add(new JLabel("pa configurar la empresa instanciada al ejecutar la wea"));
                break;
            case "añadir":
                panel.add(new JLabel("pa añadir empleados a la empresa"));
                break;
            case "info":
                panel.add(new JLabel("aqui se debe ver la info de los empleados"));
                break;
            case "eliminar":
                panel.add(new JLabel("aqui se eliminan los empleados po"));
                break;
            default:
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0; // columna 0
                gbc.insets = new Insets(10, 10, 10, 10); // noma un poquito de espacio/margen alrededor

                // añadir el texto arriba
                gbc.gridy = 0; // fila 0
                JLabel textoBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Empleados - Grupo 1");
                textoBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 17)); // Por si quieres subirle el tamaño
                panel.add(textoBienvenida, gbc);

                gbc.gridy = 1; // fila 1
                // añadir un gif cómicamente gracioso abajo
                URL gifAnimadoUwU = getClass().getResource("/img/aaaaaaaaaaaa.gif");
                if (gifAnimadoUwU != null) {
                    ImageIcon iconoGif = new ImageIcon(gifAnimadoUwU);
                    JLabel etiquetaGif = new JLabel(iconoGif);
                    panel.add(etiquetaGif, gbc);
                } else {
                    System.err.println("No se pudo cargar el GIF animado");
                }

                gbc.gridy = 2; // fila 2
                // añadir los nombres de los integrantes
                String[] integrantes = { "Hecho por:", "Karla Estefani Siguachi Polanco",
                        "Yanci Noemy Corea Mendoza",
                        "Marvin Gerardo Melendez Zometa",
                        "Wilfredo Alexander Olmedo Portillo" };
                for (String integrante : integrantes) {
                    JLabel etiquetaIntegrante = new JLabel(integrante);
                    panel.add(etiquetaIntegrante, gbc);
                    gbc.gridy++; // incrementar la fila para el siguiente nombre
                }

                break;
        }
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

        // crear paneles para cada sección (luego modifico la lógica para que quede
        // mejorrr)
        JPanel panelInicio = crearPanel("cualquier movida lleva a inicio xddd");
        JPanel panelConfigEmpresa = crearPanel("config");
        JPanel panelAddEmpleados = crearPanel("añadir");
        JPanel panelInfoEmpleados = crearPanel("info");
        JPanel panelElimEmpleados = crearPanel("eliminar");

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
