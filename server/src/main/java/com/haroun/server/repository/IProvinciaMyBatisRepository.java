package com.haroun.server.repository;

import com.haroun.server.model.Provincia;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//MyBATIS MODEL
@Mapper
public interface IProvinciaMyBatisRepository {

    List<Provincia> findAll();

    Provincia findProvinciaNameById(int id);
/*
    Provincia findById(int id);

    Provincia save(Provincia provincia);

    int insert(Provincia provincia);

    int update(Provincia provincia);

    void deleteById(int provincia);

 */
}
