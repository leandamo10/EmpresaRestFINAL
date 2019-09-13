package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.model.Empleado;
import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/api/empleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    //Filtrar un empleado por dni
    @GetMapping("/{dni}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.findEmpleado(dni), HttpStatus.OK);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Filtrar empleados segun un rango de sueldo
    @GetMapping("/sueldo")
    public ResponseEntity getEmpleados(@RequestParam Double sueldoMin, Double sueldoMax) {
        return new ResponseEntity(empleadoService.filterBySueldo(sueldoMin, sueldoMax), HttpStatus.OK);
    }

    //Filtrar empleados segun cualquiera de los parametros del mismo
    @GetMapping("")
    public ResponseEntity getEmpleadosByParam(@RequestParam(required = false) Map<String, String> allParams) {
        return new ResponseEntity(empleadoService.findByParam(allParams), HttpStatus.OK);

    }

    //Borrar cualquier empleado por dni
    @DeleteMapping("/{dni}")
    public ResponseEntity deleteEmpleado(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.borrar(dni), HttpStatus.OK);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}


