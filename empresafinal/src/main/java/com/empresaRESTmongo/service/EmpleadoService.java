package com.empresaRESTmongo.service;

import com.empresaRESTmongo.cliente.FacultadCliente;
import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.modelFacultad.EmpleadoFacultad;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    FacultadCliente facultadCliente;

    //Filtra por un solo empleado segun dni
    public Empleado findEmpleado(String dni) throws DniNotFoundException {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            throw new DniNotFoundException("El empleado no existe");
        } else {
            return empleado;
        }
    }

    //filtrar empleados segun un rango de sueldos
    public List<Empleado> filterBySueldo(Double sueldoMin, Double sueldoMax) {
        return empleadoRepository.findUserBySueldoBetween(sueldoMin, sueldoMax);
    }

    // borrar 1 empleado segun su dni
    public Empleado borrar(String dni) throws DniNotFoundException {
        Empleado empleado = empleadoRepository.findByDni(dni);
        if (empleado == null) {
            throw new DniNotFoundException("Cliente no existe");
        } else {
            empleadoRepository.deleteByDni(dni);
            return empleado;
        }
    }

    // filtrar empleados segun N cantidad de paraemtros
    public List<Empleado> findByParam(Map<String, String> allParams) {
        if (allParams.isEmpty()) {
            return empleadoRepository.findAll();
        } else {
            List<Empleado> lista = new ArrayList<>();
            List<Empleado> lista1 = new ArrayList<>();
            for (String key : allParams.keySet()) {
                if (lista.isEmpty()) {
                    if (key.equals("comisiones") || key.equals("sueldo")) {
                        String var2 = allParams.get(key);
                        lista.addAll(empleadoRepository.searchByNumber(key, (Double.valueOf(var2))));
                        if (lista.isEmpty()) {
                            return lista;
                        }
                    } else {
                        String var1 = allParams.get(key);
                        lista.addAll(empleadoRepository.searchByParam(key, var1));
                        if (lista.isEmpty()) {
                            return lista;
                        }
                    }
                } else {
                    List<Empleado> lista2 = new ArrayList<>();
                    if (key.equals("comisiones") || key.equals("sueldo")) {
                        String var2 = allParams.get(key);
                        lista1.addAll(empleadoRepository.searchByNumber(key, (Double.valueOf(var2))));
                        if (lista1.isEmpty()) {
                            return lista1;
                        }
                    } else {
                        String var1 = allParams.get(key);
                        lista1.addAll(empleadoRepository.searchByParam(key, var1));
                        if (lista1.isEmpty()) {
                            return lista;
                        }
                    }
                    for (Empleado empleado : lista) {
                        for (int i = 0; i < lista1.size(); i++) {
                            if (empleado.getDni().equals(lista1.get(i).getDni())) {
                                lista2.add(empleado);
                            }
                        }
                    }
                    lista = lista2;
                    lista1.removeAll(lista1);
                }
            }
            Set<Empleado> lista3 = lista.stream().collect(Collectors.toSet());
            List<Empleado> lista4 = new ArrayList<Empleado>(lista3);
            return lista4;
        }
    }

    // traer una lista de empleados de la facultad
    public List<EmpleadoFacultad> getEmpleadosFromFacultad(Map <String, String> allParams) throws Exception {
        if (allParams.isEmpty()) {
            return facultadCliente.findEmpleadosFromFacultad();
        } else {
            return facultadCliente.findEmpleadosFromFacultadByParams(allParams);
        }
    }

    // traer un empleado de la facultad segun su dni
    public EmpleadoFacultad getEmpleadoFromFacultadByDni(String dni) throws Exception {
        return facultadCliente.getEmpleadoFromFacultadByDni(dni);
    }
}


