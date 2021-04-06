##Introduction
Prova de conceito da utilizacao do Apache Camel.


##  Installatio


 - ###Kafka
    docker-composer up -d
    
- #####kafdrop
   * To access the Kafdrop http://localhost:19000/
   
   
## DynamoDB

Para rodar o DynamoDB localmente, rode o seguinte comando na raiz do projeto.
__Pre-requisitos: docker e docker-compose__

```shell
docker-compose up
```

### GUI para DynamoDB

Caso queira acessar o DynamoDB localmente, é possivel utilizar o **"DynamoDb-GUI-Client"**.
Para isso siga os passos aqui: https://github.com/Arattian/DynamoDb-GUI-Client