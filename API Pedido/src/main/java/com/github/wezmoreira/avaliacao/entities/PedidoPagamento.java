package com.github.wezmoreira.avaliacao.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.avaliacao.enums.EnumMarca;
import com.github.wezmoreira.avaliacao.enums.EnumMoeda;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido_pagamentos")
public class PedidoPagamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero_cartao;
    private String nome_cartao;
    private String codigo_seguranca;
    @Enumerated(EnumType.STRING)
    private EnumMarca marca;
    private String mes_expiracao;
    private String ano_expiracao;
    @Enumerated(EnumType.STRING)
    private EnumMoeda moeda;
    private Double valor;
}
