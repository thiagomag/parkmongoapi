# ParkMongoAPI

API de parquímetro desenvolvida como parte do curso de pós-graduação em tecnologia na FIAP.

## Funcionalidades

- Registro de veículos.
- Controle de tempo de estacionamento.
- Geração de relatórios de uso.
- Integração com banco de dados MongoDB.
- Monitoramento de logs com Elastic Stack (Elasticsearch, Logstash e Kibana).
- Documentação interativa com Swagger (OpenAPI).

## Tecnologias Utilizadas

Este projeto utiliza as seguintes tecnologias e ferramentas:

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **MongoDB**: Banco de dados NoSQL para armazenamento de dados.
- **Elastic Stack**:
    - **Elasticsearch**: Armazenamento e pesquisa de logs e dados estruturados.
    - **Logstash**: Pipeline de processamento de logs.
    - **Kibana**: Visualização e análise de logs e métricas.
- **Swagger** (OpenAPI): Para geração de documentação interativa da API.
- **Gradle**: Ferramenta de automação para build e gerenciamento de dependências.
- **Docker Compose**: Para execução simplificada da aplicação e seus serviços.

## Requisitos

Certifique-se de ter os seguintes itens instalados em sua máquina:

- Java 17+
- Docker e Docker Compose
- Gradle (opcional, caso prefira executar o projeto localmente)

## Como Executar o Projeto

### Usando Docker Compose (Recomendado)

1. Certifique-se de que o Docker e o Docker Compose estão instalados e funcionando.
2. No diretório raiz do projeto, execute o comando:
    ```bash
    docker-compose up --build
    ```
3. O Docker Compose iniciará os seguintes serviços:
    - **Aplicação Spring Boot**
    - **MongoDB**
    - **Elasticsearch**
    - **Logstash**
    - **Kibana**
4. Acesse os serviços:
    - Aplicação: [http://localhost:8080](http://localhost:8080)
    - Logs no Kibana: [http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc)))](http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc))))
    - Documentação Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Executando Localmente

1. Clone o repositório para sua máquina local:
    ```bash
    git clone https://github.com/vtarginoo/parkmongoapi.git
    ```

2. Acesse o diretório do projeto:
    ```bash
    cd parkmongoapi
    ```

3. Certifique-se de que o MongoDB, Elasticsearch, Logstash e Kibana estejam configurados e em execução localmente.

4. Configure as conexões e propriedades no arquivo `application.properties`.

5. Execute o projeto usando o Gradle:
    ```bash
    ./gradlew bootRun
    ```

6. Acesse os serviços:
    - Aplicação: [http://localhost:8080](http://localhost:8080)
    - Logs no Kibana: [http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc)))](http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc))))
    - Documentação Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Estrutura do Projeto

O projeto segue uma estrutura típica de aplicação Spring Boot:

- **/src/main/java**: Contém o código-fonte da aplicação.
    - **controller**: Controladores responsáveis pelas APIs.
    - **service**: Lógica de negócios.
    - **repository**: Interface para interações com o banco de dados.
    - **model**: Classes representando entidades de dados.
- **/src/main/resources**: Arquivos de configuração e propriedades.
    - `application.properties`: Configurações de ambiente.

## Monitoramento e Logs

O Elastic Stack é utilizado para o monitoramento e análise dos logs da aplicação:

1. **Logstash** processa os logs da aplicação e os envia para o **Elasticsearch**.
2. **Elasticsearch** armazena os logs e permite pesquisas rápidas.
3. **Kibana** fornece uma interface visual para explorar e analisar os logs.

Acesse os logs diretamente no Kibana:  
[http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc)))](http://localhost:5601/app/discover#/?_g=(filters:!(),refreshInterval:(pause:!t,value:60000),time:(from:now-15m,to:now))&_a=(columns:!(message),filters:!(),index:'16321dff-a7a2-475f-afd8-a69979a50a63',interval:auto,query:(language:kuery,query:''),sort:!(!('@timestamp',desc))))

## Documentação Interativa

A API conta com documentação interativa gerada pelo Swagger (OpenAPI).  
Acesse a documentação em:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Faça um fork do repositório.
2. Crie um branch para sua feature ou correção de bug:
    ```bash
    git checkout -b minha-feature
    ```
3. Realize as alterações e faça commit:
    ```bash
    git commit -m "Adiciona nova feature"
    ```
4. Envie suas alterações:
    ```bash
    git push origin minha-feature
    ```
5. Abra um Pull Request no GitHub.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---