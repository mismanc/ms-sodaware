package com.sodaware.web.events;

import com.sodaware.web.model.SodaDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends SodaEvent{
    public NewInventoryEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
