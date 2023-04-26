# Credit Application System

![Java](https://img.shields.io/badge/Java-v17-blue.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-v1.7.22-purple.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.0.6-brightgreen.svg)
![Gradle](https://img.shields.io/badge/Gradle-v7.6.1-lightgreen.svg)
![H2](https://img.shields.io/badge/H2-v2.1.214-darkblue.svg)
![Flyway](https://img.shields.io/badge/Flyway-v9.5.1-red.svg)

## Description

Uma empresa de empréstimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma <strong>API REST SPRING BOOT E KOTLIN</strong> 🍃💜 para a empresa fornecer aos seus clientes as seguintes funcionalidades:

* ### Cliente (Customer)
    * **Cadastrar:**
      1. **Request:** ___firstName, lastName, cpf, income, email, password, zipCode e street___
      2. **Response:** ___String___
    * **Editar cadastro:**
      1. **Request:** ___id, firstName, lastName, income, zipCode, street___
      2. **Response:** ___firstName, lastName, income, cpf, email, income, zipCode, street___
    * **Visualizar perfil:**
      1. **Request:** ___id___
      2. **Response:** ___firstName, lastName, income, cpf, email, income, zipCode, street___
    * **Deletar cadastro:**
      1. **Request:** ___id___
      2. **Response:** ___sem retorno___
* ### Solicitação de Empréstimo (Credit):
    * **Cadastrar:**
      1. **Request:** ___creditValue, dayFirstOfInstallment, numberOfInstallments e customerId___
      2. **Response:** ___String___
    * **Listar todas as solicitações de emprestimo de um cliente:**
      1. **Request:** ___customerId___
      2. **Response:** ___creditCode, creditValue, numberOfInstallment___
    * **Visualizar um emprestimo:**
      1. **Request:** ___customerId e creditCode___
      2. **Response:** ___creditCode, creditValue, numberOfInstallment, status, emailCustomer e incomeCustomer___

![API para Sistema de Avaliação de Créditos](https://i.imgur.com/7phya16.png)
  
Diagrama UML Simplificado de uma API para Sistema de Avaliação de Crédito

![Arquitetura em 3 camadas Projeto Spring Boot](https://i.imgur.com/1Ea5PH3.png)

Arquitetura em 3 camadas Projeto Spring Boot

## DESAFIO

 Implemente as regras de negócio a seguir para a solicitação de empréstimo:

1. O máximo de parcelas permitido será 48
2. data da primeira parcela deverá ser no máximo 3 meses após o dia atual
