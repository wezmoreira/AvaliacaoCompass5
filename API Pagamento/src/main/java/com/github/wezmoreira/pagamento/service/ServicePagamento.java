package com.github.wezmoreira.pagamento.service;

import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestCartaoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestClienteDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestPagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestToken;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponseToken;
import com.github.wezmoreira.pagamento.dto.rabbitMQ.MensagemPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServicePagamento {
    @Autowired
    WebClient.Builder webClient;
    @Autowired
    WebClient web;

    private final String bancoAprovacao = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";
    private final String autenticar = "https://pb-getway-payment.herokuapp.com/v1/auth";

    //Toda vez que chamar esse método vai retornar o token
    public ResponseToken autenticacao() {
        RequestToken wesleyToken = RequestToken.builder().client_id("client_id_wesley")
                .api_key("95801864-8009-41f4-a9d3-a602fbca4466").build();
        return webClient.build()
                .post().uri(autenticar).bodyValue(wesleyToken)
                .retrieve().bodyToMono(ResponseToken.class).block();
    }

    //alterar titulo
    /*
    public ResponsePagamentoBancoDTO pagamentoBanco(MensagemPedidoDTO mensagemPedidoDTO){
        //String autenticador = autenticacao().getAcess_token();
        String autenticador = "asasdasd2323123123";


        RequestPagamentoBancoDTO requestPagamentoBancoDTO = RequestPagamentoBancoDTO.builder()
                .seller_id("b2c3119f-e7f9-46ce-9531-9213e5dc5cb1").customer(RequestClienteDTO.builder()
                        .document_type("CPF").document_number(mensagemPedidoDTO.getCpf()).build())
                .payment_type("CREDIT_CARD").currency("BRL").transaction_amount(mensagemPedidoDTO.getTotal())  //faltou o doublevalue
                .card(RequestCartaoDTO.builder().number_token(mensagemPedidoDTO.getPagamento().getCartao().getNumber_token())
                        .cardholder_name(mensagemPedidoDTO.getCartao().getCardholder_name())
                        .security_code(mensagemPedidoDTO.getCartao().getSecurity_code())
                        .brand(mensagemPedidoDTO.getCartao().getBrand())
                        .expiration_month(mensagemPedidoDTO.getCartao().getExpiration_month())
                        .expiration_year(mensagemPedidoDTO.getCartao().getExpiration_year())
                        .build()).build();

        return web.post().uri(bancoAprovacao).bodyValue(requestPagamentoBancoDTO)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(autenticador))
                .retrieve().bodyToMono(ResponsePagamentoBancoDTO.class).block();
    }





     */













    /*

    @Autowired
    private RepositoryPagamento repositoryPagamento;


    public void pagamento(PagamentoDTO pagamentoDto){
        Pagamento pagamento = new Pagamento();
        pagamento.setPedidoId(pagamentoDto.getId());
        pagamento.setTotal(pagamentoDto.getTotal());
        repositoryPagamento.save(pagamento);
    }

     */
}
