package com.empresaRESTmongo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "Empleado")
public class Empleado {

    @Id
    private String id;
    @Pattern(regexp = "^[\\p{L}]+")
    private String nombre;
    @Pattern(regexp = "^[\\p{L}]+")
    private String apellido;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(\\d{7}|\\d{8})$", message = "El dni tiene que tener 7 u 8 numeros")
    private String dni;
    private String telefono;
    private Double sueldo;
    private CargoEnum cargo;


    public Empleado() {
    }

    public Empleado(String id, String nombre, String apellido, String dni, String telefono, Double sueldo, CargoEnum cargo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.sueldo = sueldo;
        this.cargo = cargo;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
