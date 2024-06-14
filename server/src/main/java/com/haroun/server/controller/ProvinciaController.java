package com.haroun.server.controller;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.service.LocalidadService;
import com.haroun.server.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciasService;
    @Autowired
    private LocalidadService localidadService;


    @GetMapping
    public List<Provincia> getAllProvincias() {
        return provinciasService.getAllProvincias();
    }

    @GetMapping("/localidades")
    public List<Provincia> getAllProvinciasWithLocalidades() {
        return provinciasService.getAllProvinciasWithLocalidades();
    }

    @GetMapping("/{id}")
    public Provincia findProvinciaNameById(@PathVariable int id) {
        return provinciasService.findProvinciaNameById(id);
    }

    @GetMapping("/{id}/localidades")
    public List<Localidad> getLocalidadesByProvinciaId(@PathVariable int id) {
        return localidadService.getLocalidadesByProvinciaId(id);
    }

    @PostMapping
    public Provincia createProvincia(@RequestBody Provincia provincia) {
        return provinciasService.saveProvincia(provincia);
    }

    @DeleteMapping("/{id}")
    public void deleteProvincia(@PathVariable int id) {
        provinciasService.deleteProvincia(id);
    }

    @DeleteMapping("/{id}/localidades")
    public void deleteLocalidadesByProvincia(@PathVariable int id) {
        localidadService.deleteLocalidadesByProvincia(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProvincia(@PathVariable int id, @RequestBody Provincia provincia) {
        try {
            Provincia updatedProvincia = provinciasService.updateProvincia(id, provincia);
            if (updatedProvincia != null) {
                return new ResponseEntity<>("Provincia actualizada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Provincia no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //añadir localidad a provincia existente
    @PostMapping("/{id}")
    public Localidad addLocalidad(@PathVariable int id, @RequestBody Localidad localidad){
        return localidadService.saveLocalidad(id, localidad);
    }

    @PostMapping("/{id}/localidades")
    public ResponseEntity<?> addLocalidadToProvincia(@PathVariable int id, @RequestBody String localidadName) {
        try {
            Provincia p = provinciasService.addLocalidadToProvincia(id, localidadName);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
