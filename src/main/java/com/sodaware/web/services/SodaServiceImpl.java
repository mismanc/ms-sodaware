package com.sodaware.web.services;

import com.sodaware.web.controller.NotFoundException;
import com.sodaware.web.domain.Soda;
import com.sodaware.web.mappers.SodaMapper;
import com.sodaware.web.model.SodaDto;
import com.sodaware.web.repository.SodaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class SodaServiceImpl implements SodaService {

    private final SodaRepository sodaRepository;
    private final SodaMapper sodaMapper;

    @Override
    public SodaDto saveSoda(SodaDto sodaDto) {
        return sodaMapper.sodaToSodaDto(sodaRepository.save(sodaMapper.sodaDtoToSoda(sodaDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public SodaDto getSodaById(UUID sodaId) {
        return sodaMapper.sodaToSodaDto(sodaRepository.findById(sodaId).orElseThrow(NotFoundException::new));
    }

    @Override
    public SodaDto updateSoda(SodaDto sodaDto) {
        Soda soda = sodaRepository.findById(sodaDto.getId()).orElseThrow(NotFoundException::new);
        soda.setSodaName(sodaDto.getSodaName());
        soda.setSodaStyle(sodaDto.getSodaStyle().name());
        soda.setPrice(sodaDto.getPrice());
        soda.setUpc(sodaDto.getUpc());
        return sodaMapper.sodaToSodaDto(sodaRepository.save(soda));
    }

    @Override
    public void deleteSodaById(UUID sodaId) {
        log.debug("Deleting a soda... id= " + sodaId.toString());
        Soda soda = sodaRepository.findById(sodaId).orElseThrow(NotFoundException::new);
        sodaRepository.delete(soda);
    }

    @Override
    public Page<SodaDto> getAllSodas(Pageable pageable) {
        return null;
    }
}
