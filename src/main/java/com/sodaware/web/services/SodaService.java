package com.sodaware.web.services;


import com.sodaware.web.model.SodaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SodaService {
    SodaDto saveSoda(SodaDto sodaDto);

    SodaDto getSodaById(UUID sodaId);

    SodaDto updateSoda(SodaDto sodaDto);

    void deleteSodaById(UUID sodaId);

    Page<SodaDto> getAllSodas(Pageable pageable);
}
