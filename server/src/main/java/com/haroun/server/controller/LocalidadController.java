package com.haroun.server.controller;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.IProvinciaRepository;
import com.haroun.server.service.LocalidadService;
import com.haroun.server.service.ProvinciaService;
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

    @Autowired
    private IProvinciaRepository provinciaRepository;
    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping
    public List<Localidad> getAllLocalidades(){
        return localidadService.getAllLocalidades();
    }

    @GetMapping("/{id}")
    public Localidad getLocalidadById(@PathVariable int id){
        return localidadService.getLocalidadById(id);
    }

    @GetMapping("/{id}/provincia")
    public Provincia getProvincia(@PathVariable int id){
        return localidadService.getProvinciaByLocalidadId(id);
    }

    @PostMapping("/{id}")
    public Localidad createLocalidad(@PathVariable int id, @RequestBody Localidad localidad){
        return localidadService.saveLocalidad(id, localidad);
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

    @DeleteMapping("/{id}")
    public void deleteLocalidad(@PathVariable int id){
        localidadService.deleteLocalidad(id);
    }



}
