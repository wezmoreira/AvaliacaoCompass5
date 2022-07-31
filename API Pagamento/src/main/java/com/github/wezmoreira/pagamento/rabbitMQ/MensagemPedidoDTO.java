package com.github.wezmoreira.pagamento.rabbitMQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPedidoDTO {
    private Long id;
    private Double total;
}
