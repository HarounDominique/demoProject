package com.haroun.server.service;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.ILocalidadRepository;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private ILocalidadRepository localidadRepository;
    /*
    @Autowired
    private IProvinciaRepository provinciaRepository;
     */

    //inyección de dependencias manual haciendo una instancia del repositorio de Provincia y parándolo por parámetro al constructor de la clase actual (LocalidadService)
    private final IProvinciaRepository provinciaRepository;
    @Autowired
    public LocalidadService(IProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public List<Localidad> getAllLocalidades(){
        return localidadRepository.findAll();
    }

    public Localidad getLocalidadById(int id) {
        return localidadRepository.findById(id).orElse(null);
    }

    public Localidad saveLocalidad(int id, Localidad localidad) {
        Provincia p = provinciaRepository.findById(id);
        if(p != null) {
            localidad.setProvincia(p);
            if(p.getLocalidades() == null) {
                p.setLocalidades(new ArrayList<>());
            }
            p.getLocalidades().add(localidad);
            return localidadRepository.save(localidad);
        } else {
            return null;
        }
    }

    public void deleteLocalidad(int id) {
        localidadRepository.deleteById(id);
    }

    @Transactional
    public void deleteLocalidadesByProvincia(int provinciaId) {
        Provincia provincia = provinciaRepository.findById(provinciaId);
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
