package com.haroun.server.controller;

import com.haroun.server.model.Provincia;
import com.haroun.server.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciasService;

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
}
