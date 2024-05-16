package com.mertselimb.gerimedicatest.repositories;

import com.mertselimb.gerimedicatest.models.Csv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CsvRepository extends JpaRepository<Csv, String> {
    Optional<Csv> findByCode(String code);
}
