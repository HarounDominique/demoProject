package com.haroun.server.service;

import com.haroun.server.model.Provincia;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService {
/*
    @Autowired
    private IProvinciaRepository provinciaRepository;

 */
    private final IProvinciaRepository provinciaRepository;
    @Autowired
    public ProvinciaService(IProvinciaRepository provinciaRepository) {
    this.provinciaRepository = provinciaRepository;
    }

    public List<Provincia> getAllProvincias() {
        return provinciaRepository.findAll();
    }

    public Provincia getProvinciaById(int id) {
        return provinciaRepository.findById(id);
    }
/*
    public Provincia saveProvincia(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

 */

    public Provincia saveProvincia(Provincia provincia) {
        if (provincia.getId() == null) {
            provinciaRepository.insert(provincia);
        } else {
            provinciaRepository.update(provincia);
        }
        return provincia;
    }

    public void deleteProvincia(int id) {
        provinciaRepository.deleteById(id);
    }

    public boolean updateProvincia(int id, Provincia provincia) {
        Provincia p = provinciaRepository.findById(id);
        if(p != null) {
            p.setNombre(provincia.getNombre());
            //p.setLocalidades(provincia.getLocalidades());
            provinciaRepository.update(p);
            return true;
        }else{
            return false;
        }
    }
}
