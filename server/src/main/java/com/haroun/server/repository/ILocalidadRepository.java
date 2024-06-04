package com.haroun.server.repository;

import com.haroun.server.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadRepository extends JpaRepository<Localidad, Integer> {
}
