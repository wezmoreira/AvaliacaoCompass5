package com.github.wezmoreira.avaliacao.dto.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RabbitMensagemDTO {
    private Long id;
    private Double total;
}
