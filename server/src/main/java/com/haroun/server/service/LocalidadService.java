package com.haroun.server.service;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import com.haroun.server.repository.hibernate.ILocalidadRepository;
import com.haroun.server.repository.mybatis.IProvinciaMyBatisRepository;
import com.haroun.server.repository.hibernate.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private ILocalidadRepository localidadRepository;

    @Autowired
    private IProvinciaRepository provinciaRepository;

    //inyección de dependencias (MyBatis) manual haciendo una instancia del repositorio de Provincia y parándolo por parámetro al constructor de la clase actual (LocalidadService)
    private final IProvinciaMyBatisRepository provinciaMyBatisRepository;
    @Autowired
    public LocalidadService(IProvinciaMyBatisRepository provinciaMyBatisRepository) {
        this.provinciaMyBatisRepository = provinciaMyBatisRepository;
    }

    public List<Localidad> getAllLocalidades(){
        return localidadRepository.findAll();
    }

    public Localidad getLocalidadById(int id) {
        return localidadRepository.findById(id).orElse(null);
    }

    public List<Localidad> getLocalidadByIdInAraryFormat(int id) {
        List<Localidad> localidadList = new ArrayList<>();
        localidadList.add(localidadRepository.findById(id).orElse(null));
        return localidadList;
    }

    public Provincia getProvinciaByLocalidadId(int id) {
        Localidad l = localidadRepository.findById(id).orElse(null);
        if(l != null) {
            Provincia p = provinciaMyBatisRepository.findProvinciaNameById(l.getProvincia().getId());
            return p;
        }
        return null;
    }

    public List<Localidad> getLocalidadesByProvinciaId(int id) {
        Provincia p = provinciaRepository.findById(id).orElse(null);
        if(p != null) {
            List<Localidad> l;
            l =  p.getLocalidades();
            return l;
        }else{
            return null;
        }
    }

    @Transactional
    public Localidad saveLocalidad(int id, Localidad localidad) {
        Provincia p = provinciaRepository.findById(id).orElse(null);
        if (p != null) {
            if(localidad.getNombre().isBlank() || localidad.getNombre().isEmpty()){
                return null;
            }else{
                localidad.setProvincia(p);
                if (p.getLocalidades() == null) {
                    p.setLocalidades(new ArrayList<>());
                }
                p.getLocalidades().add(localidad);
                provinciaRepository.save(p);
                return localidadRepository.save(localidad);
            }
        } else {
            return null;
        }
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    public boolean updateLocalidadName(int id, String localidadName) {
        Localidad l = localidadRepository.findById(id).orElse(null);
        if (l != null) {
            if(localidadName.trim().replaceAll("\"", "").isBlank() || localidadName.trim().replaceAll("\"", "").isEmpty()){
                return false;
            }else{
                l.setNombre(localidadName.trim().replaceAll("\"", ""));
                localidadRepository.save(l);
                return true;
            }
        }else{
            return false;
        }
    }
}