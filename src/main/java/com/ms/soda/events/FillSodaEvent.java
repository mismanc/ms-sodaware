package com.ms.soda.events;

import com.sodaware.web.model.SodaDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FillSodaEvent extends SodaEvent {

    public FillSodaEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
