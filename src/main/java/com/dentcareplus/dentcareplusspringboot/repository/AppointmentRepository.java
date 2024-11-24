package com.dentcareplus.dentcareplusspringboot.repository;

import com.dentcareplus.dentcareplusspringboot.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}