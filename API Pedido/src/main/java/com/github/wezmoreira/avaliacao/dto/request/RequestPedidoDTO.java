package com.github.wezmoreira.avaliacao.dto.request;

import com.github.wezmoreira.avaliacao.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPedidoDTO {

    @NotBlank
    @CPF(message = "CPF inválido! Deve seguir o padrão de 11 digitos")
    private String cpf;
    @NotNull @Positive
    private Double total;
    private List<@Valid Item> itens;

}
