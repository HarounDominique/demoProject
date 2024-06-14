package com.haroun.server.service;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.hibernate.ILocalidadRepository;
import com.haroun.server.repository.mybatis.IProvinciaMyBatisRepository;
import com.haroun.server.repository.hibernate.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService {

    @Autowired
    private IProvinciaRepository provinciaRepository;

    @Autowired
    private ILocalidadRepository localidadRepository;

    private final IProvinciaMyBatisRepository provinciaMyBatisRepository;
    @Autowired
    public ProvinciaService(IProvinciaMyBatisRepository provinciaMyBatisRepository) {
    this.provinciaMyBatisRepository = provinciaMyBatisRepository;
    }

    public List<Provincia> getAllProvincias() { return provinciaMyBatisRepository.findAll(); }

    public List<Provincia> getAllProvinciasWithLocalidades(){
        return provinciaRepository.findAll();
    }

    public Provincia findProvinciaNameById(int provinciaId) {
        return provinciaMyBatisRepository.findProvinciaNameById(provinciaId);
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
    public void deleteProvincia(int provinciaId) {
        provinciaRepository.deleteById(provinciaId);
    }

    @Transactional
    public Provincia updateProvincia(int provinciaId, Provincia provincia) {
        Provincia p = provinciaRepository.findById(provinciaId).orElse(null);
        if(p != null) {
            p.setNombre(provincia.getNombre());
            p.setLocalidades(provincia.getLocalidades());
            provinciaRepository.save(p);
            return p;
        }else{
            return null;
        }
    }

    @Transactional
    public Provincia addLocalidadToProvincia(int id, String localidadName) {
        Provincia p = provinciaRepository.findById(id).orElse(null);
        if(p != null) {
            if(localidadName.trim().replaceAll("\"", "").isBlank() || localidadName.trim().replaceAll("\"", "").isEmpty()){
                return null;
            }else{
                List<Localidad> newLocalidadesList = p.getLocalidades();
                Localidad localidad = new Localidad(localidadName.trim().replaceAll("\"", ""), p);
                localidadRepository.save(localidad);
                newLocalidadesList.add(localidad);
                p.setLocalidades(newLocalidadesList);
                provinciaRepository.save(p);
                return p;
            }
        }else{
            return null;
        }
    }
}
