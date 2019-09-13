package com.empresaRESTmongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vendedor extends Empleado {

    private Double comisiones;

    public Vendedor() {
    }

    public Vendedor(String id, String nombre, String apellido, String dni, String telefono, Double sueldo, CargoEnum cargo, Double comisiones) {
        super(id, nombre, apellido, dni, telefono, sueldo, cargo);
        this.comisiones = comisiones;
    }

    public Double getComisiones() {
        return comisiones;
    }

    public void setComisiones(Double comisiones) {
        this.comisiones = comisiones;
    }
}
