package com.empresaRESTmongo.modelFacultad;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpleadoFacultad {

    private String nombre;
    private String apellido;
    private String dni;
    private CargoEnumFacultad cargo;
    private String anioDeIncorpora;
    private Double salario;
    private String sector;
    private String seccion;
    private String materia;
    private String catedra;

    public EmpleadoFacultad(String nombre, String apellido, String dni, CargoEnumFacultad cargo, Double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cargo = cargo;
        this.salario = salario;
    }

    public EmpleadoFacultad() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public CargoEnumFacultad getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnumFacultad cargo) {
        this.cargo = cargo;
    }

    public String getAnioDeIncorpora() {
        return anioDeIncorpora;
    }

    public void setAnioDeIncorpora(String anioDeIncorpora) {
        this.anioDeIncorpora = anioDeIncorpora;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedra() {
        return catedra;
    }

    public void setCatedra(String catedra) {
        this.catedra = catedra;
    }
}
