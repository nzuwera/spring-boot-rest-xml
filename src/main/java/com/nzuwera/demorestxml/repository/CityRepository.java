package com.nzuwera.demorestxml.repository;

import com.nzuwera.demorestxml.bean.City;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
//    @Query("select a from cities where id=:id")
//    City findOne(Long id);
}