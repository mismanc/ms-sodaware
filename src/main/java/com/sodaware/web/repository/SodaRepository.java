package com.sodaware.web.repository;

import com.sodaware.web.domain.Soda;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SodaRepository extends PagingAndSortingRepository<Soda, UUID> {


}
