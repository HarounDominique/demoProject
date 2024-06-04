package com.haroun.server.service;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.ILocalidadRepository;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private ILocalidadRepository localidadRepository;

    public List<Localidad> getAllLocalidades(){
        return localidadRepository.findAll();
    }

    public Localidad getLocalidadById(int id) {
        return localidadRepository.findById(id).orElse(null);
    }

    public Localidad saveLocalidad(Localidad provincia) {
        return localidadRepository.save(provincia);
    }

    public void deleteLocalidad(int id) {
        localidadRepository.deleteById(id);
    }
}
