package com.haroun.server.service;

import com.haroun.server.model.Provincia;
import com.haroun.server.repository.IProvinciaMyBatisRepository;
import com.haroun.server.repository.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private IProvinciaRepository provinciaRepository;

    private final IProvinciaMyBatisRepository provinciaMyBatisRepository;
    @Autowired
    public ProvinciaService(IProvinciaMyBatisRepository provinciaMyBatisRepository) {
    this.provinciaMyBatisRepository = provinciaMyBatisRepository;
    }



    public List<Provincia> getAllProvincias() {
        return provinciaMyBatisRepository.findAll();
    }

    public Provincia getProvinciaById(int id) {
        return provinciaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Provincia saveProvincia(Provincia provincia) {
        provinciaRepository.save(provincia);
        return provincia;
    }

    @Transactional
    public void deleteProvincia(int id) {
        provinciaRepository.deleteById(id);
    }

    @Transactional
    public Provincia updateProvincia(int id, Provincia provincia) {
        Provincia p = provinciaRepository.findById(id).orElse(null);
        if(p != null) {
            p.setNombre(provincia.getNombre());
            p.setLocalidades(provincia.getLocalidades());
            provinciaRepository.save(p);
            return p;
        }else{
            return null;
        }
    }
}
