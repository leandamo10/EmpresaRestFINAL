package com.empresaRESTmongo.service;

import com.empresaRESTmongo.exceptions.CargoIncorrectoException;
import com.empresaRESTmongo.exceptions.DniNotEditableException;
import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.exceptions.EmpleadoAlreadyExistException;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.modelFacultad.EmpleadoFacultad;
import com.empresaRESTmongo.model.Vendedor;
import com.empresaRESTmongo.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    EmpleadoService empleadoService;


    // crear un vendedor nuevo
    public Vendedor newVendedor(Vendedor vendedor) throws Exception {
        if (empleadoRepository.findByDni(vendedor.getDni()) != null) {
            throw new EmpleadoAlreadyExistException("El empleado ya existe");
        } else if (vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
            return (empleadoRepository.save(vendedor));
        } else {
            throw new CargoIncorrectoException("Cargo incorrecto");
        }
    }

    // devolver una lista de vendedores
    public List<Empleado> findVendedores(CargoEnum Cargo) {
        return (empleadoRepository.findByCargo(Cargo));
    }

    // modificar un vendedor en particular segun su dni
    public Empleado insertVendedor(String dni, Vendedor vendedor) throws Exception {
        if (vendedor.getDni().equals(dni)) {
            if (empleadoRepository.findByDni(dni) == null) {
                throw new DniNotFoundException("El empleado no existe");
            } else if (empleadoRepository.findByDni(dni).getCargo().equals(CargoEnum.VENDEDOR) && vendedor.getCargo().equals(CargoEnum.VENDEDOR)) {
                Vendedor vendedor1 = (Vendedor) empleadoRepository.findByDni(dni);
                vendedor1.setComisiones(vendedor.getComisiones());
                vendedor1.setApellido(vendedor.getApellido());
                vendedor1.setCargo(vendedor.getCargo());
                vendedor1.setNombre(vendedor.getNombre());
                vendedor1.setSueldo(vendedor.getSueldo());
                vendedor1.setTelefono(vendedor.getTelefono());
                return empleadoRepository.save(vendedor1);
            } else {
                throw new CargoIncorrectoException("El cargo del empleado no se puede modificar");
            }
        } else {
            throw new DniNotEditableException("El dni del empleado no se puede modificar");
        }
    }

    //crear un vendedor nuevo segun los datos de un empleado de la facultad
    public Empleado newVendedorFromFacultad(String dni) throws Exception {
        EmpleadoFacultad empleado = empleadoService.getEmpleadoFromFacultadByDni(dni);
        if (empleado == null) {
            throw new DniNotFoundException("El empleado no existe en la facultad");
        } else {
            if (empleadoRepository.findByDni(empleado.getDni()) == null) {
                Vendedor vendedor = new Vendedor();
                vendedor.setCargo(CargoEnum.VENDEDOR);
                vendedor.setNombre(empleado.getNombre());
                vendedor.setApellido(empleado.getApellido());
                vendedor.setDni(empleado.getDni());
                vendedor.setSueldo(empleado.getSalario());
                empleadoRepository.save(vendedor);
                return vendedor;
            } else {
                throw new EmpleadoAlreadyExistException("El empleado ya existe");
            }
        }
    }
}


