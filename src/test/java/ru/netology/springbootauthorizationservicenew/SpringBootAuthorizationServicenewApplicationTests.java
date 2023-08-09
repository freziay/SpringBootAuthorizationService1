package ru.netology.springbootauthorizationservicenew;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAuthorizationServicenewApplicationTests {

    private static GenericContainer<?> myDevapp = new GenericContainer<>("devapp").withExposedPorts(8080);

    private static GenericContainer<?> myProdapp = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        myDevapp.start();
        myProdapp.start();

    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myDevapp.getMappedPort(8080), String.class);
        System.out.println(forEntity.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, forEntity.getStatusCode());
    }

    @Test
    void contextLoads1() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myProdapp.getMappedPort(8081), String.class);
        System.out.println(forEntity.getBody());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, forEntity.getStatusCode());
    }
}
