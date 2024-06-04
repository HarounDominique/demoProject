package com.haroun.server.repository;

import com.haroun.server.model.Localidad;
import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILocalidadRepository extends JpaRepository<Localidad, Integer> {
    void deleteByProvincia(Provincia provincia);
}
