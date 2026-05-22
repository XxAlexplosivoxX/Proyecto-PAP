package com.grupo1.app.Clases;

public class EmpleadoPorHora extends Empleado {
    // Atributos específicos para EmpleadoPorHora
    private int horasTrabajadas;
    private double tarifaPorHora;

    /**
     * Constructor que recibe todos los atributos e inicializa los valores.
     */
    public EmpleadoPorHora(String nombre, String apellidos, String dui, double salario, int horasTrabajadas, double tarifaPorHora) {
        super(nombre, apellidos, dui, salario);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    // Getters y Setters

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    // Método para calcular el salario basado en horas trabajadas y tarifa por hora
    @Override
    public double calcularSalario() {
        double salarioTotal = horasTrabajadas * tarifaPorHora;
        setSalario(salarioTotal);
        return salarioTotal;
    }
}