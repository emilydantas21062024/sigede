# Nome do Projeto

Descrição curta sobre o que o projeto faz.

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Frontend**: React.js
- **Banco de Dados**: PostgreSQL

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- Java 11+
- Node.js 14+
- PostgreSQL 12+
- Maven 3.6+
- Git

## Configuração do Ambiente

### Banco de Dados

1. Instale o PostgreSQL:
   - Siga as instruções de instalação no site oficial: [PostgreSQL Downloads](https://www.postgresql.org/download/).

2. Crie um banco de dados PostgreSQL:
   ```sql
   CREATE DATABASE nome_do_banco;


## Executar o Projeto


# Backend

Siga os passos abaixo para executar o projeto:
````

# SIGEDE
	1.	Clone o repositório:
	
git clone https://github.com/usuario/nome-do-repositorio.git
cd nome-do-repositorio

    2.	Navegue até o backend:
    
cd backend

   	3.	Configure as variáveis de ambiente do Spring Boot no arquivo application.properties:

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

   	4.	Compile e execute a aplicação:
mvn clean install
mvn spring-boot:run

	5.	A aplicação estará disponível em http://localhost:8080.

````

## Frontend

Siga os passos abaixo para executar o projeto:

````
   	1.	Instale o Node.js:
	•	Siga as instruções de instalação no site oficial: Node.js Downloads.
	2.	Navegue até o diretório do frontend:
      
            cd frontend
            
   	3.	Instale as dependências do projeto:

                npm install
                
    4. Execite a aplicação:
    
                npm start
   
`````

Após configurar o backend e o frontend conforme descrito acima, você poderá acessar a aplicação através do navegador em http://localhost:3000. O frontend se comunicará com o backend em http://localhost:8080.


RLs Importantes

	•	Frontend: http://localhost:3000
	•	Backend: http://localhost:8080

Variáveis de Ambiente

	•	DB_URL: URL de conexão com o banco de dados PostgreSQL
	•	DB_USERNAME: Nome de usuário do banco de dados
	•	DB_PASSWORD: Senha do banco de dados


Este projeto está licenciado sob a Licença MIT. Veja o arquivo LICENSE para mais detalhes.
