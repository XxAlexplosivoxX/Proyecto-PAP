package com.grupo1.app;

public class EmpleadoFijo extends Empleado {
    // Atributo específico para EmpleadoFijo 
    private double salarioMensual;

    /**
     * Constructor modificado para incluir el nuevo atributo heredando de la base.
     */
    public EmpleadoFijo(String nombre, String apellidos, String dui, Double salario, double salarioMensual) {
        // super() manda a llamar obligatoriamente al constructor de la clase padre (Empleado)
        super(nombre, apellidos, dui, salario);
        this.salarioMensual = salarioMensual;
    }

    // Getter y Setter para el salario mensual
    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    /**
     * Implementación obligatoria del método abstracto.
     * Devuelve el salario mensual.
     */
    @Override
    public double calcularSalario() {
        return this.salarioMensual;
    }

    /**
     * Extensión del método toString() combinando los datos heredados.
     */
    @Override
    public String toString() {
        return super.toString() + " -> Tipo: Empleado Fijo [Salario Mensual: $" + salarioMensual + "]";
    }
}
