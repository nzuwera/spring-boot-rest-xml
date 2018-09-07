package com.nzuwera.demorestxml.controller;

import com.nzuwera.demorestxml.bean.Cities;
import com.nzuwera.demorestxml.bean.City;
import com.nzuwera.demorestxml.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "/cities", produces = MediaType.APPLICATION_XML_VALUE)
    public Cities findCities() {

        return cityService.findAll();
    }

    @RequestMapping(value = "/cities/{cityId}", produces = MediaType.APPLICATION_XML_VALUE)
    public City findCity(@PathVariable Long cityId) {

        return cityService.findById(cityId);
    }
}
