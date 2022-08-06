package com.github.wezmoreira.pagamento.service;

import com.github.wezmoreira.pagamento.consumer.RecebeDadosDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestCartaoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestClienteDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestPagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.request.RequestToken;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponsePagamentoBancoDTO;
import com.github.wezmoreira.pagamento.dto.pagamento.response.ResponseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class ServicePagamentoTEST {
    @Autowired
    WebClient.Builder webClient;
    @Autowired
    WebClient web;

    //@Autowired
    //private WebClient webClient;


    private final String bancoAprovacao = "https://pb-getway-payment.herokuapp.com/v1/payments/credit-card";
    private final String autenticar = "https://pb-getway-payment.herokuapp.com/v1/auth";

    private final String numeroCartao= "dfe05208b105578c070f806c80abd3af09e246827d29b866cf4ce16c205849977c9496cbf0d0234f42\n" +
            "339937f327747075f68763537b90b31389e01231d4d13c\"";

    //Toda vez que chamar esse método vai retornar o token
    public ResponseToken autenticacao() {
        RequestToken wesleyToken = RequestToken.builder().client_id("client_id_wesley")
                .api_key("95801864-8009-41f4-a9d3-a602fbca4466").build();
        var retorno = webClient.build()
                .post().uri(autenticar).bodyValue(wesleyToken)
                .retrieve().bodyToMono(ResponseToken.class).block();

        log.info("O valor do Token aqui no método AUTENTICACAO é : " + retorno);

        return retorno;
    }

    //alterar titulo
    public ResponsePagamentoBancoDTO pagamentoBancoTEST(RecebeDadosDTO mensagemPedidoDTO){  //mudei de response pra request
        String autenticador = autenticacao().getAccess_token();

        System.out.println("O recebimento do produto no SERVICE é : " + mensagemPedidoDTO);

        //log.info("\n AAAAAAAAAAAAAAAAAAAAA \n" + autenticador);

        //String autenticador = "asasdasd2323123123";



        RequestPagamentoBancoDTO requestPagamentoBancoDTO = RequestPagamentoBancoDTO.builder()
                .seller_id("b2c3119f-e7f9-46ce-9531-9213e5dc5cb1")
                .customer(RequestClienteDTO.builder()
                        .document_type("CPF")
                        .document_number(mensagemPedidoDTO.getCpf()).build())
                .payment_type("CREDIT_CARD")
                .currency("BRL")
                .transaction_amount(mensagemPedidoDTO.getTotal())  //faltou o doublevalue
                .card(RequestCartaoDTO.builder()
                        //.number_token(mensagemPedidoDTO.getPagamento().getNumero_cartao())
                        //.number_token(criptografar(mensagemPedidoDTO.getPagamento().getNumero_cartao()))
                        .number_token("dfe05208b105578c070f806c80abd3af09e246827d29b866cf4ce16c205849977c9496cbf0d0234f42339937f327747075f68763537b90b31389e01231d4d13c")
                        .cardholder_name(mensagemPedidoDTO.getPagamento().getNome_cartao())
                        .security_code(mensagemPedidoDTO.getPagamento().getCodigo_seguranca())
                        .brand(mensagemPedidoDTO.getPagamento().getMarca())
                        .expiration_month(mensagemPedidoDTO.getPagamento().getMes_expiracao())
                        .expiration_year(mensagemPedidoDTO.getPagamento().getAno_expiracao())
                        .build()).build();


        return web.post()
                .uri(bancoAprovacao)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(autenticador))
                .bodyValue(requestPagamentoBancoDTO)
                .retrieve()
                .bodyToMono(ResponsePagamentoBancoDTO.class)
                .block();

        //log.info("\n O valor aqui no método PAGAMENTO é: " + requestPagamentoBancoDTO);


    }
}
