package com.empresaRESTmongo.controller;

import com.empresaRESTmongo.exceptions.CargoIncorrectoException;
import com.empresaRESTmongo.exceptions.DniNotEditableException;
import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.exceptions.EmpleadoAlreadyExistException;
import com.empresaRESTmongo.model.CargoEnum;
import com.empresaRESTmongo.model.Vendedor;
import com.empresaRESTmongo.service.EmpleadoService;
import com.empresaRESTmongo.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/api/")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private EmpleadoService empleadoService;

    //Creo un vendedor nuevo
    @PostMapping("empleados/vendedores")
    public ResponseEntity<Vendedor> newEmpleado(@Valid @RequestBody Vendedor vendedor) {
        try {
            return new ResponseEntity(vendedorService.newVendedor(vendedor), HttpStatus.CREATED);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (EmpleadoAlreadyExistException e) {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("empleados/vendedores")
    public ResponseEntity getVendedores() {
        return new ResponseEntity(vendedorService.findVendedores(CargoEnum.VENDEDOR), HttpStatus.OK);
    }

    @PutMapping("empleados/vendedores/{dni}")
    public ResponseEntity<Vendedor> updateVendedor(@RequestBody Vendedor vendedor, @PathVariable String dni) {
        try {
            return new ResponseEntity(vendedorService.insertVendedor(dni, vendedor), HttpStatus.OK);
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


    @PostMapping("facultad/vendedores/{dni}")
    public ResponseEntity<Vendedor> newEmpleado(@PathVariable String dni) {
        try {
           return new ResponseEntity (vendedorService.newVendedorFromFacultad(dni), HttpStatus.CREATED);
        } catch (DniNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (EmpleadoAlreadyExistException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}