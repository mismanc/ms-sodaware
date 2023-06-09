package com.sodaware.web.services;

import com.ms.soda.model.SodaDto;
import com.ms.soda.model.SodaStyle;
import com.sodaware.web.controller.NotFoundException;
import com.sodaware.web.domain.Soda;
import com.sodaware.web.mappers.SodaMapper;
import com.sodaware.web.repository.SodaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

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

    @Cacheable(cacheNames = "sodaCache", key = "#sodaId", condition = "#showInventoryOnHand == false")
    @Override
    public SodaDto getSodaById(UUID sodaId, Boolean showInventoryOnHand) {
        System.out.println("Called getSodaById");
        Soda soda = sodaRepository.findById(sodaId).orElseThrow(NotFoundException::new);
        if (showInventoryOnHand) {
            return sodaMapper.sodaToSodaDtoWithInventory(soda);
        }
        return sodaMapper.sodaToSodaDto(soda);
    }

    @Cacheable(cacheNames = "sodaCacheUpc", key = "#upc")
    @Override
    public SodaDto getSodaByUpc(Long upc) {
        System.out.println("Called getSodaByUpc");
        Soda soda = sodaRepository.findByUpc(upc).orElseThrow(NotFoundException::new);
        return sodaMapper.sodaToSodaDto(soda);
    }

    @Override
    public SodaDto updateSoda(SodaDto sodaDto) {
        Soda soda = sodaRepository.findById(sodaDto.getId()).orElseThrow(NotFoundException::new);
        soda.setSodaName(sodaDto.getSodaName());
        soda.setSodaStyle(sodaDto.getSodaStyle());
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

    @Cacheable(cacheNames = "sodaListCache", condition = "#showInventoryOnHand == false")
    @Override
    public Page<SodaDto> getAllSodas(String sodaName, SodaStyle sodaStyle, Boolean showInventoryOnHand, Pageable pageable) {

        System.out.println("Called getAllSodas");

        Page<Soda> sodaPage;
        if (!StringUtils.isEmpty(sodaName) && sodaStyle != null) {
            sodaPage = sodaRepository.findAllBySodaNameAndSodaStyle(sodaName, sodaStyle, pageable);
        } else if (!StringUtils.isEmpty(sodaName) && sodaStyle == null) {
            sodaPage = sodaRepository.findAllBySodaName(sodaName, pageable);
        } else if (StringUtils.isEmpty(sodaName) && sodaStyle != null) {
            sodaPage = sodaRepository.findAllBySodaStyle(sodaStyle, pageable);
        } else {
            sodaPage = sodaRepository.findAll(pageable);
        }
        if (!showInventoryOnHand) {
            return new PageImpl<>(sodaPage.stream().map(sodaMapper::sodaToSodaDto).collect(Collectors.toList()), pageable,
                    sodaPage.getTotalElements());
        }
        return new PageImpl<>(sodaPage.stream().map(sodaMapper::sodaToSodaDtoWithInventory).collect(Collectors.toList()), pageable,
                sodaPage.getTotalElements());
    }
}
