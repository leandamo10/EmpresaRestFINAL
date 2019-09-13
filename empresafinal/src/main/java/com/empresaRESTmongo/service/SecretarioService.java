package com.empresaRESTmongo.service;

import com.empresaRESTmongo.exceptions.CargoIncorrectoException;
import com.empresaRESTmongo.exceptions.DniNotEditableException;
import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.exceptions.EmpleadoAlreadyExistException;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.modelFacultad.EmpleadoFacultad;
import com.empresaRESTmongo.model.Secretario;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecretarioService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;

    // crear un nuevo secretario
    public Secretario newSecretario(Secretario secretario) throws Exception {
        if (empleadoRepository.findByDni(secretario.getDni()) != null) {
            throw new EmpleadoAlreadyExistException("El empleado ya existe");
        } else if (secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
            return empleadoRepository.save(secretario);
        } else {
            throw new CargoIncorrectoException("Cargo incorrecto");
        }
    }

    // devolver una lista de secretarios
    public List<Empleado> findSecretarios(CargoEnum Cargo) {
        return empleadoRepository.findByCargo(Cargo);
    }

    // modificar un secretario segun su dni
    public Empleado insertSecretario(String dni, Secretario secretario) throws Exception{
        if (secretario.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(secretario.getDni()) == null) {
                throw new DniNotFoundException("El empleado no existe");
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.SECRETARIO) && secretario.getCargo().equals(CargoEnum.SECRETARIO)) {
                Secretario secretario1 = (Secretario) empleadoRepository.findByDni(dni);
                secretario1.setDespacho(secretario.getDespacho());
                secretario1.setFax(secretario.getFax());
                secretario1.setApellido(secretario.getApellido());
                secretario1.setCargo(secretario.getCargo());
                secretario1.setNombre(secretario.getNombre());
                secretario1.setSueldo(secretario.getSueldo());
                secretario1.setTelefono(secretario.getTelefono());
                return empleadoRepository.save(secretario1);
            } else {
                throw new CargoIncorrectoException("El cargo del empleado no se puede modificar");
            }
        } else {
            throw new DniNotEditableException("El dni del empleado no se puede modificar");
        }
    }

    // crear un secretario segun los datos de un empleado de la facultad
    public Empleado newSecretarioFromFacultad(String dni) throws Exception {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado == null) {
            throw new DniNotFoundException("El empleado no existe en la facultad");
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                Secretario secretario = new Secretario();
                secretario.setCargo(CargoEnum.SECRETARIO);
                secretario.setNombre(empleado.getNombre());
                secretario.setApellido(empleado.getApellido());
                secretario.setDni(empleado.getDni());
                secretario.setSueldo(empleado.getSalario());
                return empleadoRepository.save(secretario);
            } else {
                throw new EmpleadoAlreadyExistException("El empleado ya existe");
            }
        }
    }
}
