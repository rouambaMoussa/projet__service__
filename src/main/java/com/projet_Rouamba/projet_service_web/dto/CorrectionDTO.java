package com.projet_Rouamba.projet_service_web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class CorrectionDTO {
    private Long id;
    private double value;
    private UserDTO userDTO ;

}
