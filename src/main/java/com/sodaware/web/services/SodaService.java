package com.sodaware.web.services;


import com.ms.soda.model.SodaDto;
import com.ms.soda.model.SodaStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SodaService {
    SodaDto saveSoda(SodaDto sodaDto);

    SodaDto getSodaById(UUID sodaId, Boolean showInventoryOnHand);

    SodaDto updateSoda(SodaDto sodaDto);

    void deleteSodaById(UUID sodaId);

    Page<SodaDto> getAllSodas(String sodaName, SodaStyle sodaStyle, Boolean showInventoryOnHand, Pageable pageable);

    SodaDto getSodaByUpc(Long upc);
}
