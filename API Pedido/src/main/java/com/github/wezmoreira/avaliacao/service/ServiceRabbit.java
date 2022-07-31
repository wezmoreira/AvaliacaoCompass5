package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.dto.rabbit.RabbitMensagemDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponsePedidoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRabbit {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void rabbitMensagem(ResponsePedidoDTO responsePedidoDTO) {
        RabbitMensagemDTO rabbitMensagemDTO = new RabbitMensagemDTO
                (responsePedidoDTO.getId(), responsePedidoDTO.getTotal());
        responsePedidoDTO.setId(rabbitMensagemDTO.getId());
        responsePedidoDTO.setTotal(rabbitMensagemDTO.getTotal());
        String routingKey = "pedidos.v1.pedidos-criados";
        rabbitTemplate.convertAndSend(routingKey, rabbitMensagemDTO);
    }
}
