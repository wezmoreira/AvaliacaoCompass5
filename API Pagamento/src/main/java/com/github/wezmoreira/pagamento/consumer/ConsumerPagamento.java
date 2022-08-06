package com.github.wezmoreira.pagamento.consumer;


import com.github.wezmoreira.pagamento.dto.PagamentoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.rabbitMQ.MensagemPedidoDTO;
import com.github.wezmoreira.pagamento.entities.Pagamento;
import com.github.wezmoreira.pagamento.repository.RepositoryPagamento;
import com.github.wezmoreira.pagamento.service.ServicePagamento;
import com.github.wezmoreira.pagamento.service.ServicePagamentoTEST;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerPagamento {
    @Autowired
    private ServicePagamento servicePagamento;

    @Autowired
    private ServicePagamentoTEST servicePagamentoTEST;

    @Autowired
    private RepositoryPagamento repositoryPagamento;

    public static final String QUEUE = "pedidos.v1.pedidos-criados";

    @RabbitListener(queues = QUEUE)
    public void consumidor(RecebeDadosDTO pagamentoDTO) {
        System.out.println("Id recebido: " + pagamentoDTO.getId());
        System.out.println("Total recebido: " + pagamentoDTO.getTotal());
        System.out.println("*******************************");
        pagamento(pagamentoDTO);
    }

    public void pagamento(RecebeDadosDTO pagamentoDto){


        System.out.println("O recebimento do produto é : " + pagamentoDto);


        servicePagamentoTEST.pagamentoBancoTEST(pagamentoDto);
    }





    /*
    public void pagamento(PagamentoDTO pagamentoDto){
        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(pagamentoDto.getId());
        pagamento.setTotal(pagamentoDto.getTotal());
        MensagemPedidoDTO mensagemPedidoDTO = new MensagemPedidoDTO();

        var teste = servicePagamento.pagamentoBanco(mensagemPedidoDTO);
        System.out.println(teste);
        repositoryPagamento.save(pagamento);
    }

     */
}
