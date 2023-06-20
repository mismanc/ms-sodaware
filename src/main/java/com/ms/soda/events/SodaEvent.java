package com.ms.soda.events;

import com.ms.soda.model.SodaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SodaEvent implements Serializable {

    private static final long serialVersionUID = 8433584450363635927L;

    private SodaDto sodaDto;


}
