# 📚 Literalura - Catálogo de Livros

Este projeto é um desafio de **Back-end** desenvolvido como parte da formação Java da Alura em parceria com a Oracle (**Programa ONE**). O objetivo principal foi construir um catálogo de livros que consome a API externa [Gutendex](https://gutendex.com/), realiza o mapeamento dos dados JSON e os persiste em um banco de dados relacional.

O projeto permite a interação via console para busca, listagem e filtragem de livros e autores de forma dinâmica.

---

## ✨ Funcionalidades

- **Buscar livro pelo título:** Consulta a API Gutendex, processa os dados e salva o livro e seu autor no banco de dados.
- **Listar livros registrados:** Exibe todos os títulos armazenados no banco de dados.
- **Listar autores registrados:** Lista todos os autores salvos com seus respectivos anos de nascimento e falecimento.
- **Listar autores vivos em determinado ano:** Realiza uma busca filtrada por ano para identificar quais autores estavam vivos naquela época.
- **Listar livros por idioma:** Filtra as obras salvas por siglas de idioma (ex: `pt`, `en`, `es`, `fr`).

---

## 💻 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL** (Banco de dados relacional)
- **Gutendex API** (Fonte de dados)
- **Jackson** (Manipulação de JSON)
- **Maven** (Gerenciamento de dependências)

---

## 🧠 Aprendizados

Durante o desenvolvimento deste projeto, aprimorei os seguintes conhecimentos:

- **Consumo de API:** Uso de `HttpClient` e `HttpRequest` para buscar dados externos.
- **Persistência de Dados:** Configuração e uso do Spring Data JPA para interagir com o PostgreSQL.
- **Relacionamentos SQL:** Implementação de relacionamentos `@ManyToOne` e `@OneToMany` entre Livros e Autores.
- **Queries Personalizadas:** Criação de consultas usando **JPQL** para filtros específicos.
- **Modelagem de Dados:** Transformação de registros simples em uma estrutura de banco de dados robusta.

---

Feito com 💙 por Giovanna durante os estudos no Programa ONE (Oracle Next Education).
