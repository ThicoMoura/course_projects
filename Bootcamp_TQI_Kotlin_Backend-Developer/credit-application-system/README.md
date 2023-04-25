# Credit Application System

![Java](https://img.shields.io/badge/Java-v17-blue.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-v1.7.22-purple.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.0.6-brightgreen.svg)
![Gradle](https://img.shields.io/badge/Gradle-v7.6.1-lightgreen.svg)
![H2](https://img.shields.io/badge/H2-v2.1.214-darkblue.svg)
![Flyway](https://img.shields.io/badge/Flyway-v9.5.1-red.svg)

## Description

Uma empresa de empréstimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma <strong>API REST SPRING BOOT E KOTLIN</strong> 🍃💜 para a empresa fornecer aos seus clientes as seguintes funcionalidades:

<ul>
<li><h3>Cliente (Customer):</h3>
  <ul>
    <li><strong>Cadastrar:</strong>
         <ol>
            <li><strong>Request: </strong><em>firstName, lastName, cpf, income, email, password, zipCode e street</em></li>
            <li><strong>Response: </strong><em>String</em></li>
        </ol>
    </li>
  <li><strong>Editar cadastro:</strong>
    <ol>
      <li><strong>Request: </strong><em>id, firstName, lastName, income, zipCode, street</em></li>
      <li><strong>Response: </strong><em>firstName, lastName, income, cpf, email, income, zipCode, street</em></li>
    </ol>
  </li>  
  <li><strong>Visualizar perfil:</strong>
    <ol>
      <li><strong>Request: </strong> <em>id</em></li>
      <li><strong>Response: </strong><em>firstName, lastName, income, cpf, email, income, zipCode, street</em></li>
    </ol> 
  </li>
  <li><strong>Deletar cadastro:</strong>
    <ol>
      <li><strong>Request: </strong><em>id</em></li>
      <li><strong>Response: </strong><em>sem retorno</em></li>
    </ol>
  </li>
  </ul>
  </li>
  <li><h3>Solicitação de Empréstimo (Credit):</h3>
  <ul>
    <li><strong>Cadastrar:</strong>
         <ol>
            <li><strong>Request: </strong><em>creditValue, dayFirstOfInstallment, numberOfInstallments e customerId</em></li>
            <li><strong>Response: </strong><em>String</em></li>
        </ol>
    </li>
    <li><strong>Listar todas as solicitações de emprestimo de um cliente:</strong>
    <ol>
      <li><strong>Request: </strong><em>customerId</em></li>
      <li><strong>Response: </strong><em>creditCode, creditValue, numberOfInstallment</em></li>
    </ol> 
    </li>
    <li><strong>Visualizar um emprestimo:</strong>
    <ol>
      <li><strong>Request: </strong><em>customerId e creditCode</em></li>
      <li><strong>Response: </strong><em>creditCode, creditValue, numberOfInstallment, status, emailCustomer e incomeCustomer</em></li>
    </ol> 
    </li>
</ul>

<figure>
<p align="center">
  <img src="https://i.imgur.com/7phya16.png" height="450" width="650" alt="API para Sistema de Avaliação de Créditos"/><br>
  Diagrama UML Simplificado de uma API para Sistema de Avaliação de Crédito
</p>
</figure>
<figure>
<p align="center">
  <img src="https://i.imgur.com/1Ea5PH3.png" height="350" width="600" alt="Arquitetura em 3 camadas Projeto Spring Boot"/><br>
  Arquitetura em 3 camadas Projeto Spring Boot
</p>
</figure>

<h3>DESAFIO</h3>
<p>Implemente as regras de negócio a seguir para a solicitação de empréstimo: </p>
<ol>
  <li>o máximo de parcelas permitido será 48</li>
  <li>data da primeira parcela deverá ser no máximo 3 meses após o dia atual</li>
</ol>
