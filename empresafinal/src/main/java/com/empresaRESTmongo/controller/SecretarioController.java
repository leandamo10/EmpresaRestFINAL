package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.exceptions.CargoIncorrectoException;
import com.empresaRESTmongo.exceptions.DniNotEditableException;
import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.exceptions.EmpleadoAlreadyExistException;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Secretario;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.SecretarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/")
public class SecretarioController {
    @Autowired
    private SecretarioService secretarioService;
    @Autowired
    private EmpleadoService empleadoService;

    //Crear un nuevo secretario
    @PostMapping("empleados/secretarios")
    public ResponseEntity<Secretario> newEmpleado(@Valid @RequestBody Secretario sec) {
        try {
            return new ResponseEntity(secretarioService.newSecretario(sec), HttpStatus.CREATED);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (EmpleadoAlreadyExistException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Traer la lista de secretarios
    @GetMapping("empleados/secretarios")
    public ResponseEntity getSecretarios() {
        return new ResponseEntity(secretarioService.findSecretarios(CargoEnum.SECRETARIO), HttpStatus.OK);
    }

    //Modificar un secretario segun su dni
    @PutMapping("empleados/secretarios/{dni}")
    public ResponseEntity<Secretario> updateSecretario(@RequestBody Secretario secretario, @PathVariable String dni) {
        try {
            return new ResponseEntity(secretarioService.insertSecretario(dni, secretario), HttpStatus.OK);
        } catch (DniNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (DniNotEditableException e){
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (CargoIncorrectoException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    // crear un secretario segun los datos de un empleado de la facultad
    @PostMapping("facultad/secretarios/{dni}")
    public ResponseEntity<Secretario> newEmpleado(@PathVariable String dni) throws Exception {
        try {
            return new ResponseEntity(secretarioService.newSecretarioFromFacultad(dni), HttpStatus.CREATED);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (EmpleadoAlreadyExistException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}