package com.github.wezmoreira.pagamento.consumer;


import com.github.wezmoreira.pagamento.dto.rabbitMQ.RetornaStatus;
import com.github.wezmoreira.pagamento.entities.Pagamento;
import com.github.wezmoreira.pagamento.repository.RepositoryPagamento;
import com.github.wezmoreira.pagamento.service.ServicePagamento;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerPagamento {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ServicePagamento servicePagamento;

    @Autowired
    private ServicePagamento servicePagamentoTEST;

    @Autowired
    private RepositoryPagamento repositoryPagamento;

    public static final String QUEUE = "pedidos.v1.pedidos-criados";

    public static final String queueRetorna = "pedidos.v1.pagamentos-realizados";


    @RabbitListener(queues = QUEUE)
    public void consumidor(RecebeDadosDTO pagamentoDTO) {
        System.out.println("Id recebido: " + pagamentoDTO.getId());
        System.out.println("Total recebido: " + pagamentoDTO.getTotal());
        System.out.println("*******************************");
        pagamento(pagamentoDTO);
    }

    public void pagamento(RecebeDadosDTO pagamentoDto){
        //Pagamento pagamento = new Pagamento();

        System.out.println("O recebimento do produto é : " + pagamentoDto);

        var retorno = servicePagamentoTEST.pagamentoBancoTEST(pagamentoDto);

        //pagamento.setPedidoId(pagamentoDto.getId());
        //pagamento.setCpf(pagamentoDto.getCpf());
        //pagamento.setTotal(pagamentoDto.getTotal());
        //pagamento.setStatus(retorno.getStatus());

        Pagamento pagamento = Pagamento.builder()
                .pedidoId(pagamentoDto.getId())
                .cpf(pagamentoDto.getCpf())
                .total(pagamentoDto.getTotal())
                .status(retorno.getStatus())
                .build();


        RetornaStatus retornaStatus = RetornaStatus.builder()
                .pedido_id(pagamento.getPedidoId())
                .status_pagamento(retorno.getStatus())
                .build();

        enviaDadosQueue(retornaStatus);

        repositoryPagamento.save(pagamento);
    }

    public void enviaDadosQueue(RetornaStatus retornaStatus){
        rabbitTemplate.convertAndSend(queueRetorna, retornaStatus);
    }
}
