package com.grupo1.app.Clases;

public abstract class Empleado {
    // Atributos obligatorios según la guía 
    private String nombre;
    private String apellidos;
    private String dui;
    private Double salario;

    /**
     * Constructor que recibe todos los atributos e inicializa los valores.
     */
    public Empleado(String nombre, String apellidos, String dui, Double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dui = dui;
        this.salario = salario;
    }

    // ==========================================
    //          GETTERS Y SETTERS 
    // ==========================================

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    // ==========================================
    //          MÉTODOS DE LA CLASE
    // ==========================================

    /**
     * Método abstracto que devuelva el salario del empleado.
     */
    public abstract double calcularSalario();

    /**
     * Devuelve una cadena con la información del empleado.
     */
    @Override
    public String toString() {
        return "Empleado [" +
                "Nombre: " + nombre + " " + apellidos + 
                " | DUI: " + dui + 
                " | Salario Base: $" + salario + "]";
    }
}
