package com.nzuwera.demorestxml.service;

import java.util.List;
import java.util.UUID;

import com.nzuwera.demorestxml.bean.Cities;
import com.nzuwera.demorestxml.bean.City;
import com.nzuwera.demorestxml.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public Cities findAll() {

        List<City> cities = (List<City>) repository.findAll();
        Cities mycities = new Cities();
        mycities.setCities(cities);
        return mycities;
    }

    @Override
    public City findById(UUID id) {

        City city = repository.findByGuid(id);
        return city;
    }
}
