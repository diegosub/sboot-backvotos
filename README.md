<h1 align="center">BACKVOTOS</h1>
<p align="center">API de Gerenciamento de votos para uma determinada pauta</p>


### Features

- [x] Cadastrar uma pauta;
- [x] Iniciar uma sessão de votação;
- [x] Cadastrar um voto;
- [x] Contabilizar o resultado da sessão após finalizada;
- [x] Visualizar o resultado;


### Pré-requisitos

Para rodar a api será necessário ter instalado na máquina o docker e docker-compose.

### 🎲 Buildando e Executando a API

```bash
# Clone este repositório
$ git clone https://github.com/diegosub/sboot-backvotos.git

# Abra o terminal na pasta raiz do projeto

# Execute o comando: 
      docker-compose up -d
      # Este comando irá gerar as imagens e criar o ambiente completo da api.
      
# Crie a fila no RabbitMQ:
      Entre na url: http://localhost:15672
      user: guest
      password: guest
      
      Vá até o menu queues, e adicione a fila com o nome: queue.resultado
      
# O swagger está disponível na url:
      http://localhost:8080/api/v1/swagger-ui.html

```

### Sobre a implementação

O projeto foi implementado utilizando a linguagem JAVA.
O projeto foi implementado seguindo o padrão da clean arch (Arquitetura limpa, Robert Martin)
O Projeto foi separado em 3 módulos: Domain, Application, Infrastructure
A implementação dos testes unitários foram separados, para cada módulo. Foram implementados testes unitários, integração e teste de api
O banco de dados utilizado foi o postgresql;
Os testes unitários, API e integração foi implementado com o JUnit 5, Mockito e MockMvc;
O Docker e Docker Compose foram configurados para a api subir atravéz de seus containers específicos;
A IDE utilizada para implementar o projeto foi o IntelliJ.

    















