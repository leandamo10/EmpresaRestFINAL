package com.empresaRESTmongo.service;

import com.empresaRESTmongo.exceptions.*;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.modelFacultad.EmpleadoFacultad;
import com.empresaRESTmongo.model.JefeDeZona;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JefeService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;


    //crear un nuevo jefe de zona
    public JefeDeZona newJefe(JefeDeZona jefeDeZona) throws Exception {
        if (jefeDeZona == null) {
            throw new EmpleadoEmpty("El empleado necesita atributos para ser creado");
        }
        if (empleadoRepository.findByDni(jefeDeZona.getDni()) != null) {
            throw new EmpleadoAlreadyExistException("El empleado ya existe ");
        } else if (jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
            return empleadoRepository.save(jefeDeZona);
        } else {
            throw new CargoIncorrectoException("Cargo incorrecto");
        }
    }

    // crear un nueve jefe de zona segun datos de un empleado de la facultad
    public JefeDeZona newJefeFromFacultad(String dni) throws Exception {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado == null) {
            throw new DniNotFoundException("Empleado no encontrado en facultad");
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                JefeDeZona jefeDeZona1 = new JefeDeZona();
                jefeDeZona1.setCargo(CargoEnum.JEFE_DE_ZONA);
                jefeDeZona1.setNombre(empleado.getNombre());
                jefeDeZona1.setApellido(empleado.getApellido());
                jefeDeZona1.setDni(empleado.getDni());
                jefeDeZona1.setSueldo(empleado.getSalario());
                empleadoRepository.save(jefeDeZona1);
                return jefeDeZona1;
            } else {
                throw new EmpleadoAlreadyExistException("El empleado ya existe");
            }
        }
    }

    // devolver una lista de jefes
    public List<Empleado> findJefes(CargoEnum Cargo) {
        return (empleadoRepository.findByCargo(Cargo));
    }

    // editar un jefe segun su dni
    public JefeDeZona insertJefe(String dni, JefeDeZona jefeDeZona) throws Exception {
        if (jefeDeZona.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(jefeDeZona.getDni()) == null) {
                throw new DniNotFoundException("El empleado no existe");
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.JEFE_DE_ZONA) && jefeDeZona.getCargo().equals(CargoEnum.JEFE_DE_ZONA)) {
                JefeDeZona jefeDeZona1 = (JefeDeZona) empleadoRepository.findByDni(dni);
                jefeDeZona1.setDespacho(jefeDeZona.getDespacho());
                jefeDeZona1.setSecretario(jefeDeZona.getSecretario());
                jefeDeZona1.setSecretarioDni(jefeDeZona1.getSecretarioDni());
                jefeDeZona1.setApellido(jefeDeZona.getApellido());
                jefeDeZona1.setCargo(jefeDeZona.getCargo());
                jefeDeZona1.setNombre(jefeDeZona.getNombre());
                jefeDeZona1.setSueldo(jefeDeZona.getSueldo());
                jefeDeZona1.setTelefono(jefeDeZona.getTelefono());
                return empleadoRepository.save(jefeDeZona1);
            } else {
                throw new CargoIncorrectoException("El cargo del empleado no se puede modificar");
            }
        } else {
            throw new DniNotEditableException("El dni del empleado no se puede modificar");
        }
    }
}

