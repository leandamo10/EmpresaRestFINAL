package com.empresaRESTmongo.cliente;

import com.empresaRESTmongo.exceptions.DniNotFoundException;
import com.empresaRESTmongo.modelFacultad.EmpleadoFacultad;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class FacultadCliente {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${facultad.host}")
    private String facultadHost;

    //Traer toda la lista de empleados de la facultad
    public List<EmpleadoFacultad> findEmpleadosFromFacultad() throws Exception {
        try {
            ResponseEntity<String> body = restTemplate.getForEntity(facultadHost, String.class);
            List<EmpleadoFacultad> a = objectMapper.readValue(body.getBody(), new TypeReference<List<EmpleadoFacultad>>() {
            });
            return a;
        } catch (ResourceAccessException ex) {
            throw new ResourceAccessException("Problemas al conectarse con el servidor");
        } catch (Exception e) {
            throw new Exception("Error con el body del empleadoFacultad");
        }
    }

    //Traer toda la lista de empleados de la facultad, filtrandolos por parametros atravez de una URL dinamica
    public List<EmpleadoFacultad> findEmpleadosFromFacultadByParams(Map<String, String> allParams) throws Exception {
        try {
            List<EmpleadoFacultad> lista1;
            String url = facultadHost + "?";
            for (String key : allParams.keySet()) {
                url += key + "=" + allParams.get(key) + "&";
            }
            ResponseEntity<String> xxx = restTemplate.getForEntity(url, String.class);
            lista1 = objectMapper.readValue(xxx.getBody(), new TypeReference<List<EmpleadoFacultad>>() {
            });
            return lista1;
        } catch (
                ResourceAccessException ex) {
            throw new ResourceAccessException("Problemas al conectarse con el servidor");
        } catch (
                Exception e) {
            throw new Exception("Error con el body del empleadoFacultad");
        }

    }

    // Trae un empleado filtrandolo por dni
    public EmpleadoFacultad getEmpleadoFromFacultadByDni(String dni) throws Exception {
        String url = facultadHost.concat(dni);
        try {
            ResponseEntity<String> xxx = restTemplate.getForEntity(url, String.class);
            if (xxx.equals(null)) {
                throw new DniNotFoundException("El empleado no existe en la facultad");
            }
            return objectMapper.readValue(xxx.getBody(), new TypeReference<EmpleadoFacultad>() {
            });
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException("Problemas al conectarse con el servidor");
        } catch (Exception ex) {
            throw new Exception("Error en el body del empleado");
        }
    }
}




