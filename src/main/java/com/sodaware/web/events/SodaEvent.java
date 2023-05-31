package com.sodaware.web.events;

import com.sodaware.web.model.SodaDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class SodaEvent implements Serializable {

    private static final long serialVersionUID = 8433584450363635927L;

    private final SodaDto sodaDto;


}
