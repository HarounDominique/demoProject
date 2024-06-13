package com.haroun.server.repository.hibernate;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//HIBERNATE MODEL
@Repository
public interface ILocalidadRepository extends JpaRepository<Localidad, Integer> {
    void deleteByProvincia(Provincia provincia);
}