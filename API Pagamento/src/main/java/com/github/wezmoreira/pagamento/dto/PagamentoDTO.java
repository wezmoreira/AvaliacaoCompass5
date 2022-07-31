package com.github.wezmoreira.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoDTO {
    private Long id;
    private Double total;
}
