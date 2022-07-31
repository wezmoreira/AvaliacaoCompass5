package com.github.wezmoreira.pagamento.consumer;


import com.github.wezmoreira.pagamento.dto.PagamentoDTO;
import com.github.wezmoreira.pagamento.service.ServicePagamento;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerPagamento {
    @Autowired
    private ServicePagamento servicePagamento;

    public static final String QUEUE = "pedidos.v1.pedidos-criados";

    @RabbitListener(queues = QUEUE)
    public void consumidor(PagamentoDTO pagamentoDTO) {
        servicePagamento.pagamento(pagamentoDTO);
        System.out.println("Id recebido: " + pagamentoDTO.getId());
        System.out.println("Total recebido: " + pagamentoDTO.getTotal());
        System.out.println("*******************************");
    }
}
