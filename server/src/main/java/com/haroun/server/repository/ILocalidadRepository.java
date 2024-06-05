package com.haroun.server.repository;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//HIBERNATE MODEL
@Repository
public interface ILocalidadRepository extends JpaRepository<Localidad, Integer> {
    void deleteByProvincia(Provincia provincia);
}

/*
//MyBatis MODEL
@Repository
public interface ILocalidadRepository{
    void deleteByProvincia(Provincia provincia);

    List<Localidad> findAll();

    Localidad findById(int id);

    List<Localidad> selectByProvinciaId(int provinciaId);

    Localidad save(Localidad localidad);

    void deleteById(int localidadId);

    //void deleteLocalidadesByProvinciaId(int provinciaId);
}

 */