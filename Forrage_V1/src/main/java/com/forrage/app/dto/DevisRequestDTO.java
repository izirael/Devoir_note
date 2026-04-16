package com.forrage.app.dto;

import lombok.Data;
import java.util.List;

@Data
public class DevisRequestDTO {
    private Long demandeId;
    private Double montantTotal;
    private String libelle;
    private List<DevisItemDTO> items;

    @Data
    public static class DevisItemDTO {
        private String typeName;
        private Double quantite;
        private Double prixUnitaire;
        private Double total;
    }
}
