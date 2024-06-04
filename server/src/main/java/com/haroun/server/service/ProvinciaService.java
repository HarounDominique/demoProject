package com.haroun.server.service;

import com.haroun.server.model.Provincia;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private IProvinciaRepository provinciasRepository;

    public List<Provincia> getAllProvincias() {
        return provinciasRepository.findAll();
    }

    public Provincia getProvinciaById(int id) {
        return provinciasRepository.findById(id).orElse(null);
    }

    public Provincia saveProvincia(Provincia provincia) {
        return provinciasRepository.save(provincia);
    }

    public void deleteProvincia(int id) {
        provinciasRepository.deleteById(id);
    }
}
