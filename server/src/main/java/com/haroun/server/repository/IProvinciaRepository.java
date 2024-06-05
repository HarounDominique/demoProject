package com.haroun.server.repository;

import com.haroun.server.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
}