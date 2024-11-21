package com.dentcareplus.dentcareplusspringboot.repository;

import com.dentcareplus.dentcareplusspringboot.entity.Denture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentureRepository extends JpaRepository<Denture, Long> {
}