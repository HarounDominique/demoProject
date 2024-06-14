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

    @GetMapping("/{localidadId}")
    public Localidad getLocalidadById(@PathVariable int localidadId){
        return localidadService.getLocalidadById(localidadId);
    }

    @GetMapping("/{localidadId}/array")
    public List<Localidad> getLocalidadByIdInArrayFormat(@PathVariable int localidadId){
        return localidadService.getLocalidadByIdInAraryFormat(localidadId);
    }

    @GetMapping("/{localidadId}/provincia")
    public Provincia getProvinciaByLocalidadId(@PathVariable int localidadId){
        return localidadService.getProvinciaByLocalidadId(localidadId);
    }

    @PutMapping("/{localidadId}")
    public ResponseEntity<String> updateLocalidad(@PathVariable int localidadId, @RequestBody Localidad localidad){
        try {
            boolean isUpdated = localidadService.updateLocalidad(localidadId, localidad);
            if(isUpdated){
                return new ResponseEntity<>("Localidad actualizada correctamente", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Localidad no actualizada", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{localidadId}/name")
    public ResponseEntity<String> updateLocalidadName(@PathVariable int localidadId, @RequestBody String localidadName){
        try {
            boolean isUpdated = localidadService.updateLocalidadName(localidadId, localidadName);
            if(isUpdated){
                return new ResponseEntity<>("Nombre de localidad actualizado correctamente", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Nombre de localidad no actualizado", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{localidadId}")
    public void deleteLocalidad(@PathVariable int localidadId){
        localidadService.deleteLocalidad(localidadId);
    }

}
