package com.example;

import com.example.repository.CarRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

    //load all Cars from file to memory
    @Bean
    public CommandLineRunner dataLoader(CarRepository repo) {
        return args -> repo.readFromDiskAndLoadAllCarsInMemory();
    }
}
