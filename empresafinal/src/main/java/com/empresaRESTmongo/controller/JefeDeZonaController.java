package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.exceptions.*;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.JefeDeZona;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.JefeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/")
public class JefeDeZonaController {
    @Autowired
    private JefeService jefeService;
    @Autowired
    private EmpleadoService empleadoService;

    //Crear un nuevo jefe de zona
    @PostMapping("empleados/jefes")
    public ResponseEntity<JefeDeZona> newEmpleado(@Valid @RequestBody JefeDeZona jefeDeZona) {
        try {
            return new ResponseEntity(jefeService.newJefe(jefeDeZona), HttpStatus.CREATED);
        } catch (EmpleadoAlreadyExistException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (CargoIncorrectoException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (EmpleadoEmpty e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    //Devolver una lista de jefes
    @GetMapping("empleados/jefes")
    public ResponseEntity getJefes() {
        return new ResponseEntity (jefeService.findJefes(CargoEnum.JEFE_DE_ZONA), HttpStatus.OK);
    }

    //Modificar un jefe segun su dni
    @PutMapping("empleados/jefes/{dni}")
    public ResponseEntity<JefeDeZona> updateJefe(@RequestBody JefeDeZona jefeDeZona, @PathVariable String dni) {
        try {
            return new ResponseEntity(jefeService.insertJefe(dni, jefeDeZona), HttpStatus.OK);
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

    //Crear un jefe en base a los datos de un empleado de la facultad
    @PostMapping("facultad/jefes/{dni}")
    public ResponseEntity<JefeDeZona> newEmpleado(@PathVariable String dni) {
        try {
            return new ResponseEntity(jefeService.newJefeFromFacultad(dni), HttpStatus.CREATED);
        } catch (DniNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (EmpleadoAlreadyExistException e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}