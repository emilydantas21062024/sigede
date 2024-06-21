# SIGEDE

SIGEDE é uma aplicação web desenvolvida com Java Spring Boot no backend e React no frontend. A aplicação permite gerenciar clientes e boletos.

## Estrutura de Pastas

A estrutura de pastas do projeto é a seguinte:

- `src/main/java/br/com/projecao/sigede`: Contém os arquivos de código fonte Java do backend.
- `frontend/src`: Contém os arquivos de código fonte JavaScript do frontend.
- `frontend/src/components`: Contém os componentes React criados e utilizados na aplicação.
- `frontend/src/helpers`: Contém arquivos auxiliares, como o `axios_helper.js` que é usado para fazer requisições HTTP para a API.

## Como Executar o Projeto

Siga os passos abaixo para executar o projeto:

1. Clone o repositório para a sua máquina local usando `git clone`.

2. Navegue até a pasta do projeto usando `cd SIGEDE`.

3. Para executar o backend, navegue até a pasta `src/main/java/br/com/projecao/sigede` e execute o comando `mvn spring-boot:run`.

4. Para executar o frontend, navegue até a pasta `frontend` e execute os comandos `npm install` para instalar as dependências e `npm start` para iniciar o servidor de desenvolvimento.

5. Abra o navegador e acesse `http://localhost:3000` para ver a aplicação em execução.

6. Para criar o banco de dados, execute o script `create_database.sql` no pgAdmin.