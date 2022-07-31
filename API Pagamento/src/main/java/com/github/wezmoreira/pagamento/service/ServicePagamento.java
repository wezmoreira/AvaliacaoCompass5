package com.github.wezmoreira.pagamento.service;

import com.github.wezmoreira.pagamento.dto.PagamentoDTO;
import com.github.wezmoreira.pagamento.entities.Pagamento;
import com.github.wezmoreira.pagamento.repository.RepositoryPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.wezmoreira.pagamento.consumer.ConsumerPagamento.QUEUE;

@Service
public class ServicePagamento {
    @Autowired
    private RepositoryPagamento repositoryPagamento;


    public void pagamento(PagamentoDTO pagamentoDto){
        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(pagamentoDto.getId());
        pagamento.setTotal(pagamentoDto.getTotal());
        repositoryPagamento.save(pagamento);
    }
}
