package com.dentcareplus.dentcareplusspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DentCarePlusSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DentCarePlusSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            Patient patient1 = new Patient("John", "Doe", "01/01/1999", "male", "asd", "asd", "asd");
            patientRepository.save(patient1);
        };
    }
}
