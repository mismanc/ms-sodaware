package com.sodaware.web.events;

import com.sodaware.web.model.SodaDto;

public class NewInventoryEvent extends SodaEvent{
    public NewInventoryEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
