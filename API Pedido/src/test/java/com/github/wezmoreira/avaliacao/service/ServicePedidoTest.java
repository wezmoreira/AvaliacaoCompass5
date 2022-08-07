package com.github.wezmoreira.avaliacao.service;

import com.github.wezmoreira.avaliacao.AplicationConfigTest;
import com.github.wezmoreira.avaliacao.dto.rabbit.RecebeDadoPagamentoDTO;
import com.github.wezmoreira.avaliacao.dto.request.RequestPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.request.atualizacao.RequestAtualizaPedidoDTO;
import com.github.wezmoreira.avaliacao.dto.response.ResponsePedidoDTO;
import com.github.wezmoreira.avaliacao.entities.Pedido;
import com.github.wezmoreira.avaliacao.enums.EnumStatusPagamento;
import com.github.wezmoreira.avaliacao.repositories.RepositoryPedido;
import com.github.wezmoreira.avaliacao.service.ServicePedido;
import com.github.wezmoreira.avaliacao.service.ServiceValidacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DisplayName("ServicePedidoTest")
public class ServicePedidoTest extends AplicationConfigTest {

    @MockBean
    private RepositoryPedido repositoryPedido;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    ServiceValidacao serviceValidacao;

    @Autowired
    ServicePedido servicePedido;


    @Test
    @DisplayName("Deveria deletar um pedido pelo o id")
    public void DeveriaDeletarPorId(){
        Pedido pedido = Mockito.mock(Pedido.class);
        pedido.setId(1L);
        Mockito.when(repositoryPedido.findById(1l)).thenReturn(Optional.of(pedido));
        servicePedido.delete(1l);
        Mockito.verify(repositoryPedido, Mockito.times(1)).findById(1l);
    }

    @Test
    @DisplayName("Deveria trazer os pedidos por id")
    public void DeveriaTrazerPedidosPorId(){
        Pedido pedido = Mockito.mock(Pedido.class);
        pedido.setId(1L);
        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.get(1l);
        Mockito.verify(repositoryPedido, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deveria fazer um novo cadastro de pedido")
    public void DeveriaCadastrarPedido(){
        Pedido pedido = Mockito.mock(Pedido.class);
        RequestPedidoDTO requestPedidoDTO = Mockito.mock(RequestPedidoDTO.class);
        ResponsePedidoDTO responsePedidoDTO = Mockito.mock(ResponsePedidoDTO.class);
        pedido.setCpf("71417389915");
        pedido.setTotal(50.0);
        Mockito.when(serviceValidacao.validaDataItem(requestPedidoDTO)).thenReturn(true);
        Mockito.when(serviceValidacao.validaDataOfertas(requestPedidoDTO)).thenReturn(true);
        repositoryPedido.save(pedido);
        Mockito.when(servicePedido.post(requestPedidoDTO)).thenReturn(responsePedidoDTO);
    }

    @Test
    @DisplayName("Deveria fazer um patch de algum campo de pedido")
    public void DeveriaFazerPatchDePedido(){
        Pedido pedido = Mockito.mock(Pedido.class);
        RequestAtualizaPedidoDTO requestAtualizaPedidoDTO = Mockito.mock(RequestAtualizaPedidoDTO.class);
        RequestAtualizaPedidoDTO pedidoDto =
                RequestAtualizaPedidoDTO.builder().cpf("71417389915").total(50.0).build();
        Mockito.when(repositoryPedido.findById(1L)).thenReturn(Optional.of(pedido));
        servicePedido.patch(1L, pedidoDto);
        Assertions.assertEquals(50.0, pedidoDto.getTotal());
    }

}
