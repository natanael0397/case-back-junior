Gerenciador de Biblioteca Digital

Este projeto consiste em uma API REST para o gerenciamento de uma biblioteca digital.

A aplicação oferece funcionalidades completas de CRUD (Create, Read, Update, Delete) para autores, livros e categorias, além de um módulo de web scraping para importar informações de livros de sites externos.

Visão Geral do Projeto

O sistema é construído sobre a stack Java 17+ e Spring Boot 3.x. A persistência de dados é gerenciada com Spring Data JPA e utiliza um banco de dados H2 em memória.

Como Executar o Projeto

1. Pré-requisitos:

Java Development Kit (JDK) 17 ou superior.

Maven ou Gradle.

2. Passos:

Clone o repositório.

Navegue até a pasta raiz do projeto.

Use o Maven para compilar e executar:

./mvnw spring-boot:run

O aplicativo estará disponível em http://localhost:8080.

Tecnologias e Dependências

Linguagem: Java 17+

Framework: Spring Boot 3.x

Persistência: Spring Data JPA

Banco de Dados: H2 (em memória)

Construção: Maven

Web Scraping: JSoup (ou similar)

HTTP Client: RestTemplate

***Estrutura de Endpoints da API***

A API foi projetada seguindo os princípios de um sistema RESTful, com recursos bem definidos para cada entidade.

***Endpoints de Autores***

GET	/api/autores	Lista todos os autores com paginação.

GET	/api/autores/{id}	Busca um autor específico por ID.

POST	/api/autores	Cria um novo autor.

PUT	/api/autores/{id}	Atualiza um autor existente.

DELETE	/api/autores/{id}	Deleta um autor.

GET	/api/autores/{id}/livros	Lista os livros de um autor específico.

***Endpoints de Livros***

Método	Endpoint	Descrição

GET	/api/livros	Lista todos os livros com filtros (categoria, ano, autor).

GET	/api/livros/{id}	Busca um livro por ID.

POST	/api/livros	Cria um novo livro.

PUT	/api/livros/{id}	Atualiza um livro existente.

DELETE	/api/livros/{id}	Deleta um livro.

GET	/api/livros/search	Busca livros por título. Ex: /api/livros/search?titulo=java.

POST	/api/livros/importar	Importa dados de um livro via web scraping.

***Endpoints de Categorias***

GET	/api/categorias	Lista todas as categorias.

POST	/api/categorias	Cria uma nova categoria.

GET	/api/categorias/{id}/livros	Lista os livros de uma categoria específica.

Web Scraping

O endpoint /api/livros/importar permite a importação de dados de livros de sites externos.

URL de Exemplo para Scraping: https://www.amazon.com.br/dp/{ASIN}

A implementação foi desenvolvida seguindo as boas práticas da engenharia de software, com uma estrutura de camadas bem definida (Controller, Service, Repository), uso de DTOs e validações com Bean Validation. A documentação da API está disponível via Swagger/OpenAPI.
