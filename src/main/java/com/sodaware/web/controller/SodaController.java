package com.sodaware.web.controller;

import com.sodaware.web.model.SodaDto;
import com.sodaware.web.model.SodaStyle;
import com.sodaware.web.services.SodaService;
import com.sodaware.web.util.HeaderUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RequestMapping("/api/v1/soda")
@RestController
public class SodaController {

    private final SodaService sodaService;

    public SodaController(SodaService sodaService) {
        this.sodaService = sodaService;
    }

    @GetMapping
    public ResponseEntity<Page<SodaDto>> getAllSodas(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                     @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder,
                                                     @RequestParam(value = "sodaName", required = false) String sodaName,
                                                     @RequestParam(value = "sodaStyle", required = false) SodaStyle sodaStyle) {

        Page<SodaDto> sodaDtosPage = sodaService.getAllSodas(sodaName, sodaStyle, pageable);
        HttpHeaders headers = HeaderUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), sodaDtosPage);
        return ResponseEntity.ok().headers(headers).body(sodaDtosPage);
    }


    @GetMapping("/{sodaId}")
    public ResponseEntity<SodaDto> getSoda(@PathVariable UUID sodaId) {
        return new ResponseEntity<>(sodaService.getSodaById(sodaId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SodaDto> saveSoda(@RequestBody @Validated SodaDto sodaDto) {
        SodaDto newSoda = sodaService.saveSoda(sodaDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/soda/" + newSoda.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SodaDto> updateSoda(@RequestBody @Validated SodaDto sodaDto) {
        sodaService.updateSoda(sodaDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{sodaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSoda(@PathVariable UUID sodaId) {
        sodaService.deleteSodaById(sodaId);
    }

}
