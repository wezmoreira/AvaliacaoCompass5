package com.github.wezmoreira.avaliacao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private Double total;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RL_pedidos_itens",
            joinColumns = @JoinColumn(
                    name = "pedido_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "item_id"
            )
    )
    private List<Item> itens;
}
