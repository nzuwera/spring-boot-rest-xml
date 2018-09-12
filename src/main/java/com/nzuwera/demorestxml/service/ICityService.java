package com.nzuwera.demorestxml.service;

import com.nzuwera.demorestxml.bean.Cities;
import com.nzuwera.demorestxml.bean.City;

import java.util.UUID;

public interface ICityService {

    public Cities findAll();
    public City findById(UUID id);
}
