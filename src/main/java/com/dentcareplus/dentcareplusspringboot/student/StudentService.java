package com.dentcareplus.dentcareplusspringboot.student;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List< Student> getStudents() {
        return List.of(
                new Student(1L, "Moksha Mudalige", LocalDate.of(2003, Month.OCTOBER, 9), 21)
        );
    }
}
