# Eicon-Teste

Url API REST

POST
Adicionar pedido: http://localhost:8080/Teste-Eicon/rest/pedidos/add

DEL
Deletar pedido: http://localhost:8080/Teste-Eicon/rest/pedidos/delete/{numero controle}

GET
Listar todos os pedidos: http://localhost:8080/Teste-Eicon/rest/pedidos/list
Listar pedidos por data: http://localhost:8080/Teste-Eicon/rest/pedidos/list/data/ {data cadastro}
Listar pedidos por numero controle: http://localhost:8080/Teste-Eicon/rest/pedidos/list/numero/{numero controle}
Listar pedidos por codigo: http://localhost:8080/Teste-Eicon/rest/pedidos/list/codigo/{codigo cliente}
Listar pedidos por data, codigo, numero controle: http://localhost:8080/Teste-Eicon/rest/pedidos/list/tudo/{numero controle}/{codigo cliente}/{data cadastro}

PUT
Editar pedido: http://localhost:8080/Teste-Eicon/rest/pedidos/edit/{numero controle}

Estrutura JSON
[
{
    "numeroControle": "",
    "dataCadastro": "yyyy/MM/dd",
    "nome": "",
    "valor": "",
    "quantidade": “0”,
    "codigoCliente": ""
}
]
