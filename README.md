#Avaliação Sprint 5

##Endpoints

### Post
- http://localhost:8080/api/pedido

#### Body do Post (deve ser um Cpf válido!)
```
{
    "cpf": "00000000000",
    "total": 20.0,
    "itens": [
        {
            "nome": "Pilha",
            "data_criacao": "21/06/2022 03:25:03",
            "data_validade": "23/06/2023 03:25:03",
            "valor": 30.0,
            "descricao": "Pilha para uso doméstico",
            "ofertas": [
                {
                    "nome": "Saldão",
                    "data_criacao": "20/05/2022 02:20:02",
                    "data_validade": "20/06/2024 02:20:02",
                    "desconto": 10.0,
                    "descricao": "Teste"
                }
            ]
        }
    ]
}
```


### Get
- http://localhost:8080/api/pedido

#### get por cpf 
- http://localhost:8080/api/pedido/?cpf=10302373950

#### get por Sort do valor asc
- http://localhost:8080/api/pedido?sort=total,asc

#### get por Sort do valor desc
- http://localhost:8080/api/pedido?sort=total,desc

### Get por ID
- http://localhost:8080/api/pedido/{id}

### Patch Pedido
- http://localhost:8080/api/pedido/{id}

### Patch Item
- http://localhost:8080/api/item/{id}

### Delete Pedido
- http://localhost:8080/api/pedido/{id}



### Referências que utilizei para executar essa prova:

https://stackoverflow.com/questions/9841623/mockito-how-to-verify-method-was-called-on-an-object-created-within-a-method

https://www.netjstech.com/2015/06/method-reference-in-java-8.html

https://cursos.alura.com.br/forum/topico-beanvalidator-cpf-53635


https://www.baeldung.com/jpa-size-length-column-differences

https://fabiano-goes.medium.com/api-rest-com-paginação-usando-spring-data-e-query-9eddb29c9223

https://www.youtube.com/watch?v=SzcvuHjRJKE