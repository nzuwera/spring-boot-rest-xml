package com.nzuwera.demorestxml.repository;

import com.nzuwera.demorestxml.bean.City;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends CrudRepository<City, UUID> {
    City findByGuid(UUID id);
//    @Query("select a from cities where id=:id")
//    City findOne(Long id);
}