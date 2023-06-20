package com.ms.soda.events;

import com.ms.soda.model.SodaDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends SodaEvent{
    public NewInventoryEvent(SodaDto sodaDto) {
        super(sodaDto);
    }
}
