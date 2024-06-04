package com.haroun.server.repository;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadRepository extends JpaRepository<Localidad, Integer> {

    //public Provincia findProvincia(int id);
}
