package com.haroun.server.controller;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.service.LocalidadService;
import com.haroun.server.service.ProvinciaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public Provincia getProvinciaById(@PathVariable int id) {
        return provinciasService.getProvinciaById(id);
    }

    @PostMapping
    public Provincia createProvincia(@RequestBody Provincia provincia) {
        return provinciasService.saveProvincia(provincia);
    }

    @DeleteMapping("/{id}")
    public void deleteProvincia(@PathVariable int id) {
        provinciasService.deleteProvincia(id);
    }
/*
    @DeleteMapping("/{id}/localidades")
    public void deleteLocalidadesByProvincia(@PathVariable int id) {
        Provincia p = provinciasService.getProvinciaById(id);
        List<Localidad> localidades = p.getLocalidades();
        for (Localidad localidad : localidades) {
            localidadService.deleteLocalidad(localidad.getId());
        }
    }
 */

    @DeleteMapping("/{id}/localidades")
    public void deleteLocalidadesByProvincia(@PathVariable int id) {
        localidadService.deleteLocalidadesByProvincia(id);
    }

    @PutMapping
    public Provincia updateProvincia(@RequestBody Provincia provincia) {
        return null;
    }
}
