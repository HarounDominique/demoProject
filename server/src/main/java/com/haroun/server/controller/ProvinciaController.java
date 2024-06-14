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

    @GetMapping("/{provinciaId}")
    public Provincia findProvinciaNameById(@PathVariable int provinciaId) {
        return provinciasService.findProvinciaNameById(provinciaId);
    }

    @GetMapping("/{provinciaId}/localidades")
    public List<Localidad> getLocalidadesByProvinciaId(@PathVariable int provinciaId) {
        return localidadService.getLocalidadesByProvinciaId(provinciaId);
    }

    @PostMapping
    public Provincia createProvincia(@RequestBody Provincia provincia) {
        return provinciasService.saveProvincia(provincia);
    }

    @DeleteMapping("/{provinciaId}")
    public void deleteProvincia(@PathVariable int provinciaId) {
        provinciasService.deleteProvincia(provinciaId);
    }

    @DeleteMapping("/{provinciaId}/localidades")
    public void deleteLocalidadesByProvincia(@PathVariable int provinciaId) {
        localidadService.deleteLocalidadesByProvincia(provinciaId);
    }

    @PutMapping("/{provinciaId}")
    public ResponseEntity<String> updateProvincia(@PathVariable int provinciaId, @RequestBody Provincia provincia) {
        try {
            Provincia updatedProvincia = provinciasService.updateProvincia(provinciaId, provincia);
            if (updatedProvincia != null) {
                return new ResponseEntity<>("Provincia actualizada correctamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Provincia no encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{provinciaId}/localidades")
    public ResponseEntity<?> addLocalidadToProvincia(@PathVariable int provinciaId, @RequestBody String localidadName) {
        try {
            Provincia p = provinciasService.addLocalidadToProvincia(provinciaId, localidadName);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
