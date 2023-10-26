# Publicando Sua API REST na Nuvem Usando Spring Boot 3, Java 17 ~~e Railway~~

Ministrado por Venilton Falvo Jr. através da plataforma da DIO no Bootcamp Santander Fullstack Java Angular.

O projeto foi criado através do [Spring Initializr](https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=dio&artifactId=santander-dev-2023&name=santander-dev-2023&description=Java%20RESTful%20API&packageName=dio.santander&dependencies=web,data-jpa,h2,postgresql), importado e descompactado.

O projeto não foi publicado no Railway pois a versão de teste já expirou, em vez disso, estou testando o [Render](https://render.com/)

## Diagrama de Classes (Domínio da API)

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Card card
    -Feature[] features
    -News[] news
  }

  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }

  class Feature {
    -String icon
    -String description
  }

  class Card {
    -String number
    -Number limit
  }

  class News {
    -String icon
    -String description
  }

  User "1" *-- "1" Account
  User "1" *-- "N" Feature
  User "1" *-- "1" Card
  User "1" *-- "N" News
```
