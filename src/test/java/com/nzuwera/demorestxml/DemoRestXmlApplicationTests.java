package com.nzuwera.demorestxml;

import com.nzuwera.demorestxml.bean.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoRestXmlApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("http://localhost:${local.server.port}/${server.context-path}/cities")
    private String appPath;

    private City c1, c2, c3;

    @Before
    public void setUp() {

        this.c1 = new City(1L, "Bratislava", 432000);
        this.c2 = new City(2L, "Budapest", 1759000);
        this.c3 = new City(3L, "Prague", 1280000);
    }

    @Test
    public void allCitiesTest() {

        ParameterizedTypeReference<List<City>> paramType = new ParameterizedTypeReference<List<City>>() {
        };

        ResponseEntity<List<City>> cities = restTemplate.exchange(appPath, HttpMethod.GET, null, paramType);

        assertThat(cities.getBody()).hasSize(8);
        assertThat(cities.getBody()).contains(this.c1, this.c2, this.c3);
    }

    @Test
    public void oneCity() {
        City city = this.restTemplate.getForObject(appPath + "/1/", City.class);
        assertThat(city).extracting("name", "population").containsExactly("Bratislava", 432000);
    }
}
