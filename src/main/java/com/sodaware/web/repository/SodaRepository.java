package com.sodaware.web.repository;

import com.ms.soda.model.SodaStyle;
import com.sodaware.web.domain.Soda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SodaRepository extends JpaRepository<Soda, UUID> {

    Page<Soda> findAllBySodaNameAndSodaStyle(String sodaName, SodaStyle sodaStyle, Pageable pageable);

    Page<Soda> findAllBySodaName(String sodaName, Pageable pageable);

    Page<Soda> findAllBySodaStyle(SodaStyle sodaStyle, Pageable pageable);

    Optional<Soda> findByUpc(Long upc);

}
