package com.github.wezmoreira.pagamento.dto.rabbitMQ;

import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponseCartaoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoCartaoDTO;
import com.github.wezmoreira.pagamento.entities.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemPedidoDTO {
    private Long id;
    private String cpf;
    private String tipo_pagamento;  //alterei 18:59
    private ResponseCartaoDTO cartao;
    private Double total;


    //private LocalDateTime data;
    //private String status;
    private Pagamento pagamento;
    //private ResponsePagamentoCartaoDTO pagamento_cartao;
}
