package com.haroun.server.service;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.ILocalidadRepository;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private ILocalidadRepository localidadRepository;
    @Autowired
    private IProvinciaRepository provinciaRepository;

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

    @Transactional
    public void deleteLocalidadesByProvincia(int provinciaId) {
        Provincia provincia = provinciaRepository.findById(provinciaId).orElse(null);
        if (provincia != null) {
            localidadRepository.deleteByProvincia(provincia);
        }
    }

    public boolean updateLocalidad(int id, Localidad localidad) {
        Localidad l = localidadRepository.findById(id).orElse(null);
        if (l != null) {
            l.setNombre(localidad.getNombre());
            l.setProvincia(localidad.getProvincia());
            localidadRepository.save(l);
            return true;
        }else{
            return false;
        }
    }
}
