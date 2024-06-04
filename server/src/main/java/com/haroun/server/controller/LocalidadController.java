package com.haroun.server.controller;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.IProvinciaRepository;
import com.haroun.server.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadService localidadService;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @GetMapping
    public List<Localidad> getAllLocalidades(){
        return localidadService.getAllLocalidades();
    }

    @GetMapping("/{id}")
    public Localidad getLocalidadById(@PathVariable int id){
        return localidadService.getLocalidadById(id);
    }

    @PostMapping
    public Localidad createLocalidad(@RequestBody Localidad localidad){
        return localidadService.saveLocalidad(localidad);
    }

    @DeleteMapping("/{id}")
    public void deleteLocalidad(@PathVariable int id){
        localidadService.deleteLocalidad(id);
    }

    @GetMapping("/{id}/provincia")
    public Provincia getProvincia(@PathVariable int id){
        Localidad l = localidadService.getLocalidadById(id);
        Provincia p = l.getProvincia();
        return provinciaRepository.findById(p.getId()).orElse(null);
    }
}
