package com.empresaRESTmongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Secretario extends Empleado {

    private String despacho;
    private String fax;

    public Secretario() {
    }

    public Secretario(String id, String nombre, String apellido, String dni, String telefono, Double sueldo, CargoEnum cargo, String despacho, String fax) {
        super(id, nombre, apellido, dni, telefono, sueldo, cargo);
        this.despacho = despacho;
        this.fax = fax;
    }

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
