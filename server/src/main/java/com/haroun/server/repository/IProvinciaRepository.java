package com.haroun.server.repository;

import com.haroun.server.model.Provincia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//HIBERNATE MODEL
/*
@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
}

 */

//MyBATIS MODEL
@Mapper
public interface IProvinciaRepository {

    List<Provincia> findAll();

    Provincia findById(int id);

    Provincia save(Provincia provincia);

    int insert(Provincia provincia);

    int update(Provincia provincia);

    void deleteById(int provincia);
}