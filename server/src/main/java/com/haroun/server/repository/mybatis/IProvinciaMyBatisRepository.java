package com.haroun.server.repository.mybatis;

import com.haroun.server.model.Provincia;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//MyBATIS MODEL
@Mapper
public interface IProvinciaMyBatisRepository {

    List<Provincia> findAll();

    Provincia findProvinciaNameById(int id);

}
