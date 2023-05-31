package com.sodaware.web.events;

import com.sodaware.web.model.SodaDto;

public class FillSodaEvent extends SodaEvent {

    public FillSodaEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
