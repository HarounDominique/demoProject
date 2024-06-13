package com.haroun.server.controller;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadService localidadService;

    @GetMapping
    public List<Localidad> getAllLocalidades(){
        return localidadService.getAllLocalidades();
    }

    @GetMapping("/{id}")
    public Localidad getLocalidadById(@PathVariable int id){
        return localidadService.getLocalidadById(id);
    }

    @GetMapping("/{id}/array")
    public List<Localidad> getLocalidadByIdInArrayFormat(@PathVariable int id){
        return localidadService.getLocalidadByIdInAraryFormat(id);
    }

    @GetMapping("/{id}/provincia")
    public Provincia getProvincia(@PathVariable int id){
        return localidadService.getProvinciaByLocalidadId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLocalidad(@PathVariable int id, @RequestBody Localidad localidad){
        try {
            boolean isUpdated = localidadService.updateLocalidad(id, localidad);
            if(isUpdated){
                return new ResponseEntity<>("Localidad actualizada correctamente", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Localidad no actualizada", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<String> updateLocalidadName(@PathVariable int id, @RequestBody String localidadName){
        try {
            boolean isUpdated = localidadService.updateLocalidadName(id, localidadName);
            if(isUpdated){
                return new ResponseEntity<>("Nombre de localidad actualizado correctamente", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Nombre de localidad no actualizado", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLocalidad(@PathVariable int id){
        localidadService.deleteLocalidad(id);
    }

}
