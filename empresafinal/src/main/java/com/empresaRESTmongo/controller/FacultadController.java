package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.Map;

@RestController
@RequestMapping("v1/api/facultad")
public class FacultadController {

    @Autowired
    EmpleadoService empleadoService;

    //Traer una lista de empleados de la Facultad
    @GetMapping("/")
    public ResponseEntity getEmpleadosFromFactultad(@RequestParam(required = false) Map <String, String> allParams) {
        try {
            return new ResponseEntity(empleadoService.getEmpleadosFromFacultad(allParams), HttpStatus.OK);
        } catch (ResourceAccessException e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    //Filtrar un solo empleado de la Facultad
    @GetMapping("/{dni}")
    public ResponseEntity getEmpleadosFromFactultadbyDni(@PathVariable String dni) {
        try {
            return new ResponseEntity(empleadoService.getEmpleadoFromFacultadByDni(dni), HttpStatus.OK);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ResourceAccessException e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
