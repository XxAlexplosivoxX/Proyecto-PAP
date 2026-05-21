package com.grupo1.app.Clases;
//Empresa.java
import com.grupo1.app.Empleado;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
    // Atributo: Lista de objetos de la clase abstracta Empleado
    private List<Empleado> empleados;

    // Constructor: Inicializa la lista vacía
    public Empresa() {
        this.empleados = new ArrayList<>();
    }

    // Método para agregar cualquier tipo de empleado (Fijo o Por Horas)
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado agregado: " + empleado.getNombre() + " " + empleado.getApellidos());
    }

    // Método para eliminar empleado por DUI
    public void eliminarEmpleado(String dui) throws EmpleadoNoEncontradoException {
        Empleado empleadoEncontrado = null;

        // Buscamos si existe el empleado con ese DUI
        for (Empleado emp : empleados) {
            if (emp.getDui().equals(dui)) {
                empleadoEncontrado = emp;
                break; // Si lo encuentra, rompe el ciclo
            }
        }

        // Si lo encontramos, lo borramos; si no, lanzamos la excepción
        if (empleadoEncontrado != null) {
            empleados.remove(empleadoEncontrado);
            System.out.println("-> Empleado con DUI " + dui + " eliminado exitosamente.");
        } else {
            throw new EmpleadoNoEncontradoException("Error: El empleado con DUI [" + dui + "] no está registrado en el sistema.");
        }
    }

    // Getter para poder listar los empleados en la clase Principal
    public List<Empleado> getEmpleados() {
        return empleados;
    }
}
