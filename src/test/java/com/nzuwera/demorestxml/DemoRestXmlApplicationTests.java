package com.nzuwera.demorestxml;

import com.nzuwera.demorestxml.bean.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoRestXmlApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoRestXmlApplicationTests.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("http://localhost:${local.server.port}/${server.context-path}/cities")
    private String appPath;

    private City c1, c2, c3;

    @Before
    public void setUp() {

        this.c1 = new City(UUID.fromString("5e6db7cd-3875-483c-b0aa-6adafa86a63d"), "Bratislava", 432000);
        this.c2 = new City(UUID.fromString("5ca6dbe6-1865-404f-86e8-79c341971781"), "Budapest", 1759000);
        this.c3 = new City(UUID.fromString("175b262c-0845-4d35-a271-d8d9669ef6c6"), "Prague", 1280000);
    }

    @Test
    public void allCitiesTest() {

        ParameterizedTypeReference<List<City>> paramType = new ParameterizedTypeReference<List<City>>() {
        };

        ResponseEntity<List<City>> cities = restTemplate.exchange(appPath, HttpMethod.GET, null, paramType);

        assertThat(cities.getBody()).hasSize(7);
        assertThat(cities.getBody()).contains(this.c1, this.c2, this.c3);
    }

    @Test
    public void oneCity() {
        String uuid = "5e6db7cd-3875-483c-b0aa-6adafa86a63d";
//        UUID cityId = UUID.fromString(uuid);
        City city = this.restTemplate.getForObject(appPath + "/"+uuid, City.class);
        LOGGER.info(city.toString());
        assertThat(city).extracting("name", "population").containsExactly("Bratislava", 432000);
    }
}
