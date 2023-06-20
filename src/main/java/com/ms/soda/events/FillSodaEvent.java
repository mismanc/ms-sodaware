package com.ms.soda.events;

import com.ms.soda.model.SodaDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FillSodaEvent extends SodaEvent {

    public FillSodaEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
