package com.haroun.server.repository;

import com.haroun.server.model.Provincia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//HIBERNATE MODEL

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
}

