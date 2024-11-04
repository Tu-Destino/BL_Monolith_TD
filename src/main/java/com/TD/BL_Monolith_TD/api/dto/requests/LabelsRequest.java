package com.TD.BL_Monolith_TD.api.dto.requests;

import com.TD.BL_Monolith_TD.infrastructure.helpers.WWcomma;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LabelsRequest {

    @NotNull
    @WWcomma(message = "Words in the String must be separated by commas ", words = {
            "Historia",
            "Cultura",
            "Arte",
            "Monumento",
            "Lugares",
            "Diseño Arquitectónico",
            "Gastronomía",
            "Restaurante",
            "Actividades",
            "Museo",
            "Parque",
            "Antiguo",
            "Hospedajes",
            "Lujo",
            "Naturaleza",
            "Montaña",
            "Mirador",
            "Bosque",
            "Jardín"})
    private String array;
}
