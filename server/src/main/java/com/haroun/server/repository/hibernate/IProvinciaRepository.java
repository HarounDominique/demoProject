package com.haroun.server.repository.hibernate;

import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//HIBERNATE MODEL

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
}

