package com.example.service;

import com.example.domain.Car;
import com.example.domain.Coordinate;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarServiceImplTest {
    private static final CarService service = new CarServiceImpl(); //need to create one time for all tests

    @Test
    @Order(1)
    void loadCsvTest(){
        var content = """
                position_id,latitude,longitude
                8e0b1e1a-e290-4344-8fa1-39de81c9d265,53.0,20.0
                65d6375e-b641-4791-ac74-8a84222b2ce9,53.1,20.1
                537b9506-e923-4c99-a8f4-1d02e4079408,53.2,20.2
                62ae4317-a2a3-4d74-997c-921932ff918f,53.3,20.3
                2884f5f9-e902-41a6-bc7d-e3569922025b,53.4,20.4
                """;

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "gps_pos.csv",
                MediaType.TEXT_PLAIN_VALUE,
                content.getBytes()
        );
        service.loadCsv(file);
    }

    @Test
    @Order(2)
    void findAllByCoordinates() {
        final int distance = 100000; //100km
        final int expectedCarQuantity = 3;
        var targetCoordinate = new Coordinate(53.9037654770889, 20.887423009119);

        List<Car> byCoordinates = service.findByCoordinatesAndDistance(targetCoordinate, distance);
        byCoordinates.forEach(System.out::println);
        assertEquals(expectedCarQuantity, byCoordinates.size());
    }
}