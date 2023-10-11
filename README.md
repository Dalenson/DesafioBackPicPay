# DesafioBackPicPay
## Descrição

Desenvolvido para treinamento utilizando como base o desafio de entrevista do PicPay encontrado no em: https://github.com/PicPay/picpay-desafio-backend

## Requisições Possíveis

| Método HTTP | Rota            | Descrição                                      | Exemplo de JSON                               |
|-------------|-----------------|------------------------------------------------|-----------------------------------------------|
| GET         | /users          | Recupera os usuarios criados                   | { "fistName":"Dale", "lastName":"Silva", "document":"123", "email":"dale@exemplo.com", "type":"COMMON", "balance": 10 } |                                      
| POST        | /users          | Cria usuário                                   | { "firstName":"Rodrigo", "lastName":"Borges", "document":"999", "email":"rodrigo@exemplo.com", "userType":"MERCHANT", "balance": 10 } |
| POST        | /transactions   | Cria uma transação                             | {"senderId":2,"receiverId":1,"value":10} |


Utilizado para autorizar as transações o caminho: https://y79go.wiremockapi.cloud/json/1
Utilizado para enviar POST sobre a notificação: https://y79go.wiremockapi.cloud/notify

Foi utilizado para este projeto o Spring Boot.
