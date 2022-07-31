package com.github.wezmoreira.avaliacao.dto.response;

import com.github.wezmoreira.avaliacao.entities.Item;
import lombok.Data;

import java.util.List;

@Data
public class ResponsePedidoDTO {

    private Long id;
    private String cpf;
    private Double total;
    private List<Item> itens;

}
