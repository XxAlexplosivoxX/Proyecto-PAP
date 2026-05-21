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
                // 1. Crear el panel del formulario con un diseño ordenado (GridBagLayout)
                JPanel formularioPanel = new JPanel(new GridBagLayout());
                formularioPanel.setOpaque(false);
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(8, 8, 8, 8); // Margen entre los componentes
                c.fill = GridBagConstraints.HORIZONTAL;

                // 2. Crear las etiquetas y las cajas de texto para tus atributos
                JLabel lblNombre = new JLabel("Nombre:");
                JTextField txtNombre = new JTextField(15);

                JLabel lblApellidos = new JLabel("Apellidos:");
                JTextField txtApellidos = new JTextField(15);

                JLabel lblDui = new JLabel("DUI:");
                JTextField txtDui = new JTextField(15);

                JLabel lblSalarioBase = new JLabel("Salario Base ($):");
                JTextField txtSalarioBase = new JTextField(15);

                JLabel lblSalarioMensual = new JLabel("Salario Mensual Fijo ($):");
                JTextField txtSalarioMensual = new JTextField(15);

                JButton btnGuardar = new JButton("Guardar Empleado Fijo");

                // 3. Posicionar los componentes en la cuadrícula (Fila por fila)
                c.gridx = 0; c.gridy = 0; formularioPanel.add(lblNombre, c);
                c.gridx = 1; formularioPanel.add(txtNombre, c);

                c.gridx = 0; c.gridy = 1; formularioPanel.add(lblApellidos, c);
                c.gridx = 1; formularioPanel.add(txtApellidos, c);

                c.gridx = 0; c.gridy = 2; formularioPanel.add(lblDui, c);
                c.gridx = 1; formularioPanel.add(txtDui, c);

                c.gridx = 0; c.gridy = 3; formularioPanel.add(lblSalarioBase, c);
                c.gridx = 1; formularioPanel.add(txtSalarioBase, c);

                c.gridx = 0; c.gridy = 4; formularioPanel.add(lblSalarioMensual, c);
                c.gridx = 1; formularioPanel.add(txtSalarioMensual, c);

                // Posicionar el botón abajo abarcando ambas columnas
                c.gridx = 0; c.gridy = 5; c.gridwidth = 2;
                c.fill = GridBagConstraints.NONE;
                formularioPanel.add(btnGuardar, c);

                // 4. Lógica del botón: Instanciar tus clases de negocio
                btnGuardar.addActionListener(e -> {
                    try {
                        String nom = txtNombre.getText();
                        String ape = txtApellidos.getText();
                        String dui = txtDui.getText();
                        // REEMPLAZANDO COMAS 
                        // Reemplaza las líneas donde extraes los salarios por estas:
                        Double base = Double.parseDouble(txtSalarioBase.getText().replace(",", ""));
                        double mensual = Double.parseDouble(txtSalarioMensual.getText().replace(",", ""));
                        // Aquí se ejecuta tu clase EmpleadoFIjo
                        EmpleadoFijo nuevoEmpleado = new EmpleadoFijo(nom, ape, dui, base, mensual);

                        // Mensaje de éxito usando tu método toString() optimizado
                        JOptionPane.showMessageDialog(panel, "¡Empleado Fijo guardado con éxito!\n" + nuevoEmpleado.toString());

                        // Limpiar campos
                        txtNombre.setText(""); txtApellidos.setText(""); txtDui.setText("");
                        txtSalarioBase.setText(""); txtSalarioMensual.setText("");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Por favor, introduce valores numéricos válidos en los salarios.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                // Añadir el formulario al panel de la pestaña
                panel.add(formularioPanel);
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
                textoBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 17));
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
        setSize(900, 560);
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
