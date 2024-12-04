package com.fiap.parkmongoapi.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enum que representa os perfis de motorista.")
public enum EnumPerfil {
    @Schema(description = "Perfil de motorista comum.")
    COMUM,

    @Schema(description = "Perfil de motorista idoso.")
    IDOSO,

    @Schema(description = "Perfil de motorista com deficiência.")
    PCD
}

